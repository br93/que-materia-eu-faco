package br.com.quemateria.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import br.com.quemateria.entities.Disciplina;
import br.com.quemateria.entities.HorarioAula;
import br.com.quemateria.repositories.DisciplinaRepository;
import br.com.quemateria.repositories.HorarioAulaRepository;

@Service
public class RecomendacaoService {

	private final HorarioAulaRepository horarioAulaRepository;
	private final DisciplinaRepository disciplinaRepository;
	private final DisciplinaService disciplinaService;

	public RecomendacaoService(HorarioAulaRepository horarioAulaRepository, DisciplinaRepository disciplinaRepository,
			DisciplinaService disciplinaService) {
		this.horarioAulaRepository = horarioAulaRepository;
		this.disciplinaRepository = disciplinaRepository;
		this.disciplinaService = disciplinaService;
	}

	public List<HorarioAula> listarHorarioAulaPorTurma(String turma) {
		return horarioAulaRepository.findAllByDisciplina_Turma(turma);
	}

	public void calcularPeso() {
		List<Disciplina> disciplinas = disciplinaRepository.findAll();

		for (Disciplina disciplina : disciplinas)
			if (disciplina.getPeso() == 0.0) {
				disciplinaService.atualizarPeso((Math.log(disciplina.getPeriodo()) / Math.log(2))
						- (disciplina.getPreRequisito() / 2) - disciplina.getTipoDeDisciplina().getTipoValor(),
						disciplina.getId());
			}

	}

	public List<HorarioAula> recomendarMateriasPorHorarioAula(Set<Disciplina> disciplinasCursadas, Long cursoId,
			Integer identificador, String sigla) {

		Integer quantidadeDeDisciplinasCursadas = 0;

		List<HorarioAula> recomendacao = horarioAulaRepository
				.findAllByDisciplina_Curso_IdAndDia_IdentificadorAndHorario_SiglaOrderByDisciplina_PesoAscDisciplina_CargaHorariaDescDisciplina_PeriodoAsc(
						cursoId, identificador, sigla);
		List<HorarioAula> listaRemocao = new ArrayList<>();

		for (HorarioAula horarioAula : recomendacao)
			if (disciplinasCursadas.contains(horarioAula.getDisciplina())) {
				listaRemocao.add(horarioAula);
				quantidadeDeDisciplinasCursadas++;
			}

		recomendacao.removeAll(listaRemocao);

		if (recomendacao.size() < 3)
			return recomendacao.subList(0, recomendacao.size());

		return recomendacao.subList(0, 3);

	}

	public List<HorarioAula> recomendarMateriasPorTurno(Set<Disciplina> disciplinasCursadas, Long cursoId,
			Integer periodo, Long horarioInicialId, Long horarioFinalId) {

		List<HorarioAula> recomendacao = horarioAulaRepository
				.findAllByDisciplina_Curso_IdAndDisciplina_PeriodoLessThanAndHorario_IdBetweenOrderByDisciplina_PesoAscDisciplina_CargaHorariaDescDisciplina_PeriodoAsc(
						cursoId, periodo, horarioInicialId, horarioFinalId);

		List<HorarioAula> listaRemocao = new ArrayList<>();

		for (HorarioAula horarioAula : recomendacao)
			if (disciplinasCursadas.contains(horarioAula.getDisciplina())) {
				listaRemocao.add(horarioAula);
			}

		recomendacao.removeAll(listaRemocao);

		return recomendacao;
	}

