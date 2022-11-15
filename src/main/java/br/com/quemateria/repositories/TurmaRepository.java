package br.com.quemateria.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.quemateria.entities.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
	
	Page<Turma> findAll(Pageable pageable);
	Optional<Turma> findByCodigo(String codigo);

}
