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

	public RecomendacaoService(HorarioAulaRepository horarioAulaRepository,
			MatrizCurricularRepository matrizCurricularRepository, MatrizCurricularService matrizCurricularService) {
		this.horarioAulaRepository = horarioAulaRepository;
		this.matrizCurricularRepository = matrizCurricularRepository;
		this.matrizCurricularService = matrizCurricularService;
	}

	public void calcularPeso() {
		List<MatrizCurricular> disciplinas = matrizCurricularRepository.findAll();

		for (MatrizCurricular disciplina : disciplinas)
			if (disciplina.getPeso() == 0.0) {
				matrizCurricularService.atualizarPeso((Math.log(disciplina.getPeriodo()) / Math.log(2))
						- (disciplina.getPreRequisitos() / 2) - disciplina.getTipoDeDisciplina().getTipoValor(),
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
			if (!codigos.contains(horarioAula.getTurma().getCodigo())) {
				codigos.add(horarioAula.getTurma().getCodigo());
				relatorio.add(horarioAula);
			}

		return relatorio;

	}

	private List<HorarioAula> listarMateriasFaltantesPorTurno(Aluno aluno, Long cursoId, Integer periodo,
			Long horarioInicialId, Long horarioFinalId) {

		Set<Disciplina> disciplinasCursadas = aluno.getDisciplinasCursadas();

		List<HorarioAula> materiasFaltantes = new ArrayList<>();
		
		

		if (horarioInicialId == 1L)
			materiasFaltantes = horarioAulaRepository
					.findAllByTurma_Disciplina_MatrizCurricular_Curso_IdAndTurma_Disciplina_MatrizCurricular_PeriodoLessThanAndHorario_IdBetweenOrderByTurma_Disciplina_MatrizCurricular_PesoAscHorario_IdAsc(
							cursoId, periodo, horarioInicialId, horarioFinalId);
		else
			materiasFaltantes = horarioAulaRepository
					.findAllByTurma_Disciplina_MatrizCurricular_Curso_IdAndTurma_Disciplina_MatrizCurricular_PeriodoLessThanAndHorario_IdBetweenOrderByTurma_Disciplina_MatrizCurricular_PesoAscHorario_IdDesc(
							cursoId, periodo, horarioInicialId, horarioFinalId);

		List<HorarioAula> listaRecomendacao = new ArrayList<>();

		for (HorarioAula horarioAula : materiasFaltantes)
			if (!disciplinasCursadas.contains(horarioAula.getTurma().getDisciplina())) {
				listaRecomendacao.add(horarioAula);
			}

		return listaRecomendacao;
	}

	private List<HorarioAula> recomendacaoPeriodoUnico(List<HorarioAula> lista, Integer cargaHorariaMaxima,
			Aluno aluno) {

		Integer numeroDeHorasMaximas = cargaHorariaMaxima / 15;

		List<HorarioAula> recomendacao = new ArrayList<>();
		List<Disciplina> disciplinas = new ArrayList<>();

		Set<Disciplina> disciplinasCursadas = aluno.getDisciplinasCursadas();

		for (HorarioAula horarioAula : lista)
			if (cargaHorariaMaxima >= 0 && !recomendacao.contains(horarioAula)
					&& !disciplinas.contains(horarioAula.getTurma().getDisciplina())
					&& horarioValido(recomendacao, horarioAula, numeroDeHorasMaximas)
					&& !faltaPreRequisito(recomendacao, horarioAula, disciplinasCursadas)) {

				recomendacao.addAll(this.listarHorarioAulaPorTurma(horarioAula.getTurma().getCodigo()));
				disciplinas.add(horarioAula.getTurma().getDisciplina());
				cargaHorariaMaxima -= horarioAula.getTurma().getDisciplina().getCargaHoraria();
			}

		/*
		 * if (!recomendacao.isEmpty() && cargaHorariaMaxima <= 0)
		 * recomendacao.remove(recomendacao.size() - 1);
		 */

		return ordenarListaDeRecomendacao(recomendacao);

	}

	private List<HorarioAula> recomendacaoPeriodoIntegral(List<HorarioAula> listaPrimeiroTurno,
			List<HorarioAula> listaSegundoTurno, Integer cargaHorariaMaxima, Aluno aluno) {

		Set<HorarioAula> setRecomendacao = this.horariosPossiveisPeriodoIntegral(listaPrimeiroTurno, listaSegundoTurno,
				cargaHorariaMaxima, aluno);

		return horariosEscolhidosPeriodoIntegral(setRecomendacao, cargaHorariaMaxima, aluno);

	}

	private Set<HorarioAula> horariosPossiveisPeriodoIntegral(List<HorarioAula> listaPrimeiroTurno,
			List<HorarioAula> listaSegundoTurno, Integer cargaHorariaMaxima, Aluno aluno) {

		Set<HorarioAula> setRecomendacao = new HashSet<>();
		listaPrimeiroTurno = this.recomendacaoPeriodoUnico(listaPrimeiroTurno, cargaHorariaMaxima, aluno);
		listaSegundoTurno = this.recomendacaoPeriodoUnico(listaSegundoTurno, cargaHorariaMaxima, aluno);

		setRecomendacao.addAll(listaPrimeiroTurno);
		setRecomendacao.addAll(listaSegundoTurno);

		return setRecomendacao;
	}

	private List<HorarioAula> horariosEscolhidosPeriodoIntegral(Set<HorarioAula> setRecomendacao,
			Integer cargaHorariaMaxima, Aluno aluno) {
		List<HorarioAula> listaRecomendacao = new ArrayList<>();
		List<Disciplina> disciplinas = new ArrayList<>();

		Set<Disciplina> disciplinasCursadas = aluno.getDisciplinasCursadas();

		Integer horasMaximas = cargaHorariaMaxima / 15;

		for (HorarioAula horarioAula : setRecomendacao)
			if (cargaHorariaMaxima >= 0 && !listaRecomendacao.contains(horarioAula)
					&& !disciplinas.contains(horarioAula.getTurma().getDisciplina())
					&& horarioValido(listaRecomendacao, horarioAula, horasMaximas)
					&& !faltaPreRequisito(listaRecomendacao, horarioAula, disciplinasCursadas)) {
				listaRecomendacao.addAll(this.listarHorarioAulaPorTurma(horarioAula.getTurma().getCodigo()));
				disciplinas.add(horarioAula.getTurma().getDisciplina());
				cargaHorariaMaxima -= horarioAula.getTurma().getDisciplina().getCargaHoraria();
			}

		/*
		 * if (!listaRecomendacao.isEmpty() && cargaHorariaMaxima <= 0)
		 * listaRecomendacao.remove(listaRecomendacao.size() - 1);
		 */

		return ordenarListaDeRecomendacao(listaRecomendacao);

	}

	private Boolean horarioValido(List<HorarioAula> recomendacao, HorarioAula disciplina, Integer horasMaximas) {

		if (recomendacao.isEmpty())
			return true;

		List<HorarioAula> listaParaValidar = this.listarHorarioAulaPorTurma(disciplina.getTurma().getCodigo());

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

	private List<HorarioAula> listarHorarioAulaPorTurma(String turma) {
		return horarioAulaRepository.findAllByTurma_Codigo(turma);
	}

	private List<HorarioAula> ordenarListaDeRecomendacao(List<HorarioAula> lista) {
		Collections.sort(lista, (o1, o2) -> Long.compare(o1.getId(), o2.getId()));
		Collections.sort(lista, (o1, o2) -> (o2.getTurma().getCodigo().compareTo(o1.getTurma().getCodigo())));
		Collections.sort(lista, (o1, o2) -> Long.compare(o1.getDia().getId(), o2.getDia().getId()));

		return lista;
	}

}
