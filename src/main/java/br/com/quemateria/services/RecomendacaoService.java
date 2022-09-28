package br.com.quemateria.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import br.com.quemateria.entities.Aluno;
import br.com.quemateria.entities.Disciplina;
import br.com.quemateria.entities.HorarioAula;
import br.com.quemateria.entities.MatrizCurricular;
import br.com.quemateria.repositories.HorarioAulaRepository;
import br.com.quemateria.repositories.MatrizCurricularRepository;

@Service
public class RecomendacaoService {

	private final HorarioAulaRepository horarioAulaRepository;
	private final MatrizCurricularRepository matrizCurricularRepository;
	private final MatrizCurricularService matrizCurricularService;
	private final HistoricoService historicoService;

	public RecomendacaoService(HorarioAulaRepository horarioAulaRepository,
			MatrizCurricularRepository matrizCurricularRepository, MatrizCurricularService matrizCurricularService,
			HistoricoService historicoService) {
		this.horarioAulaRepository = horarioAulaRepository;
		this.matrizCurricularRepository = matrizCurricularRepository;
		this.matrizCurricularService = matrizCurricularService;
		this.historicoService = historicoService;
	}

	public void calcularPeso(Long cursoId, Integer periodo) {
		List<MatrizCurricular> disciplinas = matrizCurricularRepository.findAll();

		// multiplicar periodo/disciplinaGetPeriodo vai elevar a prioridade de materias
		// obrigatorias... sem este fator, passa a valorizar mais trilhas

		for (MatrizCurricular disciplina : disciplinas)
			if (disciplina.getCurso().getId().equals(cursoId)) {
				matrizCurricularService.atualizarPeso((Math.log(disciplina.getPeriodo()) / Math.log(2))
						- (disciplina.getPreRequisitos() / 2) - disciplina.getTipoDeDisciplina().getTipoValor()
						- (periodo >= disciplina.getPeriodo() && historicoService.getDisciplinasObrigatoriasFaltantes()
								.contains(disciplina.getDisciplina()) ? 2 * (periodo / disciplina.getPeriodo()) : -2),
						disciplina.getId());
			}

	}

	public List<HorarioAula> recomendacaoCompleta(Aluno aluno, Long cursoId, Integer periodo, Boolean manha,
			Boolean tarde, Boolean noite, Integer cargaHorariaMaxima) {

		List<HorarioAula> recomendacaoManha = this.listarMateriasFaltantesPorTurno(aluno, cursoId, periodo, 1L, 6L);
		List<HorarioAula> recomendacaoTarde = this.listarMateriasFaltantesPorTurno(aluno, cursoId, periodo, 7L, 12L);
		List<HorarioAula> recomendacaoNoite = this.listarMateriasFaltantesPorTurno(aluno, cursoId, periodo, 13L, 17L);

		cargaHorariaMaxima *= 15;

		if (manha && !tarde && !noite)
			return this.recomendacaoPeriodoUnico(recomendacaoManha, cargaHorariaMaxima, aluno);
		if (!manha && tarde && !noite)
			return this.recomendacaoPeriodoUnico(recomendacaoTarde, cargaHorariaMaxima, aluno);
		if (!manha && !tarde && noite)
			return this.recomendacaoPeriodoUnico(recomendacaoNoite, cargaHorariaMaxima, aluno);
		if (manha && tarde && !noite)
			return this.recomendacaoPeriodoIntegral(recomendacaoManha, recomendacaoTarde, cargaHorariaMaxima, aluno);
		if (!manha && tarde && noite)
			return this.recomendacaoPeriodoIntegral(recomendacaoTarde, recomendacaoNoite, cargaHorariaMaxima, aluno);
		return new ArrayList<>();

	}

	public List<HorarioAula> gerarRelatorioDeRecomendacao(Aluno aluno, Long cursoId, Integer periodo, Boolean manha,
			Boolean tarde, Boolean noite, Integer cargaHorariaMaxima) {

		List<HorarioAula> recomendacao = this.recomendacaoCompleta(aluno, cursoId, periodo, manha, tarde, noite,
				cargaHorariaMaxima);

		List<String> codigos = new ArrayList<>();
		List<HorarioAula> relatorio = new ArrayList<>();

		for (HorarioAula horarioAula : recomendacao)
			if (!codigos.contains(getTurmaCodigo(horarioAula))) {
				codigos.add(getTurmaCodigo(horarioAula));
				relatorio.add(horarioAula);
			}

		return relatorio;

	}

