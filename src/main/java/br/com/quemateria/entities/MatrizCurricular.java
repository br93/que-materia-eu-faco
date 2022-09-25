package br.com.quemateria.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_matrizes")
public class MatrizCurricular {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "curso_id")
	private Curso curso;
	
	@ManyToOne
	@JoinColumn(name = "disciplina_id")
	private Disciplina disciplina;
	
	@ManyToOne
	@JoinColumn(name = "tipo_disciplina_id")
	private TipoDeDisciplina tipoDeDisciplina;
	
	@Column(name = "periodo")
	private Integer periodo;
	
	@Column(name = "pre_requisitos")
	private Integer preRequisitos;
	
	@Column(name = "peso")
	private Double peso;
	
	
	

}
