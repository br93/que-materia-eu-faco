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
	private Integer periodo;
	private String curso;
	private String tipo;
	private String horario;
	private String faixaDeHorario;
	private Set<ConsultaDisciplinaDTO> requisitos;

}
