package br.com.quemateria.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tipos_de_disciplina_db")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoDeDisciplina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String tipoNome;
	private Integer tipoValor;
}
