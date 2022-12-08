package br.com.quemateria.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.quemateria.entities.ItemMatrizCurricular;
import br.com.quemateria.exceptions.CustomNotFoundException;
import br.com.quemateria.repositories.ItemMatrizCurricularRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ItemMatrizCurricularService {

	private final ItemMatrizCurricularRepository matrizCurricularRepository;
	private final ValidacaoService validacaoService;
	
	public final Logger logger = LoggerFactory.getLogger(ItemMatrizCurricularService.class);

	public ItemMatrizCurricular salvarItemMatrizCurricular(ItemMatrizCurricular matrizCurricular) {
		logger.info("Acessando método salvarItemMatrizCurricular...");
		
		logger.info("Retornando disciplina " + matrizCurricular.getDisciplina().getCodigo() + " " 
				+ matrizCurricular.getDisciplina().getNome() + " vinculada à matriz do curso " 
				+ matrizCurricular.getCurso().getCodigo() + " " 
				+ matrizCurricular.getCurso().getNome());
		return matrizCurricularRepository.save(matrizCurricular);
	}

	public ItemMatrizCurricular buscarItemMatrizCurricular(Long id) {
		logger.info("Acessando método buscarItemMatrizCurricular...");
		Optional<ItemMatrizCurricular> buscarPorId = matrizCurricularRepository.findById(id);
		
		if (!buscarPorId.isPresent())
			logger.info("Disciplina não encontrada");
		
		logger.info("Disciplina " + buscarPorId.get().getDisciplina().getCodigo() + " " 
				+ buscarPorId.get().getDisciplina().getNome() + " encontrada no curso " 
				+ buscarPorId.get().getCurso().getCodigo() + " " 
				+ buscarPorId.get().getCurso().getNome() );

		return buscarPorId.orElseThrow(() -> new CustomNotFoundException("Disciplina não encontrada"));
	}
	
	public ItemMatrizCurricular atualizarPeso(Double peso, Long id) {
		logger.info("Acessando método atualizarPeso...");
		ItemMatrizCurricular item = this.buscarItemMatrizCurricular(id);
		
		if(item.getPeso().equals(peso)) {
			logger.info("Peso não precisou sofrer alteração");
			return item;
		}
			
		logger.info("Peso foi atualizado com sucesso");
		item.setPeso(peso);
		
		return matrizCurricularRepository.save(item);
	}
	
	public void validarParametros(String codigoCurso, String codigoDisciplina, Integer tipoDeDisciplina, Integer quantidadePreRequisitos) {
		logger.info("Acessando método validarParametros...");
		
		validacaoService.validarCodigoCurso(codigoCurso);
		validacaoService.validarCodigoDisciplina(codigoDisciplina);
		validacaoService.validarTipoDeDisciplina(tipoDeDisciplina);
		validacaoService.validarQuantidadePreRequisitos(quantidadePreRequisitos);
		
		logger.info("Parâmetros validados!");
	}

}
