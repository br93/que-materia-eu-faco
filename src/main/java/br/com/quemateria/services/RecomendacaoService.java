package br.com.quemateria.services;

import java.util.List;

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

	public void calcularPeso() {
		List<Disciplina> disciplinas = disciplinaRepository.findAll();

		for (Disciplina disciplina : disciplinas)
			if (disciplina.getPeso() == 0.0) {
				disciplinaService.atualizarPeso((Math.log(disciplina.getPeriodo()) / Math.log(2))
						- (disciplina.getPreRequisito() / 2) - disciplina.getTipoDeDisciplina().getTipoValor(),
						disciplina.getId());
			}

	}

	public List<HorarioAula> recomendarMateriasPorHorarioAula(Long cursoId, Integer identificador, String sigla) {
		return horarioAulaRepository
				.findAllByDisciplina_Curso_IdAndDia_IdentificadorAndHorario_SiglaOrderByDisciplina_PesoAscDisciplina_CargaHorariaDescDisciplina_PeriodoAsc(
						cursoId, identificador, sigla);
	}

}
