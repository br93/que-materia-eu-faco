package br.com.quemateria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.quemateria.entities.Aluno;
import br.com.quemateria.entities.Usuario;
import br.com.quemateria.exceptions.CustomNotFoundException;
import br.com.quemateria.repositories.AlunoRepository;
import br.com.quemateria.services.auth.UsuarioService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AlunoService {
	
	private final AlunoRepository alunoRepository;
	private final UsuarioService loginService;
	
	public Aluno salvarAluno (Aluno aluno, Long id) {
		Usuario loginAluno = loginService.criarCadastroAluno(id);
		aluno.setLogin(loginAluno);
		return alunoRepository.save(aluno);
	}
	
	public Page<Aluno> listarAlunos(Pageable pageable){
		return alunoRepository.findAll(pageable);
	}
	
	public List<Aluno> listarAlunos(){
		return alunoRepository.findAll();
	}
	
	public Aluno buscarAluno(Long id) {
		Optional<Aluno> buscarPorId = alunoRepository.findById(id);
		
		return buscarPorId.orElseThrow(() -> new CustomNotFoundException("Aluno não encontrado"));
	}
	
	public Aluno buscarAlunoPorRegistro(String registro) {
		Optional<Aluno> buscarPorRegistro = alunoRepository.findByRegistro(registro);
		
		return buscarPorRegistro.orElseThrow(() -> new CustomNotFoundException("Aluno não encontrado"));
	}
	
	public Aluno atualizarAluno(Aluno aluno, Long id) {
		Aluno alunoOriginal = this.buscarAluno(id);
		aluno.setId(alunoOriginal.getId());
		
		return alunoRepository.save(aluno);
	}
	
	public void excluirAluno(String registro) {
		Aluno aluno = this.buscarAlunoPorRegistro(registro);
		alunoRepository.delete(aluno);
	}
	
	public Aluno getUsuario() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario login = (Usuario) authentication.getPrincipal();
		return this.buscarAluno(login.getId());
	}
	
}
