package br.com.quemateria.services;

import java.util.Optional;
import java.util.Set;

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

	public Disciplina salvarDisciplina(Disciplina disciplina) {
		return disciplinaRepository.save(disciplina);
	}

	public Page<Disciplina> listarDisciplinas(Pageable pageable) {
		return disciplinaRepository.findAll(pageable);
	}

	public Disciplina buscarDisciplina(Long id) {
		Optional<Disciplina> buscarPorId = disciplinaRepository.findById(id);

		return buscarPorId.orElseThrow(() -> new CustomNotFoundException("Disciplina não encontrada"));
	}
	
	public Disciplina buscarDisciplinaPorCodigo(String codigo) {
		Optional<Disciplina> buscarPorTurma = disciplinaRepository.findByCodigo(codigo);

		return buscarPorTurma.orElseThrow(() -> new CustomNotFoundException("Disciplina não encontrada"));
	}

	public Disciplina atualizarDisciplina(Disciplina disciplina, Long id) {
		Disciplina disciplinaOriginal = this.buscarDisciplina(id);
		disciplina.setId(disciplinaOriginal.getId());

		return disciplinaRepository.save(disciplina);
	}

	public Disciplina adicionarPreRequisitos(Set<Disciplina> disciplinas, String codigo) {
		Disciplina disciplina = this.buscarDisciplinaPorCodigo(codigo);

		disciplinas.forEach(preRequisito -> this.buscarDisciplina(preRequisito.getId()));

		Set<Disciplina> preRequisitos = disciplina.getRequisitos();
		preRequisitos.addAll(disciplinas);

		disciplina.setRequisitos(disciplinas);

		return disciplinaRepository.save(disciplina);
	}

	public Disciplina removerPreRequisito(String codigoDisciplina, String codigoEquivalencia) {
		Disciplina disciplina = this.buscarDisciplinaPorCodigo(codigoDisciplina);

		Set<Disciplina> preRequisitos = disciplina.getRequisitos();
		preRequisitos.remove(this.buscarDisciplinaPorCodigo(codigoEquivalencia));

		disciplina.setRequisitos(preRequisitos);

		return disciplinaRepository.save(disciplina);
	}

	public Disciplina adicionarEquivalencias(Set<Disciplina> disciplinas, String codigo) {
		Disciplina disciplina = this.buscarDisciplinaPorCodigo(codigo);

		disciplinas.forEach(equivalencia -> this.buscarDisciplina(equivalencia.getId()));

		Set<Disciplina> equivalencias = disciplina.getEquivalencias();
		equivalencias.addAll(disciplinas);

		disciplina.setEquivalencias(disciplinas);

		return disciplinaRepository.save(disciplina);
	}

	public Disciplina removerEquivalencia(String codigoDisciplina, String codigoEquivalencia) {
		Disciplina disciplina = this.buscarDisciplinaPorCodigo(codigoDisciplina);

		Set<Disciplina> equivalencias = disciplina.getEquivalencias();
		equivalencias.remove(this.buscarDisciplinaPorCodigo(codigoEquivalencia));

		disciplina.setEquivalencias(equivalencias);

		return disciplinaRepository.save(disciplina);
	}

	public void excluirDisciplina(String codigo) {
		Disciplina disciplina = this.buscarDisciplinaPorCodigo(codigo);
		/*
		 * disciplina.getEquivalencias().clear(); disciplina.getRequisitos().clear();
		 */
		disciplinaRepository.delete(disciplina);
	}

}
