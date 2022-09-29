package br.com.quemateria.dto.matriz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroMatrizDTO {
	
	private String curso;
	private String disciplina;
	private Long tipoDeDisciplina;
	private Integer periodo;
	private Integer preRequisitos;
	
}
