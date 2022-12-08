package br.com.quemateria.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private final ValidacaoService validacaoService;
	
	public final Logger logger = LoggerFactory.getLogger(CursoService.class);

	public Curso salvarCurso(Curso curso) {
		logger.info("Acessando método salvarCurso...");
		
		logger.info("Retornando curso " + curso.getNome() + " criado");
		return cursoRepository.save(curso);
	}

	public Page<Curso> listarCursos(Pageable pageable) {
		logger.info("Acessando método listarCursos...");

		logger.info("Listando todos os cursos cadastrados");
		return cursoRepository.findAll(pageable);
	}

	public Curso buscarCurso(Long id) {
		logger.info("Acessando método buscarCurso...");
		
		Optional<Curso> buscarPorId = cursoRepository.findById(id);
		
		if (!buscarPorId.isPresent())
			logger.info("Curso não encontrado");

		logger.info("Curso " + buscarPorId.get().getNome() + " encontrado");
		return buscarPorId.orElseThrow(() -> new CustomNotFoundException("Curso não encontrado"));
	}
	
	public Curso buscarCursoPorCodigo(String codigo) {
		logger.info("Acessando método buscarCursoPorCodigo...");
		
		Optional<Curso> buscarPorCodigo = cursoRepository.findByCodigo(codigo);
		
		if (!buscarPorCodigo.isPresent())
			logger.info("Curso não encontrado");

		logger.info("Curso " + buscarPorCodigo.get().getNome() + " encontrado");
		return buscarPorCodigo.orElseThrow(() -> new CustomNotFoundException("Curso não encontrado"));
	}
	
	public Curso atualizarCurso(Curso curso, Long id) {
		logger.info("Acessando método atualizarCurso...");
		
		Curso cursoOriginal = this.buscarCurso(id);
		curso.setId(cursoOriginal.getId());

		logger.info("Retornando curso " + curso.getNome() + " atualizado");
		return cursoRepository.save(curso);
	}

	public void excluirCurso(String codigo) {
		logger.info("Acessando método excluirCurso...");
		
		Curso curso = this.buscarCursoPorCodigo(codigo);
		
		if (curso != null)
			logger.info("Curso " + curso.getNome() + " deletado com sucesso");
		cursoRepository.delete(curso);
	}
	
	public void validarParametros(String codigo) {
		logger.info("Acessando método validarParametros...");
		
		validacaoService.validarCodigoCurso(codigo);
		
		logger.info("Parâmetros validados!");
	}

	public void validarParametros(String nome, String codigo, Integer periodo) {
		logger.info("Acessando método validarParametros...");
		
		validacaoService.validarNome(nome, "Nome do curso");
		validacaoService.validarCodigoCurso(codigo);
		validacaoService.validarPeriodo(periodo);
		
		logger.info("Parâmetros validados!");
	}

}
