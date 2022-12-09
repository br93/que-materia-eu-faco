package br.com.quemateria.dto.aluno;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.quemateria.dto.disciplina.DisciplinaMapper;
import br.com.quemateria.entities.Aluno;
import br.com.quemateria.services.CursoService;
import br.com.quemateria.services.DisciplinaService;

@Mapper(componentModel = "spring", uses = {DisciplinaMapper.class, CursoService.class, DisciplinaService.class})
public interface AlunoMapper {
	
	@Mapping(target = "curso", source = "curso.codigo" )
	ConsultaAlunoDTO toDTO (Aluno aluno);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "curso", source = "curso")
	@Mapping(target = "disciplinasCursadas", source = "disciplinas")
	@Mapping(target = "login", ignore = true)
	Aluno toEntity (RegistroAlunoDTO dto);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "nome", ignore = true)
	@Mapping(target = "registro", ignore = true)
	@Mapping(target = "curso", ignore = true)
	@Mapping(target = "login", ignore = true)
	@Mapping(target = "disciplinasCursadas", source = "disciplinas")
	Aluno toEntity (AlunoCadastroDisciplinasDTO dto);
	
	List<ConsultaAlunoDTO> toListDTO (List<Aluno> alunos);

}
