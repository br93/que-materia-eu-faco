package br.com.quemateria.dto.matriz;

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
public class RegistroMatrizDTO {
	
	@NotBlank(message = "Código do curso é campo obrigatório") 
	@Length(min = 3, max = 3, message = "Quantidade de digitos permitidos: 3")
	@Pattern(regexp = "^[0-9]{3}$", message = "Formato 000")
	private String curso;
	
	@NotBlank(message = "Código da disciplina é campo obrigatório")
	@Length(min = 5, message = "Quantidade de digitos permitidos (mínimo): 5")
	@Length(max = 6, message = "Quantidade de digitos permitidos (máximo): 6")
	@Pattern(regexp = "(^[A-Z0-9]{5}|^[A-Z0-9]{6})$", message = "Formato XXXXX ou XXXXXX")
	private String disciplina;
	
	@NotNull(message = "Código do tipo de disciplina é campo obrigatório")
	@Min(value = 1, message = "Valor permitido (mínimo): 1")
	@Max(value = 4, message = "Valor permitido (máximo): 4")
	private Long tipoDeDisciplina;
	
	@NotNull(message = "Período da disciplina na matriz curricular é campo obrigatório")
	@Min(value = 1, message = "Valor permitido (mínimo): 1")
	@Max(value = 10, message = "Valor permitido (máximo): 10")
	private Integer periodo;
	
	@NotNull(message = "Quantidade de disciplinas que dependem desta na matriz curricular é campo obrigatório")
	private Integer preRequisitos;
	
}
