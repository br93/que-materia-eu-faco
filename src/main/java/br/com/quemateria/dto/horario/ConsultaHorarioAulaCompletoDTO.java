package br.com.quemateria.dto.horario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaHorarioAulaCompletoDTO {
	
	private Long id;
	private String nome;
	private String codigo;
	private String turma;
	private String dia;
	private String horario;

}
