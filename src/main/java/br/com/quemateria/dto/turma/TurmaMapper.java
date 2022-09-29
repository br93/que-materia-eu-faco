package br.com.quemateria.dto.turma;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.quemateria.dto.horario.HorarioAulaMapper;
import br.com.quemateria.entities.Turma;
import br.com.quemateria.services.DisciplinaService;

@Mapper(componentModel = "spring", uses = {HorarioAulaMapper.class, DisciplinaService.class})
public interface TurmaMapper {
	
	@Mapping(target = "disciplina", source = "disciplina.nome")
	ConsultaTurmaDTO toDTO (Turma turma);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "horarios", ignore = true)
	Turma toEntity (RegistroTurmaDTO dto);

}
