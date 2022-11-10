package br.com.quemateria.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.quemateria.entities.Horario;
import br.com.quemateria.exceptions.CustomNotFoundException;
import br.com.quemateria.repositories.HorarioRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HorarioService {

	private final HorarioRepository horarioRepository;

	public Horario buscarHorarioPorSigla(String sigla) {
		Optional<Horario> buscarPorSigla = horarioRepository.findBySigla(sigla);

		return buscarPorSigla.orElseThrow(() -> new CustomNotFoundException("Horário não encontrado"));
	}
}