	public List<HorarioAula> recomendarMateriasOpcionaisPorHorario(Aluno aluno, Long cursoId, Integer dia,
			String horario) {
		Set<Disciplina> disciplinasCursadas = aluno.getDisciplinasCursadas();

		List<HorarioAula> listaHorariosAula = listarHorarioAulaPorCursoTipoDiaHorario(cursoId, 4L, dia, horario);

		List<HorarioAula> recomendacao = new ArrayList<>();

		List<Disciplina> disciplinasEscolhidas = new ArrayList<>();

		for (HorarioAula horarioAula : listaHorariosAula)
			if (!disciplinasCursadas.contains(getTurmaDisciplina(horarioAula))
					&& !disciplinasEscolhidas.contains(getTurmaDisciplina(horarioAula))) {
				disciplinasEscolhidas.add(getTurmaDisciplina(horarioAula));
				recomendacao.addAll(listarHorarioAulaPorTurmaCodigo(getTurmaCodigo(horarioAula)));
			}

		return recomendacao;

	}

	public List<HorarioAula> recomendarMateriasEnriquecimentoPorHorario(Aluno aluno, Long cursoId, Integer dia,
			String horario) {
		Set<Disciplina> disciplinasCursadas = aluno.getDisciplinasCursadas();

		List<HorarioAula> listaHorarios = listarHorarioAulaExcetoCursoId(cursoId, dia, horario);

		List<HorarioAula> recomendacao = new ArrayList<>();

		List<Disciplina> disciplinasEscolhidas = new ArrayList<>();

		for (HorarioAula horarioAula : listaHorarios)
			if (!disciplinasCursadas.contains(getTurmaDisciplina(horarioAula))
					&& !disciplinasEscolhidas.contains(getTurmaDisciplina(horarioAula))) {
				disciplinasEscolhidas.add(getTurmaDisciplina(horarioAula));
				recomendacao.addAll(listarHorarioAulaPorTurmaCodigo(getTurmaCodigo(horarioAula)));
			}

		return recomendacao;

	}

	private List<HorarioAula> listarMateriasFaltantesPorTurno(Aluno aluno, Long cursoId, Integer periodo,
			Long horarioInicialId, Long horarioFinalId) {

		Set<Disciplina> disciplinasCursadas = aluno.getDisciplinasCursadas();

		List<HorarioAula> materiasFaltantes = new ArrayList<>();

		if (horarioInicialId == 1L)
			materiasFaltantes = listarHorarioAulaPorCursoIdAtePeriodoFaixaHorarioAsc(cursoId, periodo, horarioInicialId,
					horarioFinalId);
		else
			materiasFaltantes = listarHorarioAulaPorCursoIdAtePeriodoFaixaHorarioDesc(cursoId, periodo,
					horarioInicialId, horarioFinalId);

		List<HorarioAula> listaRecomendacao = new ArrayList<>();

		for (HorarioAula horarioAula : materiasFaltantes)
			if (!disciplinasCursadas.contains(getTurmaDisciplina(horarioAula))
					&& adicionarDisciplinaOpcional(horarioAula, false)) {
				listaRecomendacao.add(horarioAula);
			}

		return listaRecomendacao;
	}

	private Boolean adicionarDisciplinaOpcional(HorarioAula horarioAula, Boolean opcional) {
		List<Disciplina> disciplinasOpcionais = historicoService.listarDisciplinasOpcionais();

		if (opcional)
			return disciplinasOpcionais.contains(getTurmaDisciplina(horarioAula));
		return !disciplinasOpcionais.contains(getTurmaDisciplina(horarioAula));
	}

