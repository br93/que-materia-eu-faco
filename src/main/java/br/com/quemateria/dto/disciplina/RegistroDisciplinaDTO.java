package br.com.quemateria.dto.disciplina;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroDisciplinaDTO {
	
	private String nome;
	private String codigo;
	private Integer cargaHoraria;

}
