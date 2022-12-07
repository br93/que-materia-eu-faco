package br.com.quemateria.controllers;

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

import br.com.quemateria.dto.turma.ConsultaTurmaDTO;
import br.com.quemateria.dto.turma.RegistroTurmaDTO;
import br.com.quemateria.dto.turma.TurmaMapper;
import br.com.quemateria.entities.Turma;
import br.com.quemateria.services.TurmaService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("v1/turmas")
@AllArgsConstructor
public class TurmaController {

	private final TurmaService turmaService;
	private final TurmaMapper turmaMapper;

	@GetMapping
	public ResponseEntity<ConsultaTurmaDTO> buscarTurma(@RequestParam String codigo) {

		turmaService.validarParametros(codigo);
		return new ResponseEntity<>(turmaMapper.toDTO(turmaService.buscarTurmaPorCodigo(codigo)), HttpStatus.OK);
	}

	@GetMapping("list")
	public ResponseEntity<Page<ConsultaTurmaDTO>> listarTurmas(@PageableDefault Pageable pageable,
			@RequestParam(required = false) Integer page) {
		return new ResponseEntity<>(turmaService.listarTurmas(pageable).map(turmaMapper::toDTO),
				HttpStatus.PARTIAL_CONTENT);
	}

	@PostMapping("add")
	public ResponseEntity<ConsultaTurmaDTO> adicionarTurma(@RequestBody RegistroTurmaDTO dto) {

		turmaService.validarParametros(dto.getCodigo(), dto.getDisciplina());
		Turma turma = turmaService.salvarTurma(turmaMapper.toEntity(dto));

		return new ResponseEntity<>(turmaMapper.toDTO(turma), HttpStatus.CREATED);
	}

	@DeleteMapping("delete/{codigo}")
	public ResponseEntity<ConsultaTurmaDTO> deletarTurma(@PathVariable String codigo) {

		turmaService.validarParametros(codigo);
		turmaService.excluirTurma(codigo);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
