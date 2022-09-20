package br.com.quemateria.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quemateria.dto.disciplina.ConsultaDisciplinaDTO;
import br.com.quemateria.dto.disciplina.ConsultaDisciplinaSimplesDTO;
import br.com.quemateria.dto.disciplina.DisciplinaMapper;
import br.com.quemateria.services.DisciplinaService;
import br.com.quemateria.services.HistoricoService;

@RestController
@RequestMapping("v1/historico")
public class HistoricoController {

	private final HistoricoService historicoService;
	private final DisciplinaService disciplinaService;
	private final DisciplinaMapper disciplinaMapper;

	public HistoricoController(HistoricoService historicoService, DisciplinaService disciplinaService, DisciplinaMapper disciplinaMapper) {
		this.historicoService = historicoService;
		this.disciplinaService = disciplinaService;
		this.disciplinaMapper = disciplinaMapper;
	}

	@GetMapping("simples")
	public ResponseEntity<List<ConsultaDisciplinaSimplesDTO>> disciplinasCursadasSimples() {
		disciplinaService.calcularPeso();
		return ResponseEntity.ok(disciplinaMapper.toListSimplesDTO(historicoService.getDisciplinasCursadas()));
	}

	@GetMapping("completo")
	public ResponseEntity<List<ConsultaDisciplinaDTO>> disciplinasCursadasCompleto() {
		return ResponseEntity.ok(disciplinaMapper.toListCompletoDTO(historicoService.getDisciplinasCursadas()));
	}

	@GetMapping("faltantes/simples")
	public ResponseEntity<Set<ConsultaDisciplinaSimplesDTO>> disciplinasFaltantesSimples() {
		return ResponseEntity
				.ok(new HashSet<>(disciplinaMapper.toListSimplesDTO(historicoService.getDisciplinasObrigatoriasFaltantes())));
	}

	@GetMapping("faltantes/completo")
	public ResponseEntity<Set<ConsultaDisciplinaDTO>> disciplinasFaltantesCompleto() {
		return ResponseEntity
				.ok(new HashSet<>(disciplinaMapper.toListCompletoDTO(historicoService.getDisciplinasObrigatoriasFaltantes())));

	}

}
