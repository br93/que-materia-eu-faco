package br.com.quemateria.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.quemateria.entities.Dia;
import br.com.quemateria.exceptions.CustomNotFoundException;
import br.com.quemateria.repositories.DiaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DiaService {
	
	private final DiaRepository diaRepository;
	
	public Dia buscarDiaPorIdentificador(Integer identificador) {
		Optional<Dia> buscarPorIdentificador = diaRepository.findByIdentificador(identificador);
		
		return buscarPorIdentificador.orElseThrow(() -> new CustomNotFoundException("Dia não encontrado"));
	}

}
