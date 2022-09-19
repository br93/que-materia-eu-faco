package br.com.quemateria.dto.disciplina;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.quemateria.entities.Disciplina;

@Mapper(componentModel = "spring")
public interface DisciplinaMapper {
	
	@Mapping(target = "curso", source = "curso.nome")
	@Mapping(target = "tipo", source = "tipoDeDisciplina.tipoNome")
	@Mapping(target = "horario", source = "horario.sigla")
	@Mapping(target = "faixaDeHorario", source = "horario.faixa")
	ConsultaDisciplinaDTO toDTO (Disciplina disciplina);

}
