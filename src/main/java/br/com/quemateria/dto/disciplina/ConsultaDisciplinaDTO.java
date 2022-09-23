package br.com.quemateria.dto.disciplina;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaDisciplinaDTO {
	
	private String nome;
	private String codigo;
	//private String turma;
	private Integer periodo;
	private String curso;
	private String tipo;
	//private Set<ConsultaHorarioAulaDTO> horarios;
	private Set<ConsultaDisciplinaDTO> requisitos;

}