	private List<HorarioAula> recomendacaoPeriodoUnico(List<HorarioAula> lista, Integer cargaHorariaMaxima,
			Aluno aluno) {

		Integer numeroDeHorasMaximas = cargaHorariaMaxima / 15;

		List<HorarioAula> recomendacao = new ArrayList<>();
		List<Disciplina> disciplinas = new ArrayList<>();

		Set<Disciplina> disciplinasCursadas = aluno.getDisciplinasCursadas();

		for (HorarioAula horarioAula : lista)
			if (cargaHorariaMaxima >= 0 && !recomendacao.contains(horarioAula)
					&& !disciplinas.contains(getTurmaDisciplina(horarioAula))
					&& horarioValido(recomendacao, horarioAula, numeroDeHorasMaximas)
					&& !faltaPreRequisito(recomendacao, horarioAula, disciplinasCursadas)) {

				recomendacao.addAll(this.listarHorarioAulaPorTurmaCodigo(getTurmaCodigo(horarioAula)));
				disciplinas.add(getTurmaDisciplina(horarioAula));
				cargaHorariaMaxima -= getTurmaDisciplina(horarioAula).getCargaHoraria();
			}

		/*
		 * if (!recomendacao.isEmpty() && cargaHorariaMaxima <= 0)
		 * recomendacao.remove(recomendacao.size() - 1);
		 */

		return ordenarListaDeRecomendacao(recomendacao);

	}

	private List<HorarioAula> recomendacaoPeriodoIntegral(List<HorarioAula> listaPrimeiroTurno,
			List<HorarioAula> listaSegundoTurno, Integer cargaHorariaMaxima, Aluno aluno) {

		List<HorarioAula> listRecomendacao = this.horariosPossiveisPeriodoIntegral(listaPrimeiroTurno,
				listaSegundoTurno, cargaHorariaMaxima, aluno);

		return horariosEscolhidosPeriodoIntegral(listRecomendacao, cargaHorariaMaxima, aluno);

	}

	private List<HorarioAula> horariosPossiveisPeriodoIntegral(List<HorarioAula> listaPrimeiroTurno,
			List<HorarioAula> listaSegundoTurno, Integer cargaHorariaMaxima, Aluno aluno) {

		List<HorarioAula> listRecomendacao = new ArrayList<>();
		listaPrimeiroTurno = this.recomendacaoPeriodoUnico(listaPrimeiroTurno, cargaHorariaMaxima, aluno);
		listaSegundoTurno = this.recomendacaoPeriodoUnico(listaSegundoTurno, cargaHorariaMaxima, aluno);

		listRecomendacao.addAll(listaPrimeiroTurno);
		listRecomendacao.addAll(listaSegundoTurno);

		return listRecomendacao;
	}

	private List<HorarioAula> horariosEscolhidosPeriodoIntegral(List<HorarioAula> listRecomendacao,
			Integer cargaHorariaMaxima, Aluno aluno) {
		List<HorarioAula> listaRecomendacao = new ArrayList<>();
		List<Disciplina> disciplinas = new ArrayList<>();

		Set<Disciplina> disciplinasCursadas = aluno.getDisciplinasCursadas();

		Integer horasMaximas = cargaHorariaMaxima / 15;

		for (HorarioAula horarioAula : listRecomendacao)
			if (cargaHorariaMaxima >= 0 && !listaRecomendacao.contains(horarioAula)
					&& !disciplinas.contains(getTurmaDisciplina(horarioAula))
					&& horarioValido(listaRecomendacao, horarioAula, horasMaximas)
					&& !faltaPreRequisito(listaRecomendacao, horarioAula, disciplinasCursadas)) {
				listaRecomendacao.addAll(this.listarHorarioAulaPorTurmaCodigo(getTurmaCodigo(horarioAula)));
				disciplinas.add(getTurmaDisciplina(horarioAula));
				cargaHorariaMaxima -= getTurmaDisciplina(horarioAula).getCargaHoraria();
			}

		/*
		 * if (!listaRecomendacao.isEmpty() && cargaHorariaMaxima <= 0)
		 * listaRecomendacao.remove(listaRecomendacao.size() - 1);
		 */

		return ordenarListaDeRecomendacao(listaRecomendacao);

	}

	private Disciplina getTurmaDisciplina(HorarioAula horarioAula) {
		return horarioAula.getTurma().getDisciplina();
	}

	private String getTurmaCodigo(HorarioAula horarioAula) {
		return horarioAula.getTurma().getCodigo();
	}

