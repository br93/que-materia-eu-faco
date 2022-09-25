package br.com.quemateria.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import br.com.quemateria.entities.MatrizCurricular;
import br.com.quemateria.repositories.MatrizCurricularRepository;

@Service
public class MatrizCurricularService {
	
	private final MatrizCurricularRepository matrizCurricularRepository;
	
	public MatrizCurricularService (MatrizCurricularRepository matrizCurricularRepository) {
		this.matrizCurricularRepository = matrizCurricularRepository;
	}
	
	public MatrizCurricular buscarDisciplina(Long id) {
		Optional<MatrizCurricular> buscarPorId = matrizCurricularRepository.findById(id);

		return buscarPorId.orElseThrow(() -> new EntityNotFoundException("Disciplina não encontrada"));
	}
	
	public MatrizCurricular atualizarPeso(Double peso, Long id) {
		MatrizCurricular disciplina = this.buscarDisciplina(id);
		disciplina.setPeso(peso);
		
		return matrizCurricularRepository.save(disciplina);
	}

}
