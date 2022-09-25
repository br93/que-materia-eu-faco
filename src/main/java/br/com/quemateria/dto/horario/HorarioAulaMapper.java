package br.com.quemateria.dto.horario;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.quemateria.entities.HorarioAula;

@Mapper(componentModel = "spring")
public interface HorarioAulaMapper {
	
	@Mapping(target = "codigo", source = "turma.codigo")
	@Mapping(target = "disciplina", source = "turma.disciplina.nome")
	ConsultaHorarioAulaDTO toDTO (HorarioAula horario);
	
	@Mapping(target = "codigo", source = "turma.disciplina.codigo")
	@Mapping(target = "nome", source = "turma.disciplina.nome")
	@Mapping(target = "turma", source = "turma.codigo")
	@Mapping(target = "dia", source = "dia.identificador")
	@Mapping(target = "horario", source = "horario.sigla")
	RecomendacaoDTO toRecomendacaoDTO (HorarioAula horario);
	
	List<RecomendacaoDTO> toListRecomendacaoDTO (List<HorarioAula> horarios);
	Set<RecomendacaoDTO> toSetRecomendacaoDTO (Set<HorarioAula> horarios);
	
	List<ConsultaHorarioAulaDTO> toListConsultaDTO (List<HorarioAula> horarios);
	Set<ConsultaHorarioAulaDTO> toSetConsultaDTO (Set<HorarioAula> horarios);

}
