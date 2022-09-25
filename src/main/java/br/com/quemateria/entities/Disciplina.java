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
import javax.persistence.OneToMany;

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

	private Integer cargaHoraria;
	
	@OneToMany (mappedBy = "disciplina")
	private Set<MatrizCurricular> matrizCurricular;

	@OneToMany(mappedBy = "disciplina")
	private Set<Turma> turmas;

	@ManyToMany
	@JoinTable(name = "tb_disciplinas_requisitos", joinColumns = @JoinColumn(name = "disciplina_id"), inverseJoinColumns = @JoinColumn(name = "requisito_id"))
	private Set<Disciplina> requisitos;
	
	@ManyToMany
	@JoinTable(name = "tb_disciplinas_equivalencias", joinColumns = @JoinColumn(name = "disciplina_id"), inverseJoinColumns = @JoinColumn(name = "equivalencia_id"))
	private Set<Disciplina> equivalencias;
	
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
		if (this.getEquivalencias().contains(other) || other.getEquivalencias().contains(this))
			return true;
		return Objects.equals(codigo, other.codigo);
	}
	
	
	
	
	

	

}
