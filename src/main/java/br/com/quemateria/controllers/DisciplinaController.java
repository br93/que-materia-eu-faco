package br.com.quemateria.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.quemateria.dto.disciplina.ConsultaDisciplinaDTO;
import br.com.quemateria.dto.disciplina.DisciplinaMapper;
import br.com.quemateria.dto.disciplina.RegistroDisciplinaDTO;
import br.com.quemateria.dto.disciplina.RegistroDisciplinaSimplesDTO;
import br.com.quemateria.dto.matriz.ConsultaMatrizDTO;
import br.com.quemateria.dto.matriz.MatrizCurricularMapper;
import br.com.quemateria.dto.matriz.RegistroMatrizDTO;
import br.com.quemateria.entities.Disciplina;
import br.com.quemateria.entities.ItemMatrizCurricular;
import br.com.quemateria.services.DisciplinaService;
import br.com.quemateria.services.ItemMatrizCurricularService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("v1/disciplinas")
@Validated
@AllArgsConstructor
public class DisciplinaController {

	private final DisciplinaService disciplinaService;
	private final DisciplinaMapper disciplinaMapper;

	private final ItemMatrizCurricularService matrizCurricularService;
	private final MatrizCurricularMapper matrizCurricularMapper;

	@GetMapping
	public ResponseEntity<ConsultaDisciplinaDTO> buscarDisciplina(
			@RequestParam @Length(min = 5, max = 6) @Pattern(regexp = "(^[A-Z0-9]{5}|^[A-Z0-9]{6})$", message = "Formato XXXXX ou XXXXXX") String codigo) {
		return ResponseEntity.ok(disciplinaMapper.toDTO(disciplinaService.buscarDisciplinaPorCodigo(codigo)));
	}

	@GetMapping("list")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Page<ConsultaDisciplinaDTO>> listarDisciplinas(@PageableDefault Pageable pageable) {
		return ResponseEntity.ok(disciplinaService.listarDisciplinas(pageable).map(disciplinaMapper::toDTO));
	}

	@PostMapping("add")
	public ResponseEntity<ConsultaDisciplinaDTO> salvarDisciplina(@Valid @RequestBody RegistroDisciplinaDTO dto) {
		Disciplina disciplina = disciplinaService.salvarDisciplina(disciplinaMapper.toEntity(dto));

		return ResponseEntity.ok(disciplinaMapper.toDTO(disciplina));
	}

	@PutMapping("add/prerequisito")
	public ResponseEntity<ConsultaDisciplinaDTO> adicionarPreRequisito(
			@Valid @RequestBody List<RegistroDisciplinaSimplesDTO> dto,
			@RequestParam @Length(min = 5, max = 6) @Pattern(regexp = "(^[A-Z0-9]{5}|^[A-Z0-9]{6})$", message = "Formato XXXXX ou XXXXXX") String codigo) {

		Set<Disciplina> preRequisitos = new HashSet<>(disciplinaMapper.toEntityList(dto));
		Disciplina disciplina = disciplinaService.adicionarPreRequisitos(preRequisitos, codigo);

		return ResponseEntity.ok(disciplinaMapper.toDTO(disciplina));

	}

	@PutMapping("add/equivalencia")
	public ResponseEntity<ConsultaDisciplinaDTO> adicionarEquivalencia(
			@Valid @RequestBody List<RegistroDisciplinaSimplesDTO> dto,
			@RequestParam @Length(min = 5, max = 6) @Pattern(regexp = "(^[A-Z0-9]{5}|^[A-Z0-9]{6})$", message = "Formato XXXXX ou XXXXXX") String codigo) {

		Set<Disciplina> equivalencias = new HashSet<>(disciplinaMapper.toEntityList(dto));
		Disciplina disciplina = disciplinaService.adicionarPreRequisitos(equivalencias, codigo);

		return ResponseEntity.ok(disciplinaMapper.toDTO(disciplina));

	}

	@DeleteMapping("delete/{codigo}")
	public ResponseEntity<ConsultaDisciplinaDTO> deletarDisciplina(
			@PathVariable @Length(min = 5, max = 6) @Pattern(regexp = "(^[A-Z0-9]{5}|^[A-Z0-9]{6})$") String codigo) {
		disciplinaService.excluirDisciplina(codigo);

		return ResponseEntity.noContent().build();
	}

	@PutMapping("delete/prerequisito")
	public ResponseEntity<ConsultaDisciplinaDTO> deletarPreRequisito(
			@RequestParam @Length(min = 5, max = 6) @Pattern(regexp = "(^[A-Z0-9]{5}|^[A-Z0-9]{6})$", message = "Formato XXXXX ou XXXXXX") String disciplina,
			@RequestParam @Length(min = 5, max = 6) @Pattern(regexp = "(^[A-Z0-9]{5}|^[A-Z0-9]{6})$", message = "Formato XXXXX ou XXXXXX") String preRequisito) {
		Disciplina disciplinaAtualizada = disciplinaService.removerPreRequisito(disciplina, preRequisito);

		return ResponseEntity.ok(disciplinaMapper.toDTO(disciplinaAtualizada));
	}

	@PutMapping("delete/equivalencia")
	public ResponseEntity<ConsultaDisciplinaDTO> deletarEquivalencia(
			@RequestParam @Length(min = 5, max = 6) @Pattern(regexp = "(^[A-Z0-9]{5}|^[A-Z0-9]{6})$", message = "Formato XXXXX ou XXXXXX") String disciplina,
			@RequestParam @Length(min = 5, max = 6) @Pattern(regexp = "(^[A-Z0-9]{5}|^[A-Z0-9]{6})$", message = "Formato XXXXX ou XXXXXX") String equivalencia) {
		Disciplina disciplinaAtualizada = disciplinaService.removerEquivalencia(disciplina, equivalencia);

		return ResponseEntity.ok(disciplinaMapper.toDTO(disciplinaAtualizada));
	}

	@PostMapping("register")
	public ResponseEntity<ConsultaMatrizDTO> registarDisciplinaNaMatrizCurricular(
			@Valid @RequestBody RegistroMatrizDTO dto) {
		ItemMatrizCurricular itemMatrizCurricular = matrizCurricularService
				.salvarItemMatrizCurricular(matrizCurricularMapper.toEntity(dto));

		return ResponseEntity.ok(matrizCurricularMapper.toDTO(itemMatrizCurricular));
	}

}
