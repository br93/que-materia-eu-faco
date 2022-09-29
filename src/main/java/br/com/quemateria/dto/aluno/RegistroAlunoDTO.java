package br.com.quemateria.dto.aluno;

import java.util.Set;

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
	
	private String nome;
	private String registro;
	private Integer periodo;
	private String curso;
	private Set<RegistroDisciplinaSimplesDTO> disciplinas;

}
