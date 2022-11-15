package br.com.quemateria.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.quemateria.filter.FiltroAutenticacao;
import br.com.quemateria.services.auth.AutenticacaoService;
import br.com.quemateria.services.auth.UsuarioService;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

	@Autowired
	@Lazy
	private AutenticacaoService autenticacaoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/v1/auth", "/v1/usuarios").permitAll()
			.antMatchers("/v2/api-docs", "/swagger-resources/**", "/swagger-ui/**").permitAll()
			.anyRequest().authenticated()

			.and()
				.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

			.and()
				.addFilterBefore(new FiltroAutenticacao(autenticacaoService, usuarioService),
						UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

}
