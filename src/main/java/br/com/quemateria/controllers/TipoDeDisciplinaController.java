package br.com.quemateria.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quemateria.dto.disciplina.ConsultaTipoDeDisciplinaDTO;
import br.com.quemateria.dto.disciplina.TipoDeDisciplinaMapper;
import br.com.quemateria.services.TipoDeDisciplinaService;

@RestController
@RequestMapping("v1/disciplinas/tipos")
public class TipoDeDisciplinaController {
	
	private final TipoDeDisciplinaService tipoDeDisciplinaService;
	private final TipoDeDisciplinaMapper tipoDeDisciplinaMapper;
	
	public TipoDeDisciplinaController(TipoDeDisciplinaService tipoDeDisciplinaService, TipoDeDisciplinaMapper tipoDeDisciplinaMapper) {
		this.tipoDeDisciplinaService = tipoDeDisciplinaService;
		this.tipoDeDisciplinaMapper = tipoDeDisciplinaMapper;
	}
	
	@GetMapping("list")
	public ResponseEntity<Page<ConsultaTipoDeDisciplinaDTO>> listarTipoDeDisciplina(@PageableDefault Pageable pageable){
		return ResponseEntity.ok(tipoDeDisciplinaService.listarTipoDeDisciplinas(pageable).map(tipoDeDisciplinaMapper::toDTO));
	}
	

}
