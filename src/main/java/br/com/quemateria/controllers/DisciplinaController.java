package br.com.quemateria.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<ConsultaDisciplinaDTO> buscarDisciplina(@RequestParam String codigo) {
		disciplinaService.validarParametros(codigo);
		return new ResponseEntity<>(disciplinaMapper.toDTO(disciplinaService.buscarDisciplinaPorCodigo(codigo)),
				HttpStatus.OK);
	}

	@GetMapping("list")
	public ResponseEntity<Page<ConsultaDisciplinaDTO>> listarDisciplinas(@PageableDefault Pageable pageable,
			@RequestParam(required = false) Integer page) {
		return new ResponseEntity<>(disciplinaService.listarDisciplinas(pageable).map(disciplinaMapper::toDTO),
				HttpStatus.PARTIAL_CONTENT);
	}

	@PostMapping("add")
	public ResponseEntity<ConsultaDisciplinaDTO> salvarDisciplina(@RequestBody RegistroDisciplinaDTO dto) {
		disciplinaService.validarParametros(dto.getNome(), dto.getCodigo(), dto.getCargaHoraria());
		Disciplina disciplina = disciplinaService.salvarDisciplina(disciplinaMapper.toEntity(dto));

		return new ResponseEntity<>(disciplinaMapper.toDTO(disciplina), HttpStatus.CREATED);
	}

	@PutMapping("add/prerequisito")
	public ResponseEntity<ConsultaDisciplinaDTO> adicionarPreRequisito(
			@RequestBody List<RegistroDisciplinaSimplesDTO> dto, @RequestParam String codigo) {

		dto.forEach((x) -> disciplinaService.validarParametros(x.getCodigo()));
		disciplinaService.validarParametros(codigo);

		Set<Disciplina> preRequisitos = new HashSet<>(disciplinaMapper.toEntityList(dto));
		Disciplina disciplina = disciplinaService.adicionarPreRequisitos(preRequisitos, codigo);

		return new ResponseEntity<>(disciplinaMapper.toDTO(disciplina), HttpStatus.OK);

	}

	@PutMapping("add/equivalencia")
	public ResponseEntity<ConsultaDisciplinaDTO> adicionarEquivalencia(
			@RequestBody List<RegistroDisciplinaSimplesDTO> dto, @RequestParam String codigo) {

		dto.forEach((x) -> disciplinaService.validarParametros(x.getCodigo()));
		disciplinaService.validarParametros(codigo);

		Set<Disciplina> equivalencias = new HashSet<>(disciplinaMapper.toEntityList(dto));
		Disciplina disciplina = disciplinaService.adicionarPreRequisitos(equivalencias, codigo);

		return new ResponseEntity<>(disciplinaMapper.toDTO(disciplina), HttpStatus.OK);
	}

	@DeleteMapping("delete/{codigo}")
	public ResponseEntity<ConsultaDisciplinaDTO> deletarDisciplina(@PathVariable String codigo) {

		disciplinaService.validarParametros(codigo);
		disciplinaService.excluirDisciplina(codigo);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("delete/prerequisito")
	public ResponseEntity<ConsultaDisciplinaDTO> deletarPreRequisito(@RequestParam String disciplina,
			@RequestParam String preRequisito) {

		disciplinaService.validarParametros(disciplina);
		disciplinaService.validarParametros(preRequisito);

		Disciplina disciplinaAtualizada = disciplinaService.removerPreRequisito(disciplina, preRequisito);

		return new ResponseEntity<>(disciplinaMapper.toDTO(disciplinaAtualizada), HttpStatus.OK);
	}

	@PutMapping("delete/equivalencia")
	public ResponseEntity<ConsultaDisciplinaDTO> deletarEquivalencia(@RequestParam String disciplina,
			@RequestParam String equivalencia) {

		disciplinaService.validarParametros(disciplina);
		disciplinaService.validarParametros(equivalencia);

		Disciplina disciplinaAtualizada = disciplinaService.removerEquivalencia(disciplina, equivalencia);

		return new ResponseEntity<>(disciplinaMapper.toDTO(disciplinaAtualizada), HttpStatus.OK);
	}

	@PostMapping("register")
	public ResponseEntity<ConsultaMatrizDTO> registarDisciplinaNaMatrizCurricular(@RequestBody RegistroMatrizDTO dto) {

		matrizCurricularService.validarParametros(dto.getCurso(), dto.getDisciplina(),
				dto.getTipoDeDisciplina().intValue(), dto.getPreRequisitos());

		ItemMatrizCurricular itemMatrizCurricular = matrizCurricularService
				.salvarItemMatrizCurricular(matrizCurricularMapper.toEntity(dto));

		return new ResponseEntity<>(matrizCurricularMapper.toDTO(itemMatrizCurricular), HttpStatus.CREATED);
	}

}
