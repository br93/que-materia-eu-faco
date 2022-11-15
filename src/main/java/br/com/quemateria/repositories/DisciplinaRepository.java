package br.com.quemateria.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.quemateria.entities.Disciplina;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
	
	Page<Disciplina> findAll(Pageable pageable);
	Optional<Disciplina> findByCodigo(String codigo);
	
	List<Disciplina> findByMatrizCurricular_Curso_IdAndMatrizCurricular_TipoDeDisciplina_IdOrderByMatrizCurricular_PeriodoAsc(Long cursoId, Long tipoId);
	
	

}