	private Boolean horarioValido(List<HorarioAula> recomendacao, HorarioAula disciplina, Integer horasMaximas) {

		if (recomendacao.isEmpty())
			return true;

		List<HorarioAula> listaParaValidar = this.listarHorarioAulaPorTurmaCodigo(disciplina.getTurma().getCodigo());

		for (HorarioAula horarioAula : recomendacao)
			if (horarioAula == disciplina)
				return false;

		for (HorarioAula horasParaValidar : listaParaValidar)
			if (recomendacao.contains(horasParaValidar))
				return false;

		if (recomendacao.size() + listaParaValidar.size() > horasMaximas)
			return false;
		return true;

	}

	private Boolean faltaPreRequisito(List<HorarioAula> recomendacao, HorarioAula disciplina,
			Set<Disciplina> disciplinasCursadas) {
		Set<Disciplina> preRequisitos = disciplina.getTurma().getDisciplina().getRequisitos();

		if (preRequisitos.isEmpty())
			return false;

		Set<Disciplina> setDisciplinasCursadas = new HashSet<>(disciplinasCursadas);

		Integer tamanhoOriginal = setDisciplinasCursadas.size();
		setDisciplinasCursadas.removeAll(preRequisitos);
		Integer novoTamanho = setDisciplinasCursadas.size();

		return tamanhoOriginal == novoTamanho;
	}

	private List<HorarioAula> ordenarListaDeRecomendacao(List<HorarioAula> lista) {
		Collections.sort(lista, (o1, o2) -> Long.compare(o1.getId(), o2.getId()));
		Collections.sort(lista, (o1, o2) -> (o2.getTurma().getCodigo().compareTo(o1.getTurma().getCodigo())));
		Collections.sort(lista, (o1, o2) -> Long.compare(o1.getDia().getId(), o2.getDia().getId()));

		return lista;
	}

	public List<HorarioAula> listarHorarioAula() {
		return horarioAulaRepository.findAll();
	}

	public List<HorarioAula> listarHorarioAulaPorCursoTipoDiaHorario(Long cursoId, Long tipoId, Integer dia,
			String horario) {
		return horarioAulaRepository
				.findAllByTurma_Disciplina_MatrizCurricular_Curso_IdAndTurma_Disciplina_MatrizCurricular_TipoDeDisciplina_IdAndDia_IdentificadorAndHorario_Sigla(
						cursoId, tipoId, dia, horario);
	}

	public List<HorarioAula> listarHorarioAulaPorTurmaCodigo(String codigo) {
		return horarioAulaRepository.findAllByTurma_Codigo(codigo);
	}

	public List<HorarioAula> listarHorarioAulaPorDiaHorario(Integer dia, String horario) {
		return horarioAulaRepository.findAllByDia_IdentificadorAndHorario_Sigla(dia, horario);
	}

	public List<HorarioAula> listarHorarioAulaCursoId(Long cursoId) {
		return horarioAulaRepository.findAllByTurma_Disciplina_MatrizCurricular_CursoId(cursoId);
	}

	public List<HorarioAula> listarHorarioAulaExcetoCursoId(Long cursoId, Integer dia, String horario) {
		return horarioAulaRepository
				.findAllByTurma_Disciplina_MatrizCurricular_CursoIdNotAndDia_IdentificadorAndHorario_Sigla(cursoId, dia,
						horario);
	}

	public List<HorarioAula> listarHorarioAulaPorCursoIdAtePeriodoFaixaHorarioAsc(Long cursoId, Integer periodo,
			Long horarioInicialId, Long horarioFinalId) {
		return horarioAulaRepository
				.findAllByTurma_Disciplina_MatrizCurricular_Curso_IdAndTurma_Disciplina_MatrizCurricular_PeriodoLessThanAndHorario_IdBetweenOrderByTurma_Disciplina_MatrizCurricular_PesoAscHorario_IdAsc(
						cursoId, periodo, horarioInicialId, horarioFinalId);
	}

	public List<HorarioAula> listarHorarioAulaPorCursoIdAtePeriodoFaixaHorarioDesc(Long cursoId, Integer periodo,
			Long horarioInicialId, Long horarioFinalId) {
		return horarioAulaRepository
				.findAllByTurma_Disciplina_MatrizCurricular_Curso_IdAndTurma_Disciplina_MatrizCurricular_PeriodoLessThanAndHorario_IdBetweenOrderByTurma_Disciplina_MatrizCurricular_PesoAscHorario_IdDesc(
						cursoId, periodo, horarioInicialId, horarioFinalId);
	}

}
