package br.com.quemateria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.quemateria.entities.TipoDeDisciplina;

@Repository
public interface TipoDeDisciplinaRepository extends JpaRepository<TipoDeDisciplina, Long> {

}
