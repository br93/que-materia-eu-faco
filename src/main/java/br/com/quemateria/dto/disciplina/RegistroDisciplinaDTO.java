package br.com.quemateria.dto.disciplina;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroDisciplinaDTO {
	
	@NotBlank(message = "Nome é campo obrigatório")
	private String nome;
	
	@NotBlank(message = "Código da disciplina é campo obrigatório")
	@Length(min = 5, message = "Quantidade de digitos permitidos (mínimo): 5")
	@Length(max = 6, message = "Quantidade de digitos permitidos (máximo): 6")
	@Pattern(regexp = "(^[A-Z0-9]{5}|^[A-Z0-9]{6})$", message = "Formato XXXXX ou XXXXXX")
	private String codigo;
	
	@NotNull(message = "Carga horaria é campo obrigatório") 
	@Min(value = 15, message = "Valor permitido (mínimo): 15")
	@Max(value = 150, message = "Valor permitido (máximo): 150")
	private Integer cargaHoraria;

}
