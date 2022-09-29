package br.com.quemateria.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.quemateria.entities.Horario;
import br.com.quemateria.exceptions.EntityNotFoundException;
import br.com.quemateria.repositories.HorarioRepository;

@Service
public class HorarioService {

	private final HorarioRepository horarioRepository;
	
	public HorarioService(HorarioRepository horarioRepository) {
		this.horarioRepository = horarioRepository;
	}
	
	public Horario buscarHorarioPorSigla (String sigla) {
		Optional<Horario> buscarPorSigla = horarioRepository.findBySigla(sigla);
		
		return buscarPorSigla.orElseThrow(() -> new EntityNotFoundException("Horário não encontrado"));
	}
}
