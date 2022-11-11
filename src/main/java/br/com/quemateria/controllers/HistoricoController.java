package br.com.quemateria.controllers;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quemateria.dto.disciplina.ConsultaDisciplinaDTO;
import br.com.quemateria.dto.disciplina.ConsultaDisciplinaSimplesDTO;
import br.com.quemateria.dto.disciplina.DisciplinaMapper;
import br.com.quemateria.entities.Usuario;
import br.com.quemateria.services.HistoricoService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("v1/historico")
@AllArgsConstructor
public class HistoricoController {

	private final HistoricoService historicoService;
	private final DisciplinaMapper disciplinaMapper;
	

	@GetMapping("simples")
	public ResponseEntity<Set<ConsultaDisciplinaSimplesDTO>> disciplinasCursadasSimples(Authentication auth) {
		Usuario login = (Usuario) auth.getPrincipal();
		return ResponseEntity.ok(disciplinaMapper.toSetSimplesDTO(historicoService.getDisciplinasCursadas(login.getId())));
	}

	@GetMapping("completo")
	public ResponseEntity<Set<ConsultaDisciplinaDTO>> disciplinasCursadasCompleto(Authentication auth) {
		Usuario login = (Usuario) auth.getPrincipal();
		return ResponseEntity.ok(disciplinaMapper.toSetCompletoDTO(historicoService.getDisciplinasCursadas(login.getId())));
	}

	@GetMapping("faltantes/simples")
	public ResponseEntity<Set<ConsultaDisciplinaSimplesDTO>> disciplinasFaltantesSimples(Authentication auth) {
		Usuario login = (Usuario) auth.getPrincipal();
		return ResponseEntity
				.ok(new LinkedHashSet<>(disciplinaMapper.toListSimplesDTO(historicoService.getDisciplinasFaltantes(login.getId()))));
	}

	@GetMapping("faltantes/completo")
	public ResponseEntity<Set<ConsultaDisciplinaDTO>> disciplinasFaltantesCompleto(Authentication auth) {
		Usuario login = (Usuario) auth.getPrincipal();
		return ResponseEntity.ok(
				new LinkedHashSet<>(disciplinaMapper.toListCompletoDTO(historicoService.getDisciplinasFaltantes(login.getId()))));

	}

}
