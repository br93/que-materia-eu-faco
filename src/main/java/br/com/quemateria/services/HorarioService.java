package br.com.quemateria.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.quemateria.entities.Horario;
import br.com.quemateria.repositories.HorarioRepository;

@Service
public class HorarioService {
	
private final HorarioRepository horarioRepository;
	
	public HorarioService(HorarioRepository horarioRepository) {
		this.horarioRepository = horarioRepository;
	}
	
	public Horario salvarHorario (Horario horario) {
		return horarioRepository.save(horario);
	}
	
	public Page<Horario> listarHorarios(Pageable pageable){
		return horarioRepository.findAll(pageable);
	}
	
	public Horario buscarHorario(Long id) {
		Optional<Horario> buscarPorId = horarioRepository.findById(id);
		
		return buscarPorId.orElseThrow(() -> new EntityNotFoundException("Horario não encontrado"));
	}
	
	public Horario atualizarHorario(Horario horario, Long id) {
		Horario horarioOriginal = this.buscarHorario(id);
		horario.setId(horarioOriginal.getId());
		
		return horarioRepository.save(horario);
	}
	
	public void excluirHorario(Long id) {
		Horario horario = this.buscarHorario(id);
		horarioRepository.delete(horario);
	}

}
