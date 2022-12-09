package br.com.quemateria.dto.aluno;

import java.util.Set;

import br.com.quemateria.dto.disciplina.RegistroDisciplinaSimplesDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoCadastroDisciplinasDTO {
	
	private Integer periodo;
	private Set<RegistroDisciplinaSimplesDTO> disciplinas;

}
