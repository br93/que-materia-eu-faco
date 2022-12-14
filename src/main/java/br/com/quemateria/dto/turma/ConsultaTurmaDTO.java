package br.com.quemateria.dto.turma;

import java.util.List;

import br.com.quemateria.dto.horario.ConsultaHorarioAulaDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaTurmaDTO {
	
	private String codigo;
	private String disciplina;
	private List<ConsultaHorarioAulaDTO> horarios;

}
