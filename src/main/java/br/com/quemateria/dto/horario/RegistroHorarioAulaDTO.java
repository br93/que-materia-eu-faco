package br.com.quemateria.dto.horario;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroHorarioAulaDTO {
	
	@NotBlank(message = "Código da turma é campo obrigatório") 
	@Length(min = 9, message = "Quantidade de digitos permitidos (mínimo): 9")
	@Length(max = 10, message = "Quantidade de digitos permitidos (máximo): 10")
	@Pattern(regexp = "(^[A-Z0-9]{5}|^[A-Z0-9]{6})-[A-Z]{1}[0-9]{2}$", message = "Formato XXXXX-A00 ou XXXXXX-A00")
	private String turma;
	
	@NotNull(message = "Dia é campo obrigatório")
	@Min(value = 2, message = "Valor permitido (mínimo): 2 = segunda-feira")
	@Max(value = 6, message = "Valor permitido (máximo): 6 = sexta-feira")
	private Integer dia;
	
	@NotBlank(message = "Horario é campo obrigatório")
	@Length(min = 2, max = 2, message = "Quantidade de digitos permitidos: 2")
	@Pattern(regexp = "(^[MT][1-6]|^[N][1-5])$", message = "Formato X0")
	private String horario;

}
