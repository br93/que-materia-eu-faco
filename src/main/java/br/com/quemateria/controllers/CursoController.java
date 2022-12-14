package br.com.quemateria.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.quemateria.dto.curso.ConsultaCursoDTO;
import br.com.quemateria.dto.curso.CursoMapper;
import br.com.quemateria.dto.curso.RegistroCursoDTO;
import br.com.quemateria.entities.Curso;
import br.com.quemateria.services.CursoService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("v1/cursos")
@AllArgsConstructor
public class CursoController {

	private final CursoService cursoService;
	private final CursoMapper cursoMapper;

	@GetMapping
	public ResponseEntity<ConsultaCursoDTO> buscarCurso(@RequestParam String codigo) {
		cursoService.validarParametros(codigo);
		return new ResponseEntity<>(cursoMapper.toDTO(cursoService.buscarCursoPorCodigo(codigo)), HttpStatus.OK);
	}

	@GetMapping("list")
	public ResponseEntity<Page<ConsultaCursoDTO>> listarCursos(@PageableDefault Pageable pageable,
			@RequestParam(required = false) Integer page) {
		return new ResponseEntity<>(cursoService.listarCursos(pageable).map(cursoMapper::toDTO),
				HttpStatus.PARTIAL_CONTENT);
	}

	@PostMapping("add")
	public ResponseEntity<ConsultaCursoDTO> salvarCurso(@RequestBody RegistroCursoDTO dto) {

		cursoService.validarParametros(dto.getNome(), dto.getCodigo(), dto.getQuantidadeDePeriodos());
		Curso curso = cursoService.salvarCurso(cursoMapper.toEntity(dto));

		return new ResponseEntity<>(cursoMapper.toDTO(curso), HttpStatus.CREATED);
	}

	@DeleteMapping("delete/{codigo}")
	public ResponseEntity<ConsultaCursoDTO> deletarCurso(@PathVariable String codigo) {

		cursoService.validarParametros(codigo);

		cursoService.excluirCurso(codigo);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
