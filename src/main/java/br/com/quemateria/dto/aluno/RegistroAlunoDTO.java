package br.com.quemateria.dto.aluno;

import java.util.Set;

import br.com.quemateria.dto.disciplina.ConsultaDisciplinaSimplesDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroAlunoDTO {
	
	private String nome;
	private String registro;
	private Integer periodo;
	private String curso;
	private Set<ConsultaDisciplinaSimplesDTO> disciplinas;

}
