package br.com.quemateria.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import br.com.quemateria.entities.Dia;
import br.com.quemateria.repositories.DiaRepository;

@Service
public class DiaService {
	
	private final DiaRepository diaRepository;
	
	public DiaService(DiaRepository diaRepository) {
		this.diaRepository = diaRepository;
	}
	
	public Dia buscarDiaPorIdentificador(Integer identificador) {
		Optional<Dia> buscarPorIdentificador = diaRepository.findByIdentificador(identificador);
		
		return buscarPorIdentificador.orElseThrow(() -> new EntityNotFoundException("Dia não encontrado"));
	}

}
