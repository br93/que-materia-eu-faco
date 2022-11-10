package br.com.quemateria.services.auth;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.quemateria.entities.Perfil;
import br.com.quemateria.exceptions.CustomNotFoundException;
import br.com.quemateria.repositories.PerfilRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PerfilService {

	private final PerfilRepository perfilRepository;

	public Perfil buscarPerfil(String permissao) {
		Optional<Perfil> optional = perfilRepository.findByPermissao(permissao);
		
		return optional.orElseThrow(() -> new CustomNotFoundException("Perfil não encontrado"));
	}

}
