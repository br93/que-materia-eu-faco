package br.com.quemateria.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.quemateria.entities.Curso;
import br.com.quemateria.exceptions.CustomNotFoundException;
import br.com.quemateria.repositories.CursoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CursoService {

	private final CursoRepository cursoRepository;

	public Curso salvarCurso(Curso curso) {
		return cursoRepository.save(curso);
	}

	public Page<Curso> listarCursos(Pageable pageable) {
		return cursoRepository.findAll(pageable);
	}

	public Curso buscarCurso(Long id) {
		Optional<Curso> buscarPorId = cursoRepository.findById(id);

		return buscarPorId.orElseThrow(() -> new CustomNotFoundException("Curso não encontrado"));
	}
	
	public Curso buscarCursoPorCodigo(String codigo) {
		Optional<Curso> buscarPorCodigo = cursoRepository.findByCodigo(codigo);

		return buscarPorCodigo.orElseThrow(() -> new CustomNotFoundException("Curso não encontrado"));
	}
	
	public Curso atualizarCurso(Curso curso, Long id) {
		Curso cursoOriginal = this.buscarCurso(id);
		curso.setId(cursoOriginal.getId());

		return cursoRepository.save(curso);
	}

	public void excluirCurso(String codigo) {
		Curso curso = this.buscarCursoPorCodigo(codigo);
		cursoRepository.delete(curso);
	}

}
