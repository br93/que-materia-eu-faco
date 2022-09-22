package br.com.quemateria.dto.horario;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.quemateria.entities.HorarioAula;

@Mapper(componentModel = "spring")
public interface HorarioAulaMapper {
	
	@Mapping(target = "dia", source = "dia.dia")
	@Mapping(target = "faixa", source = "horario.faixa")
	@Mapping(target = "sigla", source = "horario.sigla")
	ConsultaHorarioAulaDTO toDTO (HorarioAula horario);
	
	@Mapping(target = "codigo", source = "disciplina.codigo")
	@Mapping(target = "nome", source = "disciplina.nome")
	@Mapping(target = "turma", source = "disciplina.turma")
	@Mapping(target = "dia", source = "dia.identificador")
	@Mapping(target = "horario", source = "horario.sigla")
	RecomendacaoDTO toRecomendacaoDTO (HorarioAula horario);
	
	List<RecomendacaoDTO> toListRecomendacaoDTO (List<HorarioAula> horarios);
	Set<RecomendacaoDTO> toSetRecomendacaoDTO (Set<HorarioAula> horarios);

}
