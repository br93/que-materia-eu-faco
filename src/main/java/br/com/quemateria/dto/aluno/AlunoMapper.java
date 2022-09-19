package br.com.quemateria.dto.aluno;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.quemateria.dto.disciplina.DisciplinaMapper;
import br.com.quemateria.entities.Aluno;

@Mapper(componentModel = "spring", uses = DisciplinaMapper.class)
public interface AlunoMapper {
	
	@Mapping(target = "curso", source = "curso.nome" )
	ConsultaAlunoDTO toDTO (Aluno aluno);

}
