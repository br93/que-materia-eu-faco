package br.com.quemateria.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.quemateria.entities.Usuario;
import br.com.quemateria.services.auth.AutenticacaoService;
import br.com.quemateria.services.auth.UsuarioService;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class FiltroAutenticacao extends OncePerRequestFilter {

	private final AutenticacaoService autenticacaoService;
	private final UsuarioService usuarioService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = recuperarToken(request);

		if (autenticacaoService.tokenValido(token)) {
			Long usuarioId = autenticacaoService.retornarUsuarioId(token);
			Usuario usuario = usuarioService.buscarUsuario(usuarioId);

			SecurityContextHolder.getContext().setAuthentication(
					new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities()));
		}

		filterChain.doFilter(request, response);

	}
	
	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
	       if (token == null || token.isEmpty()){
	           return null;
	       }
	     return token.substring(7, token.length());
	}

}
