package br.com.quemateria.dto.matriz;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.quemateria.entities.MatrizCurricular;
import br.com.quemateria.services.CursoService;
import br.com.quemateria.services.DisciplinaService;
import br.com.quemateria.services.MatrizCurricularService;
import br.com.quemateria.services.TipoDeDisciplinaService;

@Mapper(componentModel = "spring", uses = { MatrizCurricularService.class, TipoDeDisciplinaService.class,
		CursoService.class, DisciplinaService.class })
public interface MatrizCurricularMapper {

	@Mapping(target = "curso", source = "curso.nome")
	@Mapping(target = "disciplina", source = "disciplina.nome")
	@Mapping(target = "tipoDeDisciplina", source = "tipoDeDisciplina.tipoNome")
	ConsultaMatrizDTO toDTO(MatrizCurricular matrizCurricular);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "peso", ignore = true)
	MatrizCurricular toEntity(RegistroMatrizDTO dto);

}
