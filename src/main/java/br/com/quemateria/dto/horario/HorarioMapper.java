package br.com.quemateria.dto.horario;

import org.mapstruct.Mapper;

import br.com.quemateria.entities.Horario;

@Mapper(componentModel = "spring")
public interface HorarioMapper {
	
	ConsultaHorarioDTO toDTO (Horario horario);

}
