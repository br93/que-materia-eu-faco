package br.com.quemateria.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaUsuarioDTO {
	
	private String usuario;
	private ConsultaPerfilDTO perfil;

}
