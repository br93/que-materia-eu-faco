package br.com.quemateria.dto.curso;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaCursoDTO {
	
	private String nome;
	private Integer duracao;

}
