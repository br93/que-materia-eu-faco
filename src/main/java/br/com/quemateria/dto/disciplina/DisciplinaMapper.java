package br.com.quemateria.dto.disciplina;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.quemateria.dto.disciplina.DisciplinaUtil.GetCurso;
import br.com.quemateria.dto.disciplina.DisciplinaUtil.GetHorario;
import br.com.quemateria.dto.disciplina.DisciplinaUtil.GetId;
import br.com.quemateria.dto.disciplina.DisciplinaUtil.GetNome;
import br.com.quemateria.dto.disciplina.DisciplinaUtil.GetPeriodo;
import br.com.quemateria.dto.disciplina.DisciplinaUtil.GetRequisitos;
import br.com.quemateria.dto.disciplina.DisciplinaUtil.GetTipoDeDisciplina;
import br.com.quemateria.entities.Disciplina;
import br.com.quemateria.services.CursoService;
import br.com.quemateria.services.HorarioService;
import br.com.quemateria.services.TipoDeDisciplinaService;

@Mapper(componentModel = "spring", uses = {HorarioService.class, CursoService.class, TipoDeDisciplinaService.class, DisciplinaUtil.class})
public interface DisciplinaMapper {
	
	@Mapping(target = "curso", source = "curso.nome")
	@Mapping(target = "tipo", source = "tipoDeDisciplina.tipoNome")
	@Mapping(target = "horario", source = "horario.sigla")
	@Mapping(target = "faixaDeHorario", source = "horario.faixa")
	ConsultaDisciplinaDTO toDTO (Disciplina disciplina);
	
	ConsultaDisciplinaCursadaDTO toDisciplinaCursadaDTO (Disciplina disciplina);
	
	@Mapping(target = "id", source = "codigo", qualifiedBy = GetId.class)
	@Mapping(target = "curso", source = "codigo", qualifiedBy = GetCurso.class)
	@Mapping(target = "horario", source = "codigo", qualifiedBy = GetHorario.class)
	@Mapping(target = "periodo", source = "codigo", qualifiedBy = GetPeriodo.class)
	@Mapping(target = "requisitos", source = "codigo", qualifiedBy = GetRequisitos.class)
	@Mapping(target = "tipoDeDisciplina", source = "codigo", qualifiedBy = GetTipoDeDisciplina.class)
	@Mapping(target = "nome", source = "codigo", qualifiedBy = GetNome.class)
	Disciplina toEntityFromDisciplinaCursada (ConsultaDisciplinaCursadaDTO dto);

}
