package br.com.quemateria.dto.curso;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroCursoDTO {
	
	private String nome;
	private String matriz;
	private Integer periodos;

}
