package br.com.quemateria.controllers.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quemateria.dto.auth.AutenticacaoDTO;
import br.com.quemateria.dto.auth.TokenDTO;
import br.com.quemateria.services.auth.AutenticacaoService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("v1/auth")
@AllArgsConstructor
public class AutenticacaoController {

	private final AutenticacaoService autenticacaoService;

	@PostMapping
	public ResponseEntity<TokenDTO> autenticar(@RequestBody AutenticacaoDTO dto){
		
		try {
			return new ResponseEntity<TokenDTO>(autenticacaoService.autenticar(dto), HttpStatus.OK);
			
		} catch(AuthenticationException ex) {
			return new ResponseEntity<TokenDTO>(HttpStatus.UNAUTHORIZED);
		}
		
	}
}
