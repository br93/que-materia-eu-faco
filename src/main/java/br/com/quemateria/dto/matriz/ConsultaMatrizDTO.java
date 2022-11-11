package br.com.quemateria.dto.matriz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaMatrizDTO {
	
	private String curso;
	private String disciplina;
	private String tipoDeDisciplina;
	private Integer periodo;
	private Integer preRequisitos;
	private Double peso;
	
}