	public List<HorarioAula> recomendacaoCompleta(Set<Disciplina> disciplinasCursadas, Long cursoId, Integer periodo,
			Boolean manha, Boolean tarde, Boolean noite, Integer cargaHorariaMaxima) {

		List<HorarioAula> recomendacaoManha = this.recomendarMateriasPorTurno(disciplinasCursadas, cursoId, periodo, 1L,
				6L);
		List<HorarioAula> recomendacaoTarde = this.recomendarMateriasPorTurno(disciplinasCursadas, cursoId, periodo, 7L,
				12L);
		List<HorarioAula> recomendacaoNoite = this.recomendarMateriasPorTurno(disciplinasCursadas, cursoId, periodo,
				13L, 17L);

		cargaHorariaMaxima *= 15;

		if (manha && !tarde && !noite)
			return this.recomendacaoPeriodoUnico(recomendacaoManha, cargaHorariaMaxima);
		if (!manha && tarde && !noite)
			return this.recomendacaoPeriodoUnico(recomendacaoTarde, cargaHorariaMaxima);
		if (!manha && !tarde && noite)
			return this.recomendacaoPeriodoUnico(recomendacaoNoite, cargaHorariaMaxima);
		if (manha && tarde && !noite)
			return this.recomendacaoPeriodoIntegral(recomendacaoManha, recomendacaoTarde, cargaHorariaMaxima);
		if (!manha && tarde && noite)
			return this.recomendacaoPeriodoIntegral(recomendacaoTarde, recomendacaoNoite, cargaHorariaMaxima);
		return new ArrayList<>();

	}

	public List<HorarioAula> recomendacaoPeriodoUnico(List<HorarioAula> lista, Integer cargaHorariaMaxima) {

		List<HorarioAula> recomendacao = new ArrayList<>();
		List<String> disciplinas = new ArrayList<>();
		// List<String> horarios = new ArrayList<>();

		for (HorarioAula horarioAula : lista)
			if (cargaHorariaMaxima >= 0 && !recomendacao.contains(horarioAula) && !disciplinas
					.contains(horarioAula.getDisciplina().getNome()) && horarioDisponivel(recomendacao, horarioAula)) {

				recomendacao.addAll(this.listarHorarioAulaPorTurma(horarioAula.getDisciplina().getTurma()));
				disciplinas.add(horarioAula.getDisciplina().getNome());
				cargaHorariaMaxima -= horarioAula.getDisciplina().getCargaHoraria();
			}

		recomendacao.remove(recomendacao.size() - 1);
		return recomendacao;

	}

	// pensar na logica = horarios estao repetindo por causa do addAll do loop anterior
	public boolean horarioDisponivel(List<HorarioAula> recomendacao, HorarioAula disciplina) {

		if (recomendacao.isEmpty())
			return true;

		Boolean valido = true;

		for (HorarioAula horarioAula : recomendacao)
			if (horarioAula.getDia() == disciplina.getDia() && horarioAula.getHorario() == disciplina.getHorario())
				valido = false;

		return valido;
	}

	public List<HorarioAula> recomendacaoPeriodoIntegral(List<HorarioAula> listaPrimeiroTurno,
			List<HorarioAula> listaSegundoTurno, Integer cargaHorariaMaxima) {

		Set<HorarioAula> setRecomendacao = new HashSet<>();
		listaPrimeiroTurno = this.recomendacaoPeriodoUnico(listaPrimeiroTurno, cargaHorariaMaxima);
		listaSegundoTurno = this.recomendacaoPeriodoUnico(listaSegundoTurno, cargaHorariaMaxima);

		setRecomendacao.addAll(listaPrimeiroTurno);
		setRecomendacao.addAll(listaSegundoTurno);

		List<HorarioAula> listaRecomendacao = new ArrayList<>();
		List<String> disciplinas = new ArrayList<>();

		for (HorarioAula horarioAula : setRecomendacao)
			if (cargaHorariaMaxima >= 0 && !listaRecomendacao.contains(horarioAula)
					&& !disciplinas.contains(horarioAula.getDisciplina().getNome())) {
				listaRecomendacao.addAll(this.listarHorarioAulaPorTurma(horarioAula.getDisciplina().getTurma()));
				disciplinas.add(horarioAula.getDisciplina().getNome());
				cargaHorariaMaxima -= horarioAula.getDisciplina().getCargaHoraria();
			}

		listaRecomendacao.remove(listaRecomendacao.size() - 1);
		return listaRecomendacao;

	}

}
