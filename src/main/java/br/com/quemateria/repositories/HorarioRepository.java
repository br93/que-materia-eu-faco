package br.com.quemateria.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.quemateria.entities.Horario;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {
	
	Optional<Horario> findBySigla (String sigla);

}
