package br.com.quemateria.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.quemateria.entities.HorarioAula;

@Repository
public interface HorarioAulaRepository extends JpaRepository<HorarioAula, Long> {

	/*List<HorarioAula> findTop5ByDisciplina_Curso_IdAndDia_IdentificadorAndHorario_SiglaOrderByDisciplina_PesoAscDisciplina_CargaHorariaDescDisciplina_PeriodoAsc(
			Long cursoId, Integer identificador, String sigla);*/
	
	List<HorarioAula> findAllByDisciplina_Curso_IdAndDia_IdentificadorAndHorario_SiglaOrderByDisciplina_PesoAscDisciplina_CargaHorariaDescDisciplina_PeriodoAsc(
			Long cursoId, Integer identificador, String sigla);

}
