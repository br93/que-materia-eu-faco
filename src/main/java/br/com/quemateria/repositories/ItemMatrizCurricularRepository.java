package br.com.quemateria.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.quemateria.entities.ItemMatrizCurricular;

public interface ItemMatrizCurricularRepository extends JpaRepository<ItemMatrizCurricular, Long> {
	List<ItemMatrizCurricular> findByCurso_Id(Long cursoId);
	List<ItemMatrizCurricular> findByDisciplina_Id(Long disciplinaId);
}
