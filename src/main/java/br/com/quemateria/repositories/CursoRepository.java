package br.com.quemateria.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.quemateria.entities.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
	
	Optional<Curso> findByCodigo(String codigo);
	

}
