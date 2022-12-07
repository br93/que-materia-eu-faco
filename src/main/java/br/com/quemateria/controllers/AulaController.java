package br.com.quemateria.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("v1/aulas")
@AllArgsConstructor
public class AulaController {

	private final HorarioAulaService horarioAulaService;
	private final HorarioAulaMapper horarioAulaMapper;

	@GetMapping
	public ResponseEntity<List<ConsultaHorarioAulaCompletoDTO>> buscarAula(@RequestParam String codigoTurma) {

		horarioAulaService.validarParametros(codigoTurma);
		return new ResponseEntity<>(
				horarioAulaMapper.toListCompletoDTO(horarioAulaService.listarAulaPorTurmaCodigo(codigoTurma)),
				HttpStatus.OK);

	}

	@GetMapping("list")
	public ResponseEntity<Page<ConsultaHorarioAulaDTO>> listarAulas(@PageableDefault Pageable pageable,
			@RequestParam(required = false) Integer page) {
		return new ResponseEntity<>(horarioAulaService.listarAulas(pageable).map(horarioAulaMapper::toDTO),
				HttpStatus.PARTIAL_CONTENT);
	}

	@PostMapping("add")
	public ResponseEntity<ConsultaHorarioAulaDTO> adicionarAula(@RequestBody RegistroHorarioAulaDTO dto) {
		horarioAulaService.validarParametros(dto.getTurma(), dto.getDia(), dto.getHorario());
		HorarioAula horarioAula = horarioAulaService.salvarAula(horarioAulaMapper.toEntity(dto));

		return new ResponseEntity<>(horarioAulaMapper.toDTO(horarioAula), HttpStatus.CREATED);
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<ConsultaHorarioAulaDTO> deletarTurma(@PathVariable Long id) {

		horarioAulaService.validarParametros(id);
		horarioAulaService.excluirAula(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
