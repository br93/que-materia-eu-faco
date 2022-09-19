package br.com.quemateria.services;

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
	
	public Aluno buscarAluno(Long id) {
		Optional<Aluno> buscarPorId = alunoRepository.findById(id);
		
		return buscarPorId.orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));
	}
	
	public Aluno atualizarAluno(Aluno aluno, Long id) {
		Aluno alunoOriginal = this.buscarAluno(id);
		aluno.setId(alunoOriginal.getId());
		
		return alunoRepository.save(aluno);
	}
	
	public void excluirAluno(Long id) {
		Aluno aluno = this.buscarAluno(id);
		alunoRepository.delete(aluno);
	}

}
