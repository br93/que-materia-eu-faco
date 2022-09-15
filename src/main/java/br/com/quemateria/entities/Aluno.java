package br.com.quemateria.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "alunos_db")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String registro;
	private Integer periodo;
	
	@ManyToOne
	private Curso curso;
	
	@ManyToMany
	@JoinTable(
			  name = "alunos_disciplinas_db", 
			  joinColumns = @JoinColumn(name = "aluno_id"), 
			  inverseJoinColumns = @JoinColumn(name = "disciplina_id"))
	private Set<Disciplina> disciplinasCursadas;

}
