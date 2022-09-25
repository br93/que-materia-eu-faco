package br.com.quemateria.dto.horario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaHorarioAulaDTO {
	
	private String codigo;
	private String disciplina;
}
