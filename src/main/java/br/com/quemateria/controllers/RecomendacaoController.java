package br.com.quemateria.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quemateria.dto.horario.HorarioAulaMapper;
import br.com.quemateria.dto.horario.RecomendacaoDTO;
import br.com.quemateria.entities.Disciplina;
import br.com.quemateria.entities.HorarioAula;
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

	@GetMapping("{dia}/{horario}")
	public ResponseEntity<List<RecomendacaoDTO>> listarTop5Recomendacoes(@PathVariable Integer dia,
			@PathVariable String horario) {
		
		Integer quantidadeDeDisciplinasCursadas=0;
		Long cursoId = alunoService.getUltimoAlunoCadastrado().getCurso().getId();
		recomendacaoService.calcularPeso();

		Set<Disciplina> disciplinasCursadas = alunoService.getUltimoAlunoCadastrado().getDisciplinasCursadas();
		List<HorarioAula> recomendacao = recomendacaoService.recomendarMateriasPorHorarioAula(cursoId, dia, horario);
		List<HorarioAula> listaRemocao = new ArrayList<>();

		for (HorarioAula horarioAula : recomendacao)
			if (disciplinasCursadas.contains(horarioAula.getDisciplina())) {
				listaRemocao.add(horarioAula);
				quantidadeDeDisciplinasCursadas++;
			}
				
		recomendacao.removeAll(listaRemocao);
		
		return ResponseEntity.ok(horarioAulaMapper.toListRecomendacaoDTO(recomendacao.subList(0, quantidadeDeDisciplinasCursadas + 4)));

	}

}
