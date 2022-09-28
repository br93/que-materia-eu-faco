package br.com.quemateria.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.quemateria.entities.HorarioAula;

@Repository
public interface HorarioAulaRepository extends JpaRepository<HorarioAula, Long> {

	/*List<HorarioAula> findAllByTurma_Disciplina_MatrizCurricular_Curso_IdAndDia_IdentificadorAndHorario_SiglaOrderByTurma_Disciplina_MatrizCurricular_PesoAscTurma_Disciplina_CargaHorariaDescTurma_Disciplina_MatrizCurricular_PeriodoAsc(
			Long cursoId, Integer identificador, String sigla);*/

	List<HorarioAula> findAllByTurma_Disciplina_MatrizCurricular_Curso_IdAndTurma_Disciplina_MatrizCurricular_PeriodoLessThanAndHorario_IdBetweenOrderByTurma_Disciplina_MatrizCurricular_PesoAscHorario_IdAsc(
			Long cursoId, Integer periodo, Long horarioInicioId, Long horarioFimId);

	List<HorarioAula> findAllByTurma_Disciplina_MatrizCurricular_Curso_IdAndTurma_Disciplina_MatrizCurricular_PeriodoLessThanAndHorario_IdBetweenOrderByTurma_Disciplina_MatrizCurricular_PesoAscHorario_IdDesc(
			Long cursoId, Integer periodo, Long horarioInicioId, Long horarioFimId);

	List<HorarioAula> findAllByTurma_Codigo(String codigo);
	
	List<HorarioAula> findAllByTurma_Disciplina_MatrizCurricular_Curso_IdAndTurma_Disciplina_MatrizCurricular_TipoDeDisciplina_IdAndDia_IdentificadorAndHorario_Sigla(Long cursoId, Long tipoId, Integer dia, String horario);
	
	List<HorarioAula> findAllByDia_IdentificadorAndHorario_Sigla(Integer dia, String horario);
	
	List<HorarioAula> findAllByTurma_Disciplina_MatrizCurricular_CursoId(Long cursoId);
	
	List<HorarioAula> findAllByTurma_Disciplina_MatrizCurricular_CursoIdNotAndDia_IdentificadorAndHorario_Sigla(Long cursoId, Integer dia, String horario);

}
