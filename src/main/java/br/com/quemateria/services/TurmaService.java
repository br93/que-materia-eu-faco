package br.com.quemateria.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.quemateria.entities.Turma;
import br.com.quemateria.exceptions.EntityNotFoundException;
import br.com.quemateria.repositories.TurmaRepository;

@Service
public class TurmaService {
	
	private final TurmaRepository turmaRepository;
	
	public TurmaService(TurmaRepository turmaRepository) {
		this.turmaRepository = turmaRepository;
	}
	
	public Turma salvarTurma(Turma turma) {
		return turmaRepository.save(turma);
	}
	
	public Page<Turma> listarTurmas(Pageable pageable) {
		return turmaRepository.findAll(pageable);
	}

	public Turma buscarTurma(Long id) {
		Optional<Turma> buscarPorId = turmaRepository.findById(id);

		return buscarPorId.orElseThrow(() -> new EntityNotFoundException("Turma não encontrada"));
	}
	
	public Turma buscarTurmaPorCodigo(String codigo) {
		Optional<Turma> buscarPorCodigo = turmaRepository.findByCodigo(codigo);

		return buscarPorCodigo.orElseThrow(() -> new EntityNotFoundException("Turma não encontrada"));
	}
	
	public void excluirTurma(String codigo) {
		Turma turma = this.buscarTurmaPorCodigo(codigo);
		turmaRepository.delete(turma);
	}

}
