package br.com.quemateria.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.quemateria.dto.horario.ConsultaHorarioAulaCompletoDTO;
import br.com.quemateria.dto.horario.ConsultaHorarioAulaDTO;
import br.com.quemateria.dto.horario.HorarioAulaMapper;
import br.com.quemateria.dto.horario.RegistroHorarioAulaDTO;
import br.com.quemateria.entities.HorarioAula;
import br.com.quemateria.services.HorarioAulaService;

@RestController
@RequestMapping("v1/aulas")
public class AulaController {

	private final HorarioAulaService horarioAulaService;
	private final HorarioAulaMapper horarioAulaMapper;

	public AulaController(HorarioAulaService horarioAulaService, HorarioAulaMapper horarioAulaMapper) {
		this.horarioAulaService = horarioAulaService;
		this.horarioAulaMapper = horarioAulaMapper;
	}

	@GetMapping
	public ResponseEntity<List<ConsultaHorarioAulaCompletoDTO>> buscarAula(@RequestParam String codigoTurma) {
		return ResponseEntity
				.ok(horarioAulaMapper.toListCompletoDTO(horarioAulaService.listarAulaPorTurmaCodigo(codigoTurma)));

	}

	@GetMapping("list")
	public ResponseEntity<Page<ConsultaHorarioAulaDTO>> listarAulas(@PageableDefault Pageable pageable) {
		return ResponseEntity.ok(horarioAulaService.listarAulas(pageable).map(horarioAulaMapper::toDTO));
	}

	@PostMapping("add")
	public ResponseEntity<ConsultaHorarioAulaDTO> adicionarAula(@RequestBody RegistroHorarioAulaDTO dto) {
		HorarioAula horarioAula = horarioAulaService.salvarAula(horarioAulaMapper.toEntity(dto));

		return ResponseEntity.ok(horarioAulaMapper.toDTO(horarioAula));
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<ConsultaHorarioAulaDTO> deletarTurma(@PathVariable Long id) {
		horarioAulaService.excluirAula(id);

		return ResponseEntity.noContent().build();
	}

}
