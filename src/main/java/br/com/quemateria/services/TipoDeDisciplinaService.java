package br.com.quemateria.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.quemateria.entities.TipoDeDisciplina;
import br.com.quemateria.exceptions.CustomNotFoundException;
import br.com.quemateria.repositories.TipoDeDisciplinaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TipoDeDisciplinaService {

	private final TipoDeDisciplinaRepository tipoDeDisciplinaRepository;

	public TipoDeDisciplina buscarTipoDeDisciplina(Long id) {
		Optional<TipoDeDisciplina> buscarPorId = tipoDeDisciplinaRepository.findById(id);

		return buscarPorId.orElseThrow(() -> new CustomNotFoundException("Tipo de disciplina não encontrado"));
	}

}
