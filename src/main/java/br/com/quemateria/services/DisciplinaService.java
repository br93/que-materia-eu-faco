package br.com.quemateria.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.quemateria.entities.Disciplina;
import br.com.quemateria.repositories.DisciplinaRepository;

@Service
public class DisciplinaService {

	private final DisciplinaRepository disciplinaRepository;

	public DisciplinaService(DisciplinaRepository disciplinaRepository) {
		this.disciplinaRepository = disciplinaRepository;
	}

	public Disciplina salvarDisciplina(Disciplina disciplina) {
		return disciplinaRepository.save(disciplina);
	}

	public Page<Disciplina> listarDisciplinas(Pageable pageable) {
		return disciplinaRepository.findAll(pageable);
	}

	public Disciplina buscarDisciplina(Long id) {
		Optional<Disciplina> buscarPorId = disciplinaRepository.findById(id);

		return buscarPorId.orElseThrow(() -> new EntityNotFoundException("Disciplina não encontrada"));
	}

	public Disciplina buscarDisciplinaPorCodigo(String codigo) {
		Optional<Disciplina> buscarPorCodigo = disciplinaRepository.findByCodigo(codigo);

		return buscarPorCodigo.orElseThrow(() -> new EntityNotFoundException("Disciplina não encontrada"));
	}

	public Disciplina atualizarDisciplina(Disciplina disciplina, Long id) {
		Disciplina disciplinaOriginal = this.buscarDisciplina(id);
		disciplina.setId(disciplinaOriginal.getId());

		return disciplinaRepository.save(disciplina);
	}

	public void excluirDisciplina(Long id) {
		Disciplina disciplina = this.buscarDisciplina(id);
		disciplinaRepository.delete(disciplina);
	}

	public void calcularPeso() {
		List<Disciplina> disciplinas = disciplinaRepository.findAll();
		
		for (Disciplina disciplina : disciplinas)
			if (disciplina.getPeso() == 0.0d)
				disciplina.setPeso(Math.log(disciplina.getPeriodo()) - disciplina.getRequisitos().size()
						- disciplina.getTipoDeDisciplina().getTipoValor());
	}

}
