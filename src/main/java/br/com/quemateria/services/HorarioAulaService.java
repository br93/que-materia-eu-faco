package br.com.quemateria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.quemateria.entities.HorarioAula;
import br.com.quemateria.exceptions.EntityNotFoundException;
import br.com.quemateria.repositories.HorarioAulaRepository;

@Service
public class HorarioAulaService {

	private final HorarioAulaRepository horarioAulaRepository;
	
	public HorarioAulaService(HorarioAulaRepository horarioAulaRepository) {
		this.horarioAulaRepository = horarioAulaRepository;
	}
	
	public HorarioAula salvarAula(HorarioAula horarioAula) {
		return horarioAulaRepository.save(horarioAula);
	}
	
	public Page<HorarioAula> listarAulas(Pageable pageable) {
		return horarioAulaRepository.findAll(pageable);
	}
	
	public HorarioAula buscarAula(Long id) {
		Optional<HorarioAula> buscarPorId = horarioAulaRepository.findById(id);

		return buscarPorId.orElseThrow(() -> new EntityNotFoundException("Aula não encontrada"));
	}
	
	public List<HorarioAula> listarAulaPorTurmaCodigo(String codigo){
		return horarioAulaRepository.findAllByTurma_Codigo(codigo);
	}
	
	public void excluirAula(Long id) {
		HorarioAula horarioAula = this.buscarAula(id);
		horarioAulaRepository.delete(horarioAula);
	}
}
