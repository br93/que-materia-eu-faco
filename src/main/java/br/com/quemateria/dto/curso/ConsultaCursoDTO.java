package br.com.quemateria.dto.curso;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaCursoDTO {
	
	private String nome;
	private String codigo;
	private Integer duracao;
	

}
