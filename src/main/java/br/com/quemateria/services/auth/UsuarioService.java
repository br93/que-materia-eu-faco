package br.com.quemateria.services.auth;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.quemateria.entities.Usuario;
import br.com.quemateria.entities.Perfil;
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> optional = loginRepository.findByUsuario(username);

		return optional.orElseThrow(() -> new CustomNotFoundException("Usuário não encontrado"));
	}
	
	public Page<Usuario> listarUsuarios(Pageable pageable) {
		return loginRepository.findAll(pageable);
	}
	
	public Usuario salvarUsuario (Usuario login) {
		login.setAtivo(true);
        login.setSenha(passwordEncoder.encode(login.getSenha()));
        
        Perfil perfilUsuario = perfilService.buscarPerfil(PerfilType.USUARIO.toString());
        login.setPerfil(perfilUsuario);
        
        return loginRepository.save(login);
	}
	
	public Usuario buscarUsuario(Long id) {
        return loginRepository.findById(id).orElseThrow(() -> new CustomBadRequestException("Usuário não encontrado!"));
    }
	
	public Usuario atualizarUsuario(Usuario login, Long id) {
        Usuario loginOriginal = buscarUsuario(id);

        if (!loginOriginal.getUsuario().equals(login.getUsuario())) {
            usuarioExiste(login.getUsuario());
        }

        login.setId(loginOriginal.getId());
        login.setPerfil(loginOriginal.getPerfil());
        login.setAtivo(loginOriginal.isAtivo());
        login.setSenha(passwordEncoder.encode(login.getSenha()));
        return loginRepository.save(login);
    }
	
	public Usuario criarCadastroAluno(Long id) {
		Usuario login = this.buscarUsuario(id);
		Perfil perfilAluno = perfilService.buscarPerfil(PerfilType.ALUNO.toString());
		
		login.setPerfil(perfilAluno);
		return loginRepository.save(login);
	}
	
	public Usuario promoverUsuario(Long id) {
		Usuario login = this.buscarUsuario(id);
		Perfil perfilAdmin = perfilService.buscarPerfil(PerfilType.ADMIN.toString());
		
		login.setPerfil(perfilAdmin);
		
		return loginRepository.save(login);
	}

    public void atualizarAtivo(Long id, boolean ativo) {
       Usuario login = this.buscarUsuario(id);
       login.setAtivo(ativo);
       loginRepository.save(login);
    }

    public void excluirUsuario(Long id) {
        Usuario login = this.buscarUsuario(id);
        loginRepository.delete(login);
    }

    private void usuarioExiste(String usuario) {
        if (loadUserByUsername(usuario) != null) {
            throw new CustomBadRequestException("Usuário já existe");
        }
    }

}
