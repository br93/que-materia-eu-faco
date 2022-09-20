package br.com.quemateria.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tb_cursos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String matriz;
	private Integer periodos;
	
	@OneToMany(mappedBy = "curso")
	private Set<Disciplina> disciplinas;
}
