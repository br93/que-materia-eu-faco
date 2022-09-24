package br.com.quemateria.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.quemateria.dto.horario.HorarioAulaMapper;
import br.com.quemateria.dto.horario.RecomendacaoDTO;
import br.com.quemateria.entities.Aluno;
import br.com.quemateria.services.AlunoService;
import br.com.quemateria.services.RecomendacaoService;

@RestController
@RequestMapping("v1/recomendacoes")
public class RecomendacaoController {

	public final AlunoService alunoService;
	public final RecomendacaoService recomendacaoService;
	public final HorarioAulaMapper horarioAulaMapper;

	public RecomendacaoController(AlunoService alunoService, RecomendacaoService recomendacaoService,
			HorarioAulaMapper horarioAulaMapper) {
		this.alunoService = alunoService;
		this.recomendacaoService = recomendacaoService;
		this.horarioAulaMapper = horarioAulaMapper;
	}

	/* Quebrou com as mudanças da Recomendação Completa, depois vou refazer para retornar um relatorio
	@GetMapping("{dia}/{horario}")
	public ResponseEntity<List<RecomendacaoDTO>> listarTop3RecomendacoesPorHorario(@PathVariable Integer dia,
			@PathVariable String horario) {

		Long cursoId = alunoService.getUltimoAlunoCadastrado().getCurso().getId();
		Set<Disciplina> disciplinasCursadas = alunoService.getUltimoAlunoCadastrado().getDisciplinasCursadas();
		recomendacaoService.calcularPeso();

		return ResponseEntity.ok(horarioAulaMapper.toListRecomendacaoDTO(
				recomendacaoService.recomendarMateriasPorHorarioAula(disciplinasCursadas, cursoId, dia, horario)));

	}
	*/

	@GetMapping
	public ResponseEntity<List<RecomendacaoDTO>> recomendacao(
			@RequestParam(value = "manha", defaultValue = "true", required = false) Boolean manha,
			@RequestParam(value = "tarde", defaultValue = "false", required = false) Boolean tarde,
			@RequestParam(value = "noite", defaultValue = "false", required = false) Boolean noite,
			@RequestParam(value = "horas", defaultValue = "20", required = false) Integer maximoHoras) {

		Aluno aluno = alunoService.getUltimoAlunoCadastrado();
		Long cursoId = alunoService.getUltimoAlunoCadastrado().getCurso().getId();
		Integer periodoMaximo = alunoService.getUltimoAlunoCadastrado().getPeriodo()+2;
		
		recomendacaoService.calcularPeso();
		
		return ResponseEntity.ok(horarioAulaMapper.toListRecomendacaoDTO(recomendacaoService
				.recomendacaoCompleta(aluno, cursoId, periodoMaximo, manha, tarde, noite, maximoHoras)));

	}

}
