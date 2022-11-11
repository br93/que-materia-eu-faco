package br.com.quemateria.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.quemateria.entities.ItemMatrizCurricular;
import br.com.quemateria.exceptions.CustomNotFoundException;
import br.com.quemateria.repositories.ItemMatrizCurricularRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ItemMatrizCurricularService {

	private final ItemMatrizCurricularRepository matrizCurricularRepository;

	public ItemMatrizCurricular salvarItemMatrizCurricular(ItemMatrizCurricular matrizCurricular) {
		return matrizCurricularRepository.save(matrizCurricular);
	}

	public ItemMatrizCurricular buscarItemMatrizCurricular(Long id) {
		Optional<ItemMatrizCurricular> buscarPorId = matrizCurricularRepository.findById(id);

		return buscarPorId.orElseThrow(() -> new CustomNotFoundException("Disciplina não encontrada"));
	}

}
