package br.com.quemateria.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.quemateria.entities.TipoDeDisciplina;
import br.com.quemateria.repositories.TipoDeDisciplinaRepository;

@Service
public class TipoDeDisciplinaService {
	
private final TipoDeDisciplinaRepository tipoDeDisciplinaRepository;
	
	public TipoDeDisciplinaService(TipoDeDisciplinaRepository tipoDeDisciplinaRepository) {
		this.tipoDeDisciplinaRepository = tipoDeDisciplinaRepository;
	}
	
	public TipoDeDisciplina salvarTipoDeDisciplina (TipoDeDisciplina tipoDeDisciplina) {
		return tipoDeDisciplinaRepository.save(tipoDeDisciplina);
	}
	
	public Page<TipoDeDisciplina> listarTipoDeDisciplinas(Pageable pageable){
		return tipoDeDisciplinaRepository.findAll(pageable);
	}
	
	public TipoDeDisciplina buscarTipoDeDisciplina(Long id) {
		Optional<TipoDeDisciplina> buscarPorId = tipoDeDisciplinaRepository.findById(id);
		
		return buscarPorId.orElseThrow(() -> new EntityNotFoundException("Tipo de disciplina não encontrado"));
	}
	
	public TipoDeDisciplina buscarTipoDeDisciplinaPorNome(String nome) {
		Optional<TipoDeDisciplina> buscarPorNome = tipoDeDisciplinaRepository.findByTipoNome(nome);
		
		return buscarPorNome.orElseThrow(() -> new EntityNotFoundException("Tipo de disciplina não encontrado"));
	}
	
	public TipoDeDisciplina atualizarTipoDeDisciplina(TipoDeDisciplina tipoDeDisciplina, Long id) {
		TipoDeDisciplina tipoDeDisciplinaOriginal = this.buscarTipoDeDisciplina(id);
		tipoDeDisciplina.setId(tipoDeDisciplinaOriginal.getId());
		
		return tipoDeDisciplinaRepository.save(tipoDeDisciplina);
	}
	
	public void excluirTipoDeDisciplina(Long id) {
		TipoDeDisciplina tipoDeDisciplina = this.buscarTipoDeDisciplina(id);
		tipoDeDisciplinaRepository.delete(tipoDeDisciplina);
	}

}
