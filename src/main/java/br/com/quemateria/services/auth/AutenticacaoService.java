package br.com.quemateria.services.auth;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.quemateria.dto.auth.AutenticacaoDTO;
import br.com.quemateria.dto.auth.TokenDTO;
import br.com.quemateria.entities.Usuario;

@Service
public class AutenticacaoService {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private AuthenticationManager authManager;
	
	public TokenDTO autenticar(AutenticacaoDTO dto) throws AuthenticationException {
		Authentication authenticate = authManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsuario(), dto.getSenha()));
		String token = gerarToken(authenticate);
		
		return new TokenDTO(token);
	}
	
	public boolean tokenValido(String token) {
		if (token == null)
			return false;
		
		try {
			verifyToken(token);
		} catch (JWTVerificationException exception) {
			return false;
		}
		
		return true;
	}
	
	public Long retornarUsuarioId(String token) {
		String subject = verifyToken(token).getSubject();
		return Long.parseLong(subject);
	}
	
	private String gerarToken(Authentication authenticate) {
		
		String expiration = env.getProperty("materias-api.jwt.expiration");
		String issuer = env.getProperty("materias-api.jwt.issuer");
		
		Usuario principal = (Usuario) authenticate.getPrincipal();
		Date hoje = new Date();
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
		Algorithm algoritmo = criarAlgoritmo();
		
		return JWT.create().withIssuer(issuer).withExpiresAt(dataExpiracao).withSubject(principal.getId().toString()).sign(algoritmo);
		
	}
	
	private Algorithm criarAlgoritmo() {
		String secret = env.getProperty("materias-api.jwt.secret");
		return Algorithm.HMAC256(secret);
	}
	
	private DecodedJWT verifyToken(String token) {
		String issuer = env.getProperty("materias-api.jwt.issuer");
		return JWT.require(this.criarAlgoritmo()).withIssuer(issuer).build().verify(token);
	}
	

}
