package br.com.quemateria.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tb_turmas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Turma {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String codigo;

	@ManyToOne(fetch = FetchType.LAZY)
	private Disciplina disciplina;
	
	@OneToMany(mappedBy = "turma", cascade = CascadeType.ALL)
	@OrderBy(value = "id ASC")
	private Set<HorarioAula> horarios;

}
