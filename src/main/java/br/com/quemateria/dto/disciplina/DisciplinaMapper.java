package br.com.quemateria.dto.disciplina;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.quemateria.dto.disciplina.DisciplinaUtil.GetCargaHoraria;
import br.com.quemateria.dto.disciplina.DisciplinaUtil.GetCurso;
import br.com.quemateria.dto.disciplina.DisciplinaUtil.GetId;
import br.com.quemateria.dto.disciplina.DisciplinaUtil.GetNome;
import br.com.quemateria.dto.disciplina.DisciplinaUtil.GetPeriodo;
import br.com.quemateria.dto.disciplina.DisciplinaUtil.GetPreRequisito;
import br.com.quemateria.dto.disciplina.DisciplinaUtil.GetRequisitos;
import br.com.quemateria.dto.disciplina.DisciplinaUtil.GetTipoDeDisciplina;
import br.com.quemateria.dto.disciplina.DisciplinaUtil.GetTurmas;
import br.com.quemateria.dto.horario.HorarioAulaMapper;
import br.com.quemateria.entities.Disciplina;
import br.com.quemateria.services.CursoService;

@Mapper(componentModel = "spring", uses = {CursoService.class, DisciplinaUtil.class, HorarioAulaMapper.class})
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
	@Mapping(target = "turmas", source = "codigo", qualifiedBy = GetTurmas.class)
	@Mapping(target = "preRequisito", source = "codigo", qualifiedBy = GetPreRequisito.class)
	@Mapping(target = "peso", ignore = true)
	Disciplina toEntityFromDisciplinaSimples (RegistroDisciplinaSimplesDTO dto);
	
	List<ConsultaDisciplinaDTO> toListCompletoDTO (List<Disciplina> disciplina);
	List<ConsultaDisciplinaSimplesDTO> toListSimplesDTO (List<Disciplina> disciplina);

	Set<ConsultaDisciplinaDTO> toSetCompletoDTO (Set<Disciplina> disciplina);
	Set<ConsultaDisciplinaSimplesDTO> toSetSimplesDTO(Set<Disciplina> disciplinasCursadas);
	
	
}
