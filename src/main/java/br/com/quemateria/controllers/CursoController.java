package br.com.quemateria.controllers;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
@Validated
@AllArgsConstructor
public class CursoController {

	private final CursoService cursoService;
	private final CursoMapper cursoMapper;

	@GetMapping
	public ResponseEntity<ConsultaCursoDTO> buscarCurso(
			@RequestParam @Length(min = 3, max = 3) @Pattern(regexp = "^[0-9]{3}$", message = "Formato 000") String codigo) {
		return ResponseEntity.ok(cursoMapper.toDTO(cursoService.buscarCursoPorCodigo(codigo)));
	}

	@GetMapping("list")
	public ResponseEntity<Page<ConsultaCursoDTO>> listarCursos(@PageableDefault Pageable pageable) {
		return ResponseEntity.ok(cursoService.listarCursos(pageable).map(cursoMapper::toDTO));
	}

	@PostMapping("add")
	public ResponseEntity<ConsultaCursoDTO> salvarCurso(@Valid @RequestBody RegistroCursoDTO dto) {
		Curso curso = cursoService.salvarCurso(cursoMapper.toEntity(dto));

		return ResponseEntity.ok(cursoMapper.toDTO(curso));
	}

	@DeleteMapping("delete/{codigo}")
	public ResponseEntity<ConsultaCursoDTO> deletarCurso(
			@PathVariable @Length(min = 3, max = 3) @Pattern(regexp = "^[0-9]{3}$", message = "Formato 000") String codigo) {
		cursoService.excluirCurso(codigo);

		return ResponseEntity.noContent().build();
	}

}
