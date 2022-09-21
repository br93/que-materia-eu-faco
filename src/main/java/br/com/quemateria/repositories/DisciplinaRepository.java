package br.com.quemateria.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.quemateria.entities.Disciplina;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
	
	Optional<Disciplina> findByTurma(String turma);
	//List<Disciplina> findByCurso_IdAndTipoDeDisciplina_IdOrderByPesoAscCargaHorariaDescPeriodoAsc(Long cursoId, Long tipoId);
	List<Disciplina> findByCurso_IdAndTipoDeDisciplina_IdOrderByPeriodoAsc(Long cursoId, Long tipoId);
	
	

}
