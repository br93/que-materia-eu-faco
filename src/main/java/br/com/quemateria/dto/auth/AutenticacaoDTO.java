package br.com.quemateria.dto.auth;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AutenticacaoDTO {
	
	private String usuario;
	private String senha;

}
