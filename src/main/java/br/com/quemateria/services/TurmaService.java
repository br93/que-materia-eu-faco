package br.com.quemateria.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.quemateria.entities.Turma;
import br.com.quemateria.exceptions.CustomNotFoundException;
import br.com.quemateria.repositories.TurmaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TurmaService {

	private final TurmaRepository turmaRepository;
	private final ValidacaoService validacaoService;
	
	public final Logger logger = LoggerFactory.getLogger(TurmaService.class);

	public Turma salvarTurma(Turma turma) {
		logger.info("Acessando método salvarTurma...");
		
		logger.info("Retornando turma " + turma.getDisciplina().getCodigo() + " " + turma.getCodigo() + " criada!");
		return turmaRepository.save(turma);
	}

	public Page<Turma> listarTurmas(Pageable pageable) {
		logger.info("Acessando método listarTurmas...");

		logger.info("Listando todos as turmas cadastradas!");
		return turmaRepository.findAll(pageable);
	}

	public Turma buscarTurma(Long id) {
		logger.info("Acessando método buscarTurma...");
		Optional<Turma> buscarPorId = turmaRepository.findById(id);
		
		if (!buscarPorId.isPresent())
			logger.info("Turma não encontrada");
		
		logger.info("Turma " + buscarPorId.get().getDisciplina().getCodigo() + " " + 
				buscarPorId.get().getCodigo() + " encontrada!");

		return buscarPorId.orElseThrow(() -> new CustomNotFoundException("Turma não encontrada"));
	}

	public Turma buscarTurmaPorCodigo(String codigo) {
		logger.info("Acessando método buscarTurmaPorCodigo...");
		Optional<Turma> buscarPorCodigo = turmaRepository.findByCodigo(codigo);
		
		if (!buscarPorCodigo.isPresent())
			logger.info("Turma não encontrada");
		
		logger.info("Turma " + buscarPorCodigo.get().getDisciplina().getCodigo() + " " + 
				buscarPorCodigo.get().getCodigo() + " encontrada!");

		return buscarPorCodigo.orElseThrow(() -> new CustomNotFoundException("Turma não encontrada"));
	}

	public void excluirTurma(String codigo) {
		logger.info("Acessando método excluirTurma...");
		Turma turma = this.buscarTurmaPorCodigo(codigo);
		
		if (turma != null)
			logger.info("Turma " + turma.getDisciplina().getCodigo() + " " + turma.getCodigo() + " deletada com sucesso!");
		
		turmaRepository.delete(turma);
	}
	
	public void validarParametros(String codigo) {
		logger.info("Acessando método validarParametros...");
		
		validacaoService.validarCodigoTurma(codigo);
		
		logger.info("Parâmetros validados!");
	}

	public void validarParametros(String codigo, String codigoDisciplina) {
		logger.info("Acessando método validarParametros...");
		
		validacaoService.validarCodigoTurma(codigo);
		validacaoService.validarCodigoDisciplina(codigoDisciplina);
		
		logger.info("Parâmetros validados!");
	}

}
