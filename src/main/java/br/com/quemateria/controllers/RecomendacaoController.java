package br.com.quemateria.controllers;

import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.quemateria.dto.horario.ConsultaHorarioAulaDTO;
import br.com.quemateria.dto.horario.ConsultaHorarioAulaSimplesDTO;
import br.com.quemateria.dto.horario.HorarioAulaMapper;
import br.com.quemateria.entities.Aluno;
import br.com.quemateria.entities.HorarioAula;
import br.com.quemateria.entities.Usuario;
import br.com.quemateria.services.AlunoService;
import br.com.quemateria.services.RecomendacaoService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("v1/recomendacoes")
@Validated
@AllArgsConstructor
public class RecomendacaoController {

	public final AlunoService alunoService;
	public final RecomendacaoService recomendacaoService;
	public final HorarioAulaMapper horarioAulaMapper;

	@PatchMapping
	public ResponseEntity<List<ConsultaHorarioAulaDTO>> getRecomendacao(
			@RequestParam(value = "manha", defaultValue = "true", required = false) Boolean manha,
			@RequestParam(value = "tarde", defaultValue = "false", required = false) Boolean tarde,
			@RequestParam(value = "noite", defaultValue = "false", required = false) Boolean noite,
			@RequestParam(value = "horas", defaultValue = "20", required = false) Integer maximoHoras,
			Authentication auth) {

		Usuario login = (Usuario) auth.getPrincipal();

		Aluno aluno = alunoService.getUsuario(login.getId());
		Long cursoId = aluno.getCurso().getId();
		Integer periodo = aluno.getPeriodo();
		Integer periodoMaximo = periodo + 2;

		recomendacaoService.calcularPeso(cursoId, periodo, login.getId());

		return new ResponseEntity<>(horarioAulaMapper.toListRecomendacaoDTO(recomendacaoService.recomendacaoCompleta(aluno,
				cursoId, periodoMaximo, manha, tarde, noite, maximoHoras)), HttpStatus.OK);

	}

	@PatchMapping("relatorio")
	public ResponseEntity<List<ConsultaHorarioAulaSimplesDTO>> getRelatorioRecomendacoes(
			@RequestParam(value = "manha", defaultValue = "true", required = false) Boolean manha,
			@RequestParam(value = "tarde", defaultValue = "false", required = false) Boolean tarde,
			@RequestParam(value = "noite", defaultValue = "false", required = false) Boolean noite,
			@RequestParam(value = "horas", defaultValue = "20", required = false) Integer maximoHoras,
			Authentication auth) {

		Usuario login = (Usuario) auth.getPrincipal();

		Aluno aluno = alunoService.getUsuario(login.getId());
		Long cursoId = aluno.getCurso().getId();
		Integer periodo = aluno.getPeriodo();
		Integer periodoMaximo = periodo + 2;

		recomendacaoService.calcularPeso(cursoId, periodo, login.getId());

		List<HorarioAula> relatorioRecomendacao = recomendacaoService.gerarRelatorioDeRecomendacao(aluno, cursoId,
				periodoMaximo, manha, tarde, noite, maximoHoras);

		return new ResponseEntity<>(horarioAulaMapper.toListConsultaDTO(relatorioRecomendacao), HttpStatus.OK);

	}

	@GetMapping("opcionais")
	public ResponseEntity<List<ConsultaHorarioAulaDTO>> getRecomendacoesOpcionais(
			@RequestParam @Range(min = 2, max = 6) Integer dia,
			@RequestParam @Length(min = 2, max = 2) @Pattern(regexp = "(^[MT][1-6]|^[N][1-5])$", message = "Formato X0") String horario,
			Authentication auth) {

		Usuario login = (Usuario) auth.getPrincipal();

		Aluno aluno = alunoService.getUsuario(login.getId());
		Long cursoId = aluno.getCurso().getId();

		List<HorarioAula> recomendacoesOpcionais = recomendacaoService.recomendarMateriasOpcionaisPorHorario(aluno,
				cursoId, dia, horario);

		return new ResponseEntity<>(horarioAulaMapper.toListRecomendacaoDTO(recomendacoesOpcionais), HttpStatus.OK);
	}

	@GetMapping("opcionais/relatorio")
	public ResponseEntity<List<ConsultaHorarioAulaSimplesDTO>> getRelatorioRecomendacoesOpcionais(
			@RequestParam @Range(min = 2, max = 6) Integer dia,
			@RequestParam @Length(min = 2, max = 2) @Pattern(regexp = "(^[MT][1-6]|^[N][1-5])$", message = "Formato X0") String horario,
			Authentication auth) {

		Usuario login = (Usuario) auth.getPrincipal();

		Aluno aluno = alunoService.getUsuario(login.getId());
		Long cursoId = aluno.getCurso().getId();

		List<HorarioAula> recomendacoesOpcionais = recomendacaoService.recomendarMateriasOpcionaisPorHorario(aluno,
				cursoId, dia, horario);

		return new ResponseEntity<>(horarioAulaMapper.toListConsultaDTO(recomendacoesOpcionais), HttpStatus.OK);
	}

	@GetMapping("enriquecimento")
	public ResponseEntity<List<ConsultaHorarioAulaDTO>> getRecomendacoesEnriquecimento(
			@RequestParam @Range(min = 2, max = 6) Integer dia,
			@RequestParam @Length(min = 2, max = 2) @Pattern(regexp = "(^[MT][1-6]|^[N][1-5])$", message = "Formato X0") String horario,
			Authentication auth) {

		Usuario login = (Usuario) auth.getPrincipal();

		Aluno aluno = alunoService.getUsuario(login.getId());
		Long cursoId = aluno.getCurso().getId();

		List<HorarioAula> recomendacoesEnriquecimento = recomendacaoService
				.recomendarMateriasEnriquecimentoPorHorario(aluno, cursoId, dia, horario);

		return new ResponseEntity<>(horarioAulaMapper.toListRecomendacaoDTO(recomendacoesEnriquecimento), HttpStatus.OK);
	}

	@GetMapping("enriquecimento/relatorio")
	public ResponseEntity<List<ConsultaHorarioAulaSimplesDTO>> getRelatorioRecomendacoesEnriquecimento(
			@RequestParam @Size(min = 2, max = 6) Integer dia,
			@RequestParam @Length(min = 2, max = 2) @Pattern(regexp = "(^[MT][1-6]|^[N][1-5])$", message = "Formato X0") String horario,
			Authentication auth) {

		Usuario login = (Usuario) auth.getPrincipal();

		Aluno aluno = alunoService.getUsuario(login.getId());
		Long cursoId = aluno.getCurso().getId();

		List<HorarioAula> recomendacoesEnriquecimento = recomendacaoService
				.recomendarMateriasEnriquecimentoPorHorario(aluno, cursoId, dia, horario);

		return new ResponseEntity<>(horarioAulaMapper.toListConsultaDTO(recomendacoesEnriquecimento), HttpStatus.OK);
	}

}
