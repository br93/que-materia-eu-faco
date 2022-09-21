package br.com.quemateria.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tb_horario_aula")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HorarioAula {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Disciplina disciplina;
	
	@ManyToOne
	private Dia dia;
	
	@ManyToOne
	private Horario horario;
	
	

}
