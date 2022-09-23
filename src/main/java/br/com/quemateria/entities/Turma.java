package br.com.quemateria.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tb_turmas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Turma {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String codigo;

	@ManyToOne
	private Disciplina disciplina;
	
	@OneToMany(mappedBy = "turma")
	@OrderBy(value = "id ASC")
	private Set<HorarioAula> horarios;

}
