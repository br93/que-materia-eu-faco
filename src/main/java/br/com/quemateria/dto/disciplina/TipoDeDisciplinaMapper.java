package br.com.quemateria.dto.disciplina;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.quemateria.entities.TipoDeDisciplina;

@Mapper(componentModel = "spring")
public interface TipoDeDisciplinaMapper {
	
	@Mapping(target = "tipo", source = "tipoNome")
	ConsultaTipoDeDisciplinaDTO toDTO (TipoDeDisciplina tipoDeDisciplina);

}
