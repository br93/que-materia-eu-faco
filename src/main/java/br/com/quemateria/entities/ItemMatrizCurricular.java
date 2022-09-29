package br.com.quemateria.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tb_matrizes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemMatrizCurricular {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "curso_id")
	private Curso curso;
	
	@ManyToOne(fetch = FetchType.LAZY)
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
