package br.com.quemateria.controllers;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quemateria.dto.disciplina.ConsultaDisciplinaDTO;
import br.com.quemateria.dto.disciplina.ConsultaDisciplinaSimplesDTO;
import br.com.quemateria.dto.disciplina.DisciplinaMapper;
import br.com.quemateria.services.HistoricoService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("v1/historico")
@AllArgsConstructor
public class HistoricoController {

	private final HistoricoService historicoService;
	private final DisciplinaMapper disciplinaMapper;

	@GetMapping("simples")
	public ResponseEntity<Set<ConsultaDisciplinaSimplesDTO>> disciplinasCursadasSimples() {
		return ResponseEntity.ok(disciplinaMapper.toSetSimplesDTO(historicoService.getDisciplinasCursadas()));
	}

	@GetMapping("completo")
	public ResponseEntity<Set<ConsultaDisciplinaDTO>> disciplinasCursadasCompleto() {
		return ResponseEntity.ok(disciplinaMapper.toSetCompletoDTO(historicoService.getDisciplinasCursadas()));
	}

	@GetMapping("faltantes/simples")
	public ResponseEntity<Set<ConsultaDisciplinaSimplesDTO>> disciplinasFaltantesSimples() {
		return ResponseEntity
				.ok(new LinkedHashSet<>(disciplinaMapper.toListSimplesDTO(historicoService.getDisciplinasFaltantes())));
	}

	@GetMapping("faltantes/completo")
	public ResponseEntity<Set<ConsultaDisciplinaDTO>> disciplinasFaltantesCompleto() {
		return ResponseEntity.ok(
				new LinkedHashSet<>(disciplinaMapper.toListCompletoDTO(historicoService.getDisciplinasFaltantes())));

	}

}
