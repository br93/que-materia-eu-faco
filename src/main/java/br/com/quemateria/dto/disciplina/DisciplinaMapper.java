package br.com.quemateria.dto.disciplina;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.quemateria.dto.disciplina.DisciplinaUtil.GetCargaHoraria;
import br.com.quemateria.dto.disciplina.DisciplinaUtil.GetCurso;
import br.com.quemateria.dto.disciplina.DisciplinaUtil.GetHorarios;
import br.com.quemateria.dto.disciplina.DisciplinaUtil.GetId;
import br.com.quemateria.dto.disciplina.DisciplinaUtil.GetNome;
import br.com.quemateria.dto.disciplina.DisciplinaUtil.GetPeriodo;
import br.com.quemateria.dto.disciplina.DisciplinaUtil.GetRequisitos;
import br.com.quemateria.dto.disciplina.DisciplinaUtil.GetTipoDeDisciplina;
import br.com.quemateria.dto.disciplina.DisciplinaUtil.GetTurma;
import br.com.quemateria.entities.Disciplina;
import br.com.quemateria.services.CursoService;
import br.com.quemateria.services.TipoDeDisciplinaService;

@Mapper(componentModel = "spring", uses = {CursoService.class, TipoDeDisciplinaService.class, DisciplinaUtil.class})
public interface DisciplinaMapper {
	
	@Mapping(target = "curso", source = "curso.nome")
	@Mapping(target = "tipo", source = "tipoDeDisciplina.tipoNome")
	ConsultaDisciplinaDTO toDTO (Disciplina disciplina);
	
	ConsultaDisciplinaSimplesDTO toSimplesDTO (Disciplina disciplina);
	
	@Mapping(target = "id", source = "codigo", qualifiedBy = GetId.class)
	@Mapping(target = "curso", source = "codigo", qualifiedBy = GetCurso.class)
	@Mapping(target = "periodo", source = "codigo", qualifiedBy = GetPeriodo.class)
	@Mapping(target = "requisitos", source = "codigo", qualifiedBy = GetRequisitos.class)
	@Mapping(target = "tipoDeDisciplina", source = "codigo", qualifiedBy = GetTipoDeDisciplina.class)
	@Mapping(target = "nome", source = "codigo", qualifiedBy = GetNome.class)
	@Mapping(target = "cargaHoraria", source = "codigo", qualifiedBy = GetCargaHoraria.class)
	@Mapping(target = "horarios", source = "codigo", qualifiedBy = GetHorarios.class)
	@Mapping(target = "turma", source = "codigo", qualifiedBy = GetTurma.class)
	@Mapping(target = "peso", ignore = true)
	Disciplina toEntityFromDisciplinaSimples (ConsultaDisciplinaSimplesDTO dto);
	
	Set<ConsultaDisciplinaDTO> toSetCompletoDTO (Set<Disciplina> disciplina);
	Set<ConsultaDisciplinaSimplesDTO> toSetSimplesDTO (Set<Disciplina> disciplina);
}
