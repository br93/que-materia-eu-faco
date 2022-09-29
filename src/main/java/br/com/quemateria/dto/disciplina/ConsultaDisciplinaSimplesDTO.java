package br.com.quemateria.dto.disciplina;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaDisciplinaSimplesDTO {

	private String nome;
	private String codigo;
	private Set<ConsultaDisciplinaSimplesDTO> requisitos;

}
