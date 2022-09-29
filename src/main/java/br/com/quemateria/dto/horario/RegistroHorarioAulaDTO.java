package br.com.quemateria.dto.horario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroHorarioAulaDTO {
	
	private String turma;
	private Integer dia;
	private String horario;

}
