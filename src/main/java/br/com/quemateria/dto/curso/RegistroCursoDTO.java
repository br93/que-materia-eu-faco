package br.com.quemateria.dto.curso;

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
public class RegistroCursoDTO {
	
	@NotBlank(message = "Nome é campo obrigatório")
	private String nome;
	
	@NotBlank(message = "Código do curso é campo obrigatório") 
	@Length(min = 3, max = 3, message = "Quantidade de digitos permitidos: 3")
	@Pattern(regexp = "^[0-9]{3}$", message = "Formato 000")
	private String codigo;
	
	@NotNull(message = "Quantidade de periodos é campo obrigatório") 
	@Min(value = 1, message = "Valor permitido (mínimo): 1")
	@Max(value = 10, message = "Valor permitido (máximo): 10")
	private Integer quantidadeDePeriodos;

}
