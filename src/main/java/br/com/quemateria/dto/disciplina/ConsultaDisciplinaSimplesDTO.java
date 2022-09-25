package br.com.quemateria.dto.disciplina;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaDisciplinaSimplesDTO {

	private String nome;
	private String codigo;
	private Set<ConsultaDisciplinaSimplesDTO> requisitos;

}
