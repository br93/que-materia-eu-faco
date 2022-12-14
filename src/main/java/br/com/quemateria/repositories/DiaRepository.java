package br.com.quemateria.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.quemateria.entities.Dia;

@Repository
public interface DiaRepository extends JpaRepository<Dia, Long> {
	
	Optional<Dia> findByIdentificador(Integer identificador);

}
