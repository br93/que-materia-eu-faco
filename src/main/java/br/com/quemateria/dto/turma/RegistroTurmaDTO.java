package br.com.quemateria.dto.turma;

import javax.validation.constraints.NotBlank;
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
public class RegistroTurmaDTO {
	
	@NotBlank(message = "Código da turma é campo obrigatório") 
	@Length(min = 9, message = "Quantidade de digitos permitidos (mínimo): 9")
	@Length(max = 10, message = "Quantidade de digitos permitidos (máximo): 10")
	@Pattern(regexp = "(^[A-Z0-9]{5}|^[A-Z0-9]{6})-[A-Z]{1}[0-9]{2}$", message = "Formato XXXXX-A00 ou XXXXXX-A00")
	private String codigo;
	
	@NotBlank(message = "Código da disciplina é campo obrigatório")
	@Length(min = 5, message = "Quantidade de digitos permitidos (mínimo): 5")
	@Length(max = 6, message = "Quantidade de digitos permitidos (máximo): 6 digitos")
	@Pattern(regexp = "(^[A-Z0-9]{5}|^[A-Z0-9]{6})$", message = "Formato XXXXX ou XXXXXX")
	private String disciplina;

}
