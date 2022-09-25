package br.com.quemateria.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.quemateria.entities.Disciplina;
import br.com.quemateria.repositories.DisciplinaRepository;

@Service
public class DisciplinaService {

	private final DisciplinaRepository disciplinaRepository;

	public DisciplinaService(DisciplinaRepository disciplinaRepository) {
		this.disciplinaRepository = disciplinaRepository;
	}

	public Disciplina salvarDisciplina(Disciplina disciplina) {
		return disciplinaRepository.save(disciplina);
	}

	public Page<Disciplina> listarDisciplinas(Pageable pageable) {
		return disciplinaRepository.findAll(pageable);
	}

	public Disciplina buscarDisciplina(Long id) {
		Optional<Disciplina> buscarPorId = disciplinaRepository.findById(id);

		return buscarPorId.orElseThrow(() -> new EntityNotFoundException("Disciplina não encontrada"));
	}

	public Disciplina buscarDisciplinaPorCodigo(String codigo) {
		Optional<Disciplina> buscarPorTurma = disciplinaRepository.findByCodigo(codigo);

		return buscarPorTurma.orElseThrow(() -> new EntityNotFoundException("Disciplina não encontrada"));
	}

	public Disciplina atualizarDisciplina(Disciplina disciplina, Long id) {
		Disciplina disciplinaOriginal = this.buscarDisciplina(id);
		disciplina.setId(disciplinaOriginal.getId());

		return disciplinaRepository.save(disciplina);
	}
	
	public void excluirDisciplina(Long id) {
		Disciplina disciplina = this.buscarDisciplina(id);
		disciplinaRepository.delete(disciplina);
	}

	
	
	
	

}
