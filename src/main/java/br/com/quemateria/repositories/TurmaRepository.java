package br.com.quemateria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.quemateria.entities.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {

}
