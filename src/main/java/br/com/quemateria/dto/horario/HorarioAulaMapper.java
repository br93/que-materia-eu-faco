package br.com.quemateria.dto.horario;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.quemateria.entities.HorarioAula;
import br.com.quemateria.services.DiaService;
import br.com.quemateria.services.HorarioService;
import br.com.quemateria.services.TurmaService;

@Mapper(componentModel = "spring", uses = {HorarioService.class, DiaService.class, TurmaService.class})
public interface HorarioAulaMapper {
	
	@Mapping(target = "codigo", source = "turma.codigo")
	@Mapping(target = "disciplina", source = "turma.disciplina.nome")
	ConsultaHorarioAulaSimplesDTO toDTOSimples (HorarioAula horario);
	
	@Mapping(target = "codigo", source = "turma.disciplina.codigo")
	@Mapping(target = "nome", source = "turma.disciplina.nome")
	@Mapping(target = "turma", source = "turma.codigo")
	@Mapping(target = "dia", source = "dia.identificador")
	@Mapping(target = "horario", source = "horario.sigla")
	ConsultaHorarioAulaDTO toDTO (HorarioAula horario);
	
	@Mapping(target = "codigo", source = "turma.disciplina.codigo")
	@Mapping(target = "nome", source = "turma.disciplina.nome")
	@Mapping(target = "turma", source = "turma.codigo")
	@Mapping(target = "dia", source = "dia.identificador")
	@Mapping(target = "horario", source = "horario.sigla")
	ConsultaHorarioAulaCompletoDTO toDTOCompleto (HorarioAula horario);
	
	@Mapping(target = "id", ignore = true)
	HorarioAula toEntity (RegistroHorarioAulaDTO dto);
	
	List<ConsultaHorarioAulaDTO> toListRecomendacaoDTO (List<HorarioAula> horarios);
	Set<ConsultaHorarioAulaDTO> toSetRecomendacaoDTO (Set<HorarioAula> horarios);
	
	List<ConsultaHorarioAulaSimplesDTO> toListConsultaDTO (List<HorarioAula> horarios);
	Set<ConsultaHorarioAulaSimplesDTO> toSetConsultaDTO (Set<HorarioAula> horarios);
	
	List<ConsultaHorarioAulaCompletoDTO> toListCompletoDTO (List<HorarioAula> horarios);

}
