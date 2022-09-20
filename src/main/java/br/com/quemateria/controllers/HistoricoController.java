package br.com.quemateria.controllers;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quemateria.dto.disciplina.ConsultaDisciplinaCursadaDTO;
import br.com.quemateria.dto.disciplina.ConsultaDisciplinaDTO;
import br.com.quemateria.dto.disciplina.DisciplinaMapper;
import br.com.quemateria.services.HistoricoService;

@RestController
@RequestMapping("v1/historico")
public class HistoricoController {

	private final HistoricoService historicoService;
	private final DisciplinaMapper disciplinaMapper;

	public HistoricoController(HistoricoService historicoService, DisciplinaMapper disciplinaMapper) {
		this.historicoService = historicoService;
		this.disciplinaMapper = disciplinaMapper;
	}

	@GetMapping("simples")
	public ResponseEntity<Set<ConsultaDisciplinaCursadaDTO>> disciplinasCursadasSimples() {
		return ResponseEntity.ok(disciplinaMapper.toSetSimplesDTO(historicoService.getDisciplinasCursadas()));
	}

	@GetMapping("completo")
	public ResponseEntity<Set<ConsultaDisciplinaDTO>> disciplinasCursadasCompleto() {
		return ResponseEntity.ok(disciplinaMapper.toSetCompletoDTO(historicoService.getDisciplinasCursadas()));
	}

	@GetMapping("faltantes/simples")
	public ResponseEntity<Set<ConsultaDisciplinaCursadaDTO>> disciplinasFaltantesSimples() {
		return ResponseEntity
				.ok(disciplinaMapper.toSetSimplesDTO(historicoService.getDisciplinasObrigatoriasFaltantes()));
	}

	@GetMapping("faltantes/completo")
	public ResponseEntity<Set<ConsultaDisciplinaDTO>> disciplinasFaltantesCompleto() {
		return ResponseEntity
				.ok(disciplinaMapper.toSetCompletoDTO(historicoService.getDisciplinasObrigatoriasFaltantes()));

	}

}
