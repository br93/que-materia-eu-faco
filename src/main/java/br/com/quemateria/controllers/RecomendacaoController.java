package br.com.quemateria.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.quemateria.dto.horario.ConsultaHorarioAulaDTO;
import br.com.quemateria.dto.horario.ConsultaHorarioAulaSimplesDTO;
import br.com.quemateria.dto.horario.HorarioAulaMapper;
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
	public ResponseEntity<List<ConsultaHorarioAulaDTO>> getRecomendacao(
			@RequestParam(value = "manha", defaultValue = "true", required = false) Boolean manha,
			@RequestParam(value = "tarde", defaultValue = "false", required = false) Boolean tarde,
			@RequestParam(value = "noite", defaultValue = "false", required = false) Boolean noite,
			@RequestParam(value = "horas", defaultValue = "20", required = false) Integer maximoHoras) {

		Aluno aluno = alunoService.getUltimoAlunoCadastrado();
		Long cursoId = alunoService.getUltimoAlunoCadastrado().getCurso().getId();
		Integer periodo = alunoService.getUltimoAlunoCadastrado().getPeriodo();
		Integer periodoMaximo = periodo + 2;

		recomendacaoService.calcularPeso(cursoId, periodo);

		return ResponseEntity.ok(horarioAulaMapper.toListRecomendacaoDTO(recomendacaoService.recomendacaoCompleta(aluno,
				cursoId, periodoMaximo, manha, tarde, noite, maximoHoras)));

	}

	@GetMapping("relatorio")
	public ResponseEntity<List<ConsultaHorarioAulaSimplesDTO>> getRelatorioRecomendacoes(
			@RequestParam(value = "manha", defaultValue = "true", required = false) Boolean manha,
			@RequestParam(value = "tarde", defaultValue = "false", required = false) Boolean tarde,
			@RequestParam(value = "noite", defaultValue = "false", required = false) Boolean noite,
			@RequestParam(value = "horas", defaultValue = "20", required = false) Integer maximoHoras) {

		Aluno aluno = alunoService.getUltimoAlunoCadastrado();
		Long cursoId = alunoService.getUltimoAlunoCadastrado().getCurso().getId();
		Integer periodo = alunoService.getUltimoAlunoCadastrado().getPeriodo();
		Integer periodoMaximo = periodo + 2;

		recomendacaoService.calcularPeso(cursoId, periodo);

		List<HorarioAula> relatorioRecomendacao = recomendacaoService.gerarRelatorioDeRecomendacao(aluno, cursoId,
				periodoMaximo, manha, tarde, noite, maximoHoras);

		return ResponseEntity.ok(horarioAulaMapper.toListConsultaDTO(relatorioRecomendacao));

	}

	@GetMapping("opcionais")
	public ResponseEntity<List<ConsultaHorarioAulaDTO>> getRecomendacoesOpcionais(@RequestParam Integer dia,
			@RequestParam String horario) {

		Aluno aluno = alunoService.getUltimoAlunoCadastrado();
		Long cursoId = alunoService.getUltimoAlunoCadastrado().getCurso().getId();

		List<HorarioAula> recomendacoesOpcionais = recomendacaoService.recomendarMateriasOpcionaisPorHorario(aluno,
				cursoId, dia, horario);

		return ResponseEntity.ok(horarioAulaMapper.toListRecomendacaoDTO(recomendacoesOpcionais));
	}

	@GetMapping("opcionais/relatorio")
	public ResponseEntity<List<ConsultaHorarioAulaSimplesDTO>> getRelatorioRecomendacoesOpcionais(@RequestParam Integer dia,
			@RequestParam String horario) {

		Aluno aluno = alunoService.getUltimoAlunoCadastrado();
		Long cursoId = alunoService.getUltimoAlunoCadastrado().getCurso().getId();

		List<HorarioAula> recomendacoesOpcionais = recomendacaoService.recomendarMateriasOpcionaisPorHorario(aluno,
				cursoId, dia, horario);

		return ResponseEntity.ok(horarioAulaMapper.toListConsultaDTO(recomendacoesOpcionais));
	}

	@GetMapping("enriquecimento")
	public ResponseEntity<List<ConsultaHorarioAulaDTO>> getRecomendacoesEnriquecimento(@RequestParam Integer dia,
			@RequestParam String horario) {

		Aluno aluno = alunoService.getUltimoAlunoCadastrado();
		Long cursoId = alunoService.getUltimoAlunoCadastrado().getCurso().getId();

		List<HorarioAula> recomendacoesEnriquecimento = recomendacaoService
				.recomendarMateriasEnriquecimentoPorHorario(aluno, cursoId, dia, horario);

		return ResponseEntity.ok(horarioAulaMapper.toListRecomendacaoDTO(recomendacoesEnriquecimento));
	}

	@GetMapping("enriquecimento/relatorio")
	public ResponseEntity<List<ConsultaHorarioAulaSimplesDTO>> getRelatorioRecomendacoesEnriquecimento(
			@RequestParam Integer dia, @RequestParam String horario) {

		Aluno aluno = alunoService.getUltimoAlunoCadastrado();
		Long cursoId = alunoService.getUltimoAlunoCadastrado().getCurso().getId();

		List<HorarioAula> recomendacoesEnriquecimento = recomendacaoService
				.recomendarMateriasEnriquecimentoPorHorario(aluno, cursoId, dia, horario);

		return ResponseEntity.ok(horarioAulaMapper.toListConsultaDTO(recomendacoesEnriquecimento));
	}

}
