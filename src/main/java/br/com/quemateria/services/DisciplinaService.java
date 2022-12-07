package br.com.quemateria.services;

import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.quemateria.entities.Disciplina;
import br.com.quemateria.exceptions.CustomNotFoundException;
import br.com.quemateria.repositories.DisciplinaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DisciplinaService {

	private final DisciplinaRepository disciplinaRepository;
	private final ValidacaoService validacaoService;
	
	public final Logger logger = LoggerFactory.getLogger(DisciplinaService.class);

	public Disciplina salvarDisciplina(Disciplina disciplina) {
		logger.info("Acessando método salvarDisciplina...");
		
		logger.info("Retornando disciplina " + disciplina.getCodigo() + " " + disciplina.getNome() + " criada");
		return disciplinaRepository.save(disciplina);
	}

	public Page<Disciplina> listarDisciplinas(Pageable pageable) {
		logger.info("Acessando método listarDisciplinas...");

		logger.info("Listando todos as disciplinas cadastradas");
		return disciplinaRepository.findAll(pageable);
	}

	public Disciplina buscarDisciplina(Long id) {
		logger.info("Acessando método buscarDisciplina...");
		
		Optional<Disciplina> buscarPorId = disciplinaRepository.findById(id);
		
		if (buscarPorId.get() == null)
			logger.info("Disciplina não encontrada");
		
		logger.info("Disciplina " + buscarPorId.get().getCodigo() + " " + buscarPorId.get().getNome() + " encontrada");

		return buscarPorId.orElseThrow(() -> new CustomNotFoundException("Disciplina não encontrada"));
	}
	
	public Disciplina buscarDisciplinaPorCodigo(String codigo) {
		logger.info("Acessando método buscarDisciplinaPorCodigo...");
		
		Optional<Disciplina> buscarPorCodigo = disciplinaRepository.findByCodigo(codigo);
		
		if (buscarPorCodigo.isEmpty())
			logger.info("Disciplina não encontrada");
		
		logger.info("Disciplina " + buscarPorCodigo.get().getCodigo() + " " + buscarPorCodigo.get().getNome() + " encontrada");

		return buscarPorCodigo.orElseThrow(() -> new CustomNotFoundException("Disciplina não encontrada"));
	}

	public Disciplina atualizarDisciplina(Disciplina disciplina, Long id) {
		logger.info("Acessando método atualizarDisciplina...");
		
		Disciplina disciplinaOriginal = this.buscarDisciplina(id);
		disciplina.setId(disciplinaOriginal.getId());

		logger.info("Retornando disciplina " + disciplina.getCodigo() + " " + disciplina.getNome() + " atualizada");
		return disciplinaRepository.save(disciplina);
	}

	public Disciplina adicionarPreRequisitos(Set<Disciplina> disciplinas, String codigo) {
		logger.info("Acessando método adicionarPreRequisitos...");
		Disciplina disciplina = this.buscarDisciplinaPorCodigo(codigo);

		logger.info("Verificando pre-requisitos já vinculados a " + disciplina.getCodigo() + " " + disciplina.getNome());
		Set<Disciplina> preRequisitos = disciplina.getRequisitos();
		
		logger.info("Adicionando pre-requisitos na disciplina " + disciplina.getCodigo() + " " + disciplina.getNome());
		disciplinas.forEach(preRequisito -> this.buscarDisciplina(preRequisito.getId()));
		preRequisitos.addAll(disciplinas);
		
		logger.info("Atualizando disciplina " + disciplina.getCodigo() + " " + disciplina.getNome());
		disciplina.setRequisitos(disciplinas);

		logger.info("Retornando disciplina " + disciplina.getCodigo() + " " + disciplina.getNome() + " atualizada");
		return disciplinaRepository.save(disciplina);
	}

	public Disciplina removerPreRequisito(String codigoDisciplina, String codigoPreRequisito) {
		logger.info("Acessando método removerPreRequisitos...");
		Disciplina disciplina = this.buscarDisciplinaPorCodigo(codigoDisciplina);

		logger.info("Verificando pre-requisitos já vinculados a " + disciplina.getCodigo() + " " + disciplina.getNome());
		Set<Disciplina> preRequisitos = disciplina.getRequisitos();
		
		logger.info("Removendo pre-requisitos na disciplina " + disciplina.getCodigo() + " " + disciplina.getNome());
		preRequisitos.remove(this.buscarDisciplinaPorCodigo(codigoPreRequisito));

		logger.info("Atualizando disciplina " + disciplina.getCodigo() + " " + disciplina.getNome());
		disciplina.setRequisitos(preRequisitos);

		logger.info("Retornando disciplina " + disciplina.getCodigo() + " " + disciplina.getNome() + " atualizada");
		return disciplinaRepository.save(disciplina);
	}

	public Disciplina adicionarEquivalencias(Set<Disciplina> disciplinas, String codigo) {
		logger.info("Acessando método adicionarEquivalencias...");
		Disciplina disciplina = this.buscarDisciplinaPorCodigo(codigo);
		
		logger.info("Verificando equivalencias já vinculados a " + disciplina.getCodigo() + " " + disciplina.getNome());
		Set<Disciplina> equivalencias = disciplina.getEquivalencias();

		logger.info("Adicionando equivalencias na disciplina " + disciplina.getCodigo() + " " + disciplina.getNome());
		disciplinas.forEach(equivalencia -> this.buscarDisciplina(equivalencia.getId()));
		equivalencias.addAll(disciplinas);

		logger.info("Atualizando disciplina " + disciplina.getCodigo() + " " + disciplina.getNome());
		disciplina.setEquivalencias(disciplinas);

		logger.info("Retornando disciplina " + disciplina.getCodigo() + " " + disciplina.getNome() + " atualizada");
		return disciplinaRepository.save(disciplina);
	}

	public Disciplina removerEquivalencia(String codigoDisciplina, String codigoEquivalencia) {
		logger.info("Acessando método removerEquivalencia...");
		Disciplina disciplina = this.buscarDisciplinaPorCodigo(codigoDisciplina);

		logger.info("Verificando equivalencias já vinculados a " + disciplina.getCodigo() + " " + disciplina.getNome());
		Set<Disciplina> equivalencias = disciplina.getEquivalencias();
		
		logger.info("Removendo equivalencias na disciplina " + disciplina.getCodigo() + " " + disciplina.getNome());
		equivalencias.remove(this.buscarDisciplinaPorCodigo(codigoEquivalencia));

		logger.info("Atualizando disciplina " + disciplina.getCodigo() + " " + disciplina.getNome());
		disciplina.setEquivalencias(equivalencias);

		logger.info("Retornando disciplina " + disciplina.getCodigo() + " " + disciplina.getNome() + " atualizada");
		return disciplinaRepository.save(disciplina);
	}

	public void excluirDisciplina(String codigo) {
		logger.info("Acessando método excluirDisciplina...");
		Disciplina disciplina = this.buscarDisciplinaPorCodigo(codigo);
		
		if (disciplina != null)
			logger.info("Disciplina " + disciplina.getNome() + " deletada com sucesso");
		
		disciplinaRepository.delete(disciplina);
	}
	
	public void validarParametros(String codigo) {
		logger.info("Acessando método validarParametros...");
		
		validacaoService.validarCodigoDisciplina(codigo);
		
		logger.info("Parâmetros validados!");
	}

	public void validarParametros(String nome, String codigo, Integer cargaHoraria) {
		logger.info("Acessando método validarParametros...");
		
		validacaoService.validarNome(nome, "Nome do curso");
		validacaoService.validarCodigoCurso(codigo);
		validacaoService.validarCargaHoraria(cargaHoraria);
		
		logger.info("Parâmetros validados!");
	}

}
