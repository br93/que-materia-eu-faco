package br.com.quemateria.dto.matriz;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.quemateria.entities.ItemMatrizCurricular;
import br.com.quemateria.services.CursoService;
import br.com.quemateria.services.DisciplinaService;
import br.com.quemateria.services.ItemMatrizCurricularService;
import br.com.quemateria.services.TipoDeDisciplinaService;

@Mapper(componentModel = "spring", uses = { ItemMatrizCurricularService.class, TipoDeDisciplinaService.class,
		CursoService.class, DisciplinaService.class })
public interface MatrizCurricularMapper {

	@Mapping(target = "curso", source = "curso.nome")
	@Mapping(target = "disciplina", source = "disciplina.nome")
	@Mapping(target = "tipoDeDisciplina", source = "tipoDeDisciplina.tipoNome")
	ConsultaMatrizDTO toDTO(ItemMatrizCurricular matrizCurricular);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "peso", ignore = true)
	ItemMatrizCurricular toEntity(RegistroMatrizDTO dto);

}
