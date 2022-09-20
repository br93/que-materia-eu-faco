package br.com.quemateria.dto.disciplina;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.quemateria.dto.disciplina.DisciplinaUtil.GetCargaHoraria;
import br.com.quemateria.dto.disciplina.DisciplinaUtil.GetCodigo;
import br.com.quemateria.dto.disciplina.DisciplinaUtil.GetCurso;
import br.com.quemateria.dto.disciplina.DisciplinaUtil.GetHorarios;
import br.com.quemateria.dto.disciplina.DisciplinaUtil.GetId;
import br.com.quemateria.dto.disciplina.DisciplinaUtil.GetNome;
import br.com.quemateria.dto.disciplina.DisciplinaUtil.GetPeriodo;
import br.com.quemateria.dto.disciplina.DisciplinaUtil.GetPreRequisito;
import br.com.quemateria.dto.disciplina.DisciplinaUtil.GetRequisitos;
import br.com.quemateria.dto.disciplina.DisciplinaUtil.GetTipoDeDisciplina;
import br.com.quemateria.entities.Disciplina;
import br.com.quemateria.services.CursoService;
import br.com.quemateria.services.TipoDeDisciplinaService;

@Mapper(componentModel = "spring", uses = {CursoService.class, TipoDeDisciplinaService.class, DisciplinaUtil.class})
public interface DisciplinaMapper {
	
	@Mapping(target = "curso", source = "curso.nome")
	@Mapping(target = "tipo", source = "tipoDeDisciplina.tipoNome")
	ConsultaDisciplinaDTO toDTO (Disciplina disciplina);
	
	ConsultaDisciplinaSimplesDTO toSimplesDTO (Disciplina disciplina);
	
	@Mapping(target = "id", source = "turma", qualifiedBy = GetId.class)
	@Mapping(target = "curso", source = "turma", qualifiedBy = GetCurso.class)
	@Mapping(target = "periodo", source = "turma", qualifiedBy = GetPeriodo.class)
	@Mapping(target = "requisitos", source = "turma", qualifiedBy = GetRequisitos.class)
	@Mapping(target = "tipoDeDisciplina", source = "turma", qualifiedBy = GetTipoDeDisciplina.class)
	@Mapping(target = "nome", source = "turma", qualifiedBy = GetNome.class)
	@Mapping(target = "cargaHoraria", source = "turma", qualifiedBy = GetCargaHoraria.class)
	@Mapping(target = "horarios", source = "turma", qualifiedBy = GetHorarios.class)
	@Mapping(target = "codigo", source = "turma", qualifiedBy = GetCodigo.class)
	@Mapping(target = "preRequisito", source = "turma", qualifiedBy = GetPreRequisito.class)
	@Mapping(target = "peso", ignore = true)
	Disciplina toEntityFromDisciplinaSimples (RegistroDisciplinaSimplesDTO dto);
	
	List<ConsultaDisciplinaDTO> toListCompletoDTO (List<Disciplina> disciplina);
	List<ConsultaDisciplinaSimplesDTO> toListSimplesDTO (List<Disciplina> disciplina);
	
	
}
