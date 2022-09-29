package br.com.quemateria.dto.curso;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.quemateria.entities.Curso;

@Mapper(componentModel = "spring")
public interface CursoMapper {
	
	@Mapping(target = "duracao", source = "periodos")
	ConsultaCursoDTO toDTO (Curso curso);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "disciplinas", ignore = true)
	Curso toEntity (RegistroCursoDTO dto);

}
