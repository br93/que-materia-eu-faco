package br.com.quemateria.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.quemateria.entities.Disciplina;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
	
	Optional<Disciplina> findByCodigo(String codigo);
	Set<Disciplina> findByCurso_IdAndTipoDeDisciplina_TipoValor(Long id, Integer valor);

}
