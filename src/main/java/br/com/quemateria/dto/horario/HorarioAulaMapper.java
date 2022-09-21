package br.com.quemateria.dto.horario;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.quemateria.entities.HorarioAula;

@Mapper(componentModel = "spring")
public interface HorarioAulaMapper {
	
	@Mapping(target = "dia", source = "dia.dia")
	@Mapping(target = "faixa", source = "horario.faixa")
	@Mapping(target = "sigla", source = "horario.sigla")
	ConsultaHorarioAulaDTO toDTO (HorarioAula horario);

}
