package br.com.quemateria.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.quemateria.filter.FiltroAutenticacao;
import br.com.quemateria.services.auth.AutenticacaoService;
import br.com.quemateria.services.auth.UsuarioService;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

	private AutenticacaoService autenticacaoService;
	private UsuarioService usuarioService;
	
	public WebSecurityConfig(AutenticacaoService autenticacaoService, @Lazy UsuarioService usuarioService) {
		this.autenticacaoService = autenticacaoService;
		this.usuarioService = usuarioService;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**", "/swagger-ui/**");
	}
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/v1/auth", "/v1/usuarios").permitAll()
			.anyRequest().authenticated()

			.and()
				.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

			.and()
				.addFilterBefore(new FiltroAutenticacao(autenticacaoService, usuarioService),
						UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

}
