package br.com.quemateria.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quemateria.dto.aluno.AlunoMapper;
import br.com.quemateria.dto.aluno.ConsultaAlunoDTO;
import br.com.quemateria.services.AlunoService;

@RestController
@RequestMapping("v1/alunos")
public class AlunoController {
	
	private final AlunoService alunoService;
	private final AlunoMapper alunoMapper;
	
	public AlunoController(AlunoService alunoService, AlunoMapper alunoMapper) {
		this.alunoService = alunoService;
		this.alunoMapper = alunoMapper;
	}
	
	@GetMapping("list")
	public ResponseEntity<Page<ConsultaAlunoDTO>> listarAlunos (@PageableDefault Pageable pageable){
		return ResponseEntity.ok(alunoService.listarAlunos(pageable).map(alunoMapper::toDTO));
	}

}
