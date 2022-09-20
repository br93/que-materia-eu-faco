package br.com.quemateria.dto.disciplina;

import java.util.Set;

import br.com.quemateria.dto.horario.ConsultaHorarioDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaDisciplinaDTO {
	
	private String nome;
	private String codigo;
	private Integer periodo;
	private String curso;
	private String tipo;
	private Set<ConsultaHorarioDTO> horarios;
	private Set<ConsultaDisciplinaDTO> requisitos;

}
