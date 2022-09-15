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

@Entity(name = "disciplinas_db")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Disciplina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private Integer periodo;
	
	@ManyToOne
	private Curso curso;
	
	@ManyToOne
	private TipoDeDisciplina tipoDeDisciplina;
	
	@ManyToOne
	private Horario horario;
	
	@ManyToMany
	@JoinTable(
			  name = "disciplinas_requisitos_db", 
			  joinColumns = @JoinColumn(name = "disciplina_id"), 
			  inverseJoinColumns = @JoinColumn(name = "requisito_id"))
	private Set<Disciplina> requisitos;
	
	@ManyToMany(mappedBy = "disciplinasCursadas")
	private Set<Aluno> alunos;
	
	
	
	
	
	
	
	

}
