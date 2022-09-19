package br.com.quemateria.dto.aluno;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.quemateria.dto.disciplina.DisciplinaMapper;
import br.com.quemateria.entities.Aluno;
import br.com.quemateria.services.CursoService;
import br.com.quemateria.services.DisciplinaService;
import br.com.quemateria.services.HorarioService;

@Mapper(componentModel = "spring", uses = {DisciplinaMapper.class, CursoService.class, HorarioService.class, DisciplinaService.class})
public interface AlunoMapper {
	
	@Mapping(target = "curso", source = "curso.matriz" )
	ConsultaAlunoDTO toDTO (Aluno aluno);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "curso", source = "curso")
	@Mapping(target = "disciplinasCursadas", source = "disciplinas")
	Aluno toEntity (RegistroAlunoDTO dto);

}
