package br.com.quemateria.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.quemateria.entities.Aluno;
import br.com.quemateria.repositories.AlunoRepository;

@Service
public class AlunoService {
	
	private final AlunoRepository alunoRepository;
	
	public AlunoService(AlunoRepository alunoRepository) {
		this.alunoRepository = alunoRepository;
	}
	
	public Aluno salvarAluno (Aluno aluno) {
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
		
		return buscarPorId.orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));
	}
	
	public Aluno buscarAlunoPorRegistro(String registro) {
		Optional<Aluno> buscarPorRegistro = alunoRepository.findByRegistro(registro);
		
		return buscarPorRegistro.orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));
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
	
	public Aluno getUltimoAlunoCadastrado() {
		List<Aluno> listaAlunos = alunoRepository.findAll();
		return listaAlunos.get(listaAlunos.size() - 1);
	}
	
	
	

}
