package br.com.quemateria.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.quemateria.entities.ItemMatrizCurricular;
import br.com.quemateria.exceptions.EntityNotFoundException;
import br.com.quemateria.repositories.ItemMatrizCurricularRepository;

@Service
public class ItemMatrizCurricularService {
	
	private final ItemMatrizCurricularRepository matrizCurricularRepository;
	
	public ItemMatrizCurricularService (ItemMatrizCurricularRepository matrizCurricularRepository) {
		this.matrizCurricularRepository = matrizCurricularRepository;
	}
	
	public ItemMatrizCurricular salvarItemMatrizCurricular(ItemMatrizCurricular matrizCurricular) {
		return matrizCurricularRepository.save(matrizCurricular);
	}
	
	public ItemMatrizCurricular buscarItemMatrizCurricular(Long id) {
		Optional<ItemMatrizCurricular> buscarPorId = matrizCurricularRepository.findById(id);

		return buscarPorId.orElseThrow(() -> new EntityNotFoundException("Disciplina não encontrada"));
	}
	
	public ItemMatrizCurricular atualizarPeso(Double peso, Long id) {
		ItemMatrizCurricular item = this.buscarItemMatrizCurricular(id);
		item.setPeso(peso);
		
		return matrizCurricularRepository.save(item);
	}

}
