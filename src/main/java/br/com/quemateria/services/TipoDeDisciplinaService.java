package br.com.quemateria.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import br.com.quemateria.entities.TipoDeDisciplina;
import br.com.quemateria.repositories.TipoDeDisciplinaRepository;

@Service
public class TipoDeDisciplinaService {
	
	private final TipoDeDisciplinaRepository tipoDeDisciplinaRepository;
	
	public TipoDeDisciplinaService(TipoDeDisciplinaRepository tipoDeDisciplinaRepository) {
		this.tipoDeDisciplinaRepository = tipoDeDisciplinaRepository;
	}
	
	public TipoDeDisciplina buscarTipoDeDisciplina(Long id) {
		Optional<TipoDeDisciplina> buscarPorId = tipoDeDisciplinaRepository.findById(id);

		return buscarPorId.orElseThrow(() -> new EntityNotFoundException("Tipo de disciplina não encontrado"));
	}

}
