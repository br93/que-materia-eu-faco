package br.com.quemateria.dto.aluno;

import java.util.Set;

import br.com.quemateria.dto.disciplina.ConsultaDisciplinaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaAlunoDTO {
	
	private String nome;
	private String registro;
	private Integer periodo;
	private String curso;
	private Set<ConsultaDisciplinaDTO> disciplinasCursadas;

}
