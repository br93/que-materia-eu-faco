package br.com.quemateria.dto.horario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
