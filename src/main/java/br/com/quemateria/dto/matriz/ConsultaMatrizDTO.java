package br.com.quemateria.dto.matriz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaMatrizDTO {
	
	private String curso;
	private String disciplina;
	private String tipoDeDisciplina;
	private Integer periodo;

}
