package br.com.quemateria.dto.curso;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroCursoDTO {
	
	private String nome;
	private String matriz;
	private Integer periodos;

}
