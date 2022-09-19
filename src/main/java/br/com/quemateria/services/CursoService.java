package br.com.quemateria.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.quemateria.entities.Curso;
import br.com.quemateria.repositories.CursoRepository;

@Service
public class CursoService {

	private final CursoRepository cursoRepository;

	public CursoService(CursoRepository cursoRepository) {
		this.cursoRepository = cursoRepository;
	}

	public Curso salvarCurso(Curso curso) {
		return cursoRepository.save(curso);
	}

	public Page<Curso> listarCursos(Pageable pageable) {
		return cursoRepository.findAll(pageable);
	}

	public Curso buscarCurso(Long id) {
		Optional<Curso> buscarPorId = cursoRepository.findById(id);

		return buscarPorId.orElseThrow(() -> new EntityNotFoundException("Curso não encontrado"));
	}
	
	public Curso buscarCursoPorMatriz(String matriz) {
		Optional<Curso> buscarPorMatriz = cursoRepository.findByMatriz(matriz);

		return buscarPorMatriz.orElseThrow(() -> new EntityNotFoundException("Curso não encontrado"));
	}

	public Curso atualizarCurso(Curso curso, Long id) {
		Curso cursoOriginal = this.buscarCurso(id);
		curso.setId(cursoOriginal.getId());

		return cursoRepository.save(curso);
	}

	public void excluirCurso(Long id) {
		Curso curso = this.buscarCurso(id);
		cursoRepository.delete(curso);
	}

}
