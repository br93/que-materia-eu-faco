package br.com.quemateria.services.auth;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.quemateria.entities.Perfil;
import br.com.quemateria.entities.Usuario;
import br.com.quemateria.enums.PerfilType;
import br.com.quemateria.exceptions.CustomBadRequestException;
import br.com.quemateria.exceptions.CustomNotFoundException;
import br.com.quemateria.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService implements UserDetailsService {

	private final UsuarioRepository loginRepository;
	private final PerfilService perfilService;
	private final PasswordEncoder passwordEncoder;

	public final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> optional = loginRepository.findByUsuario(username);

		return optional.orElseThrow(() -> new CustomNotFoundException("Usuário não encontrado"));
	}

	public Page<Usuario> listarUsuarios(Pageable pageable) {
		logger.info("Acessando método listarUsuarios...");

		logger.info("Listando todos os usuarios cadastrados");
		return loginRepository.findAll(pageable);
	}

	public Usuario salvarUsuario(Usuario login) {
		logger.info("Acessando método salvarUsuario...");

		logger.info("Ativando usuário...");
		login.setAtivo(true);

		logger.info("Criando hash da senha...");
		login.setSenha(passwordEncoder.encode(login.getSenha()));

		logger.info("Definindo perfil de usuário...");
		Perfil perfilUsuario = perfilService.buscarPerfil(PerfilType.USUARIO.toString());
		login.setPerfil(perfilUsuario);

		logger.info("Usuário cadastrado com sucesso!");
		return loginRepository.save(login);
	}

	public Usuario buscarUsuario(Long id) {
		logger.info("Acessando método buscarUsuario...");

		Optional<Usuario> buscarPorId = loginRepository.findById(id);

		if (!buscarPorId.isPresent())
			logger.info("Usuário não encontrado");

		logger.info("Usuário " + buscarPorId.get().getUsername() + " encontrado");
		return buscarPorId.orElseThrow(() -> new CustomBadRequestException("Usuário não encontrado!"));
	}

	public Usuario buscarUsuario(String nome) {
		logger.info("Acessando método buscarUsuario...");

		Optional<Usuario> buscarPorNome = loginRepository.findByUsuario(nome);

		if (!buscarPorNome.isPresent())
			logger.info("Usuário não encontrado");

		logger.info("Usuário " + buscarPorNome.get().getUsername() + " encontrado");
		return buscarPorNome.orElseThrow(() -> new CustomBadRequestException("Usuário não encontrado!"));
	}

	public Usuario atualizarUsuario(Usuario login, Long id) {
		logger.info("Acessando método atualizarUsuario...");
		logger.info("Validando se o novo username é único...");
		isUsuarioUnico(login.getUsername());

		Usuario loginOriginal = buscarUsuario(id);

		login.setId(loginOriginal.getId());
		login.setPerfil(loginOriginal.getPerfil());
		login.setAtivo(loginOriginal.isAtivo());
		login.setSenha(passwordEncoder.encode(login.getSenha()));

		logger.info("Retornando usuario " + login.getUsername() + " atualizado");
		return loginRepository.save(login);
	}

	public Usuario criarCadastroAluno(Long id) {
		logger.info("Acessando método criarCadastroAluno...");
		
		Usuario login = this.buscarUsuario(id);
		
		logger.info("Definindo perfil de usuario " + login.getUsername() + " como aluno...");
		Perfil perfilAluno = perfilService.buscarPerfil(PerfilType.ALUNO.toString());

		login.setPerfil(perfilAluno);
		logger.info("Retornando usuario " + login.getUsername() + " atualizado com perfil "
				+ login.getPerfil().getPermissao());
		return loginRepository.save(login);
	}

	public Usuario promoverUsuario(Long id) {
		logger.info("Acessando método promoverUsuario...");
		Usuario login = this.buscarUsuario(id);
		logger.info("Definindo perfil de usuario " + login.getUsername() + " como administrador...");
		Perfil perfilAdmin = perfilService.buscarPerfil(PerfilType.ADMIN.toString());

		login.setPerfil(perfilAdmin);
		logger.info("Retornando usuario " + login.getUsername() + " atualizado com perfil "
				+ login.getPerfil().getPermissao());
		return loginRepository.save(login);
	}

	public void excluirUsuario(Long id) {
		logger.info("Acessando método excluirAluno...");

		Usuario login = this.buscarUsuario(id);
		
		if (login != null)
			logger.info("Usuario " + login.getUsername() + " deletado com sucesso");
		
		loginRepository.delete(login);
	}

	private Boolean isUsuarioUnico(String usuario) {

		try {
			this.loadUserByUsername(usuario);
		} catch (Exception ex) {
			logger.info("Username validado");
			return true;
		}

		logger.info("Username invalido: Usuário já existe!");
		throw new CustomBadRequestException("Usuário já existe");

	}

}