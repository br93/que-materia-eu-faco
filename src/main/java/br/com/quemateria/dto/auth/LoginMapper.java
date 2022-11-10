package br.com.quemateria.dto.auth;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.quemateria.entities.Usuario;
import br.com.quemateria.entities.Perfil;

@Mapper(componentModel = "spring")
public interface LoginMapper {
	
	ConsultaUsuarioDTO toDTO(Usuario login);
	ConsultaPerfilDTO toDTO(Perfil perfil);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "ativo", ignore = true)
	@Mapping(target = "perfil", ignore = true)
	@Mapping(target = "authorities", ignore = true)
	Usuario toEntity(AutenticacaoDTO dto);
	

}
