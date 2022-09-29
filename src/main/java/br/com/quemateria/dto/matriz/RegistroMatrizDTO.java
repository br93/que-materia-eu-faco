package br.com.quemateria.dto.matriz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroMatrizDTO {
	
	private String curso;
	private String disciplina;
	private Long tipoDeDisciplina;
	private Integer periodo;
	private Integer preRequisitos;
	
}
