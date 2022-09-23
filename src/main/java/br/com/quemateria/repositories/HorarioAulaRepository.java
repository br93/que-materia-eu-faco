package br.com.quemateria.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.quemateria.entities.HorarioAula;

@Repository
public interface HorarioAulaRepository extends JpaRepository<HorarioAula, Long> {

	List<HorarioAula> findAllByTurma_Disciplina_Curso_IdAndDia_IdentificadorAndHorario_SiglaOrderByTurma_Disciplina_PesoAscTurma_Disciplina_CargaHorariaDescTurma_Disciplina_PeriodoAsc(
			Long cursoId, Integer identificador, String sigla);

	List<HorarioAula> findAllByTurma_Disciplina_Curso_IdAndTurma_Disciplina_PeriodoLessThanAndHorario_IdBetweenOrderByTurma_Disciplina_PesoAscHorario_IdAsc(
			Long cursoId, Integer periodo, Long horarioInicioId, Long horarioFimId);
	
	List<HorarioAula> findAllByTurma_Disciplina_Curso_IdAndTurma_Disciplina_PeriodoLessThanAndHorario_IdBetweenOrderByTurma_Disciplina_PesoAscHorario_IdDesc(
			Long cursoId, Integer periodo, Long horarioInicioId, Long horarioFimId);

	List<HorarioAula> findAllByTurma_Codigo(String codigo);

}
