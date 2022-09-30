package br.com.quemateria.dto.aluno;

import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import br.com.quemateria.dto.disciplina.RegistroDisciplinaSimplesDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroAlunoDTO {
	
	@NotBlank(message = "Nome é campo obrigatório")
	private String nome;
	
	@NotBlank(message = "Registro é campo obrigatório") 
	@Length (min = 8, max = 8,  message = "Quantidade de digitos permitidos: 8") 
	@Pattern(regexp = "^[a][0-9]{7}", message = "Formato a0000000")
	private String registro;
	
	@NotNull(message = "Periodo é campo obrigatório") 
	@Min(value = 1, message = "Valor permitido (minimo): 1")
	@Max(value = 10, message = "Valor permitido (máximo): 10")
	private Integer periodo;
	
	@NotBlank(message = "Código do curso é campo obrigatório") 
	@Length(min = 3, max = 3, message = "Quantidade de digitos permitidos: 3")
	@Pattern(regexp = "^[0-9]{3}$", message = "Formato 000")
	private String curso;
	
	private Set<RegistroDisciplinaSimplesDTO> disciplinas;

}
