package br.com.quemateria.dto.disciplina;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroDisciplinaDTO {
	
	private String nome;
	private String codigo;
	private Integer cargaHoraria;

}
