package br.com.quemateria.entities;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tb_disciplinas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Disciplina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	
	private String codigo;

	private Integer periodo;

	private Integer cargaHoraria;
	
	private Integer preRequisito;

	private Double peso;

	@ManyToOne
	private Curso curso;

	@ManyToOne
	private TipoDeDisciplina tipoDeDisciplina;
	
	@OneToMany(mappedBy = "disciplina")
	private Set<Turma> turmas;

	@ManyToMany
	@OrderBy(value = "periodo ASC")
	@JoinTable(name = "tb_disciplinas_requisitos", joinColumns = @JoinColumn(name = "disciplina_id"), inverseJoinColumns = @JoinColumn(name = "requisito_id"))
	private Set<Disciplina> requisitos;

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Disciplina other = (Disciplina) obj;
		return Objects.equals(codigo, other.codigo);
	}
	
	
	
	
	

	

}
