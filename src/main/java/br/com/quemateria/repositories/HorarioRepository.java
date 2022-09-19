package br.com.quemateria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.quemateria.entities.Horario;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {

}
