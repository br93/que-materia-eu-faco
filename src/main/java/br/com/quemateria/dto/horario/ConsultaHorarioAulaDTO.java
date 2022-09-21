package br.com.quemateria.dto.horario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaHorarioAulaDTO {
	
	private String dia;
	private String sigla;
	private String faixa;

}
