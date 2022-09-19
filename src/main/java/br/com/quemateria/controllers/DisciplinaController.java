package br.com.quemateria.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quemateria.dto.disciplina.ConsultaDisciplinaDTO;
import br.com.quemateria.dto.disciplina.DisciplinaMapper;
import br.com.quemateria.services.DisciplinaService;

@RestController
@RequestMapping("v1/disciplinas")
public class DisciplinaController {
	
	private final DisciplinaService disciplinaService;
	private final DisciplinaMapper disciplinaMapper;
	
	public DisciplinaController(DisciplinaService disciplinaService, DisciplinaMapper disciplinaMapper) {
		this.disciplinaService = disciplinaService;
		this.disciplinaMapper = disciplinaMapper;
	}
	
	@GetMapping("list")
	public ResponseEntity<Page<ConsultaDisciplinaDTO>> listarDisciplinas(@PageableDefault Pageable pageable){
		return ResponseEntity.ok(disciplinaService.listarDisciplinas(pageable).map(disciplinaMapper::toDTO));
	}

}
