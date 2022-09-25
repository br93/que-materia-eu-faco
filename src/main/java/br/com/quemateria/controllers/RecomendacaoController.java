package br.com.quemateria.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.quemateria.dto.horario.ConsultaHorarioAulaDTO;
import br.com.quemateria.dto.horario.HorarioAulaMapper;
import br.com.quemateria.dto.horario.RecomendacaoDTO;
import br.com.quemateria.entities.Aluno;
import br.com.quemateria.entities.HorarioAula;
import br.com.quemateria.services.AlunoService;
import br.com.quemateria.services.DisciplinaService;
import br.com.quemateria.services.RecomendacaoService;

@RestController
@RequestMapping("v1/recomendacoes")
public class RecomendacaoController {

	public final AlunoService alunoService;
	public final RecomendacaoService recomendacaoService;
	public final HorarioAulaMapper horarioAulaMapper;
	
	@Autowired
	public DisciplinaService disciplinaService;

	public RecomendacaoController(AlunoService alunoService, RecomendacaoService recomendacaoService,
			HorarioAulaMapper horarioAulaMapper) {
		this.alunoService = alunoService;
		this.recomendacaoService = recomendacaoService;
		this.horarioAulaMapper = horarioAulaMapper;
	}

	@GetMapping
	public ResponseEntity<List<RecomendacaoDTO>> getRecomendacao(
			@RequestParam(value = "manha", defaultValue = "true", required = false) Boolean manha,
			@RequestParam(value = "tarde", defaultValue = "false", required = false) Boolean tarde,
			@RequestParam(value = "noite", defaultValue = "false", required = false) Boolean noite,
			@RequestParam(value = "horas", defaultValue = "20", required = false) Integer maximoHoras) {

		Aluno aluno = alunoService.getUltimoAlunoCadastrado();
		Long cursoId = alunoService.getUltimoAlunoCadastrado().getCurso().getId();
		Integer periodoMaximo = alunoService.getUltimoAlunoCadastrado().getPeriodo() + 2;

		recomendacaoService.calcularPeso();
		
		System.out.println(disciplinaService.buscarDisciplina(91L).getEquivalencias().size());
		
		return ResponseEntity.ok(horarioAulaMapper.toListRecomendacaoDTO(recomendacaoService.recomendacaoCompleta(aluno,
				cursoId, periodoMaximo, manha, tarde, noite, maximoHoras)));

	}

	@GetMapping("relatorio")
	public ResponseEntity<List<ConsultaHorarioAulaDTO>> getRelatorioRecomendacoes(
			@RequestParam(value = "manha", defaultValue = "true", required = false) Boolean manha,
			@RequestParam(value = "tarde", defaultValue = "false", required = false) Boolean tarde,
			@RequestParam(value = "noite", defaultValue = "false", required = false) Boolean noite,
			@RequestParam(value = "horas", defaultValue = "20", required = false) Integer maximoHoras) {

		Aluno aluno = alunoService.getUltimoAlunoCadastrado();
		Long cursoId = alunoService.getUltimoAlunoCadastrado().getCurso().getId();
		Integer periodoMaximo = alunoService.getUltimoAlunoCadastrado().getPeriodo() + 2;

		recomendacaoService.calcularPeso();

		List<HorarioAula> relatorioRecomendacao = recomendacaoService.gerarRelatorioDeRecomendacao(aluno, cursoId,
				periodoMaximo, manha, tarde, noite, maximoHoras);

		return ResponseEntity.ok(horarioAulaMapper.toListConsultaDTO(relatorioRecomendacao));

	}

}
