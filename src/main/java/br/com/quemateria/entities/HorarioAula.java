package br.com.quemateria.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tb_horario_aula")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HorarioAula {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Turma turma;
	
	@ManyToOne
	private Dia dia;
	
	@ManyToOne
	private Horario horario;

	@Override
	public int hashCode() {
		return Objects.hash(dia, horario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HorarioAula other = (HorarioAula) obj;
		return Objects.equals(dia, other.dia) && Objects.equals(horario, other.horario);
	}

	
	
	
	
	

}
