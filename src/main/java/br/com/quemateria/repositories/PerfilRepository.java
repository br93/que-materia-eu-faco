package br.com.quemateria.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.quemateria.entities.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
	
	Optional<Perfil> findByPermissao(String permissao);

}
