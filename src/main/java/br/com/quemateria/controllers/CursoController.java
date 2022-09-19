package br.com.quemateria.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quemateria.dto.curso.ConsultaCursoDTO;
import br.com.quemateria.dto.curso.CursoMapper;
import br.com.quemateria.services.CursoService;

@RestController
@RequestMapping("v1/cursos")
public class CursoController {
	
	private final CursoService cursoService;
	private final CursoMapper cursoMapper;
	
	public CursoController(CursoService cursoService, CursoMapper cursoMapper) {
		this.cursoService = cursoService;
		this.cursoMapper = cursoMapper;
	}
	
	@GetMapping("list")
	public ResponseEntity<Page<ConsultaCursoDTO>> listarCursos(@PageableDefault Pageable pageable){
		return ResponseEntity.ok(cursoService.listarCursos(pageable).map(cursoMapper::toDTO));
	}
	
	

}
