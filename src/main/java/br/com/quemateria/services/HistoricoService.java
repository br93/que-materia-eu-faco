package br.com.quemateria.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.quemateria.entities.Aluno;
import br.com.quemateria.entities.Disciplina;
import br.com.quemateria.repositories.AlunoRepository;
import br.com.quemateria.repositories.DisciplinaRepository;

@Service
public class HistoricoService {

	private AlunoRepository alunoRepository;
	private DisciplinaRepository disciplinaRepository;

	public HistoricoService(AlunoRepository alunoRepository, DisciplinaRepository disciplinaRepository) {
		this.alunoRepository = alunoRepository;
		this.disciplinaRepository = disciplinaRepository;
	}

	private Aluno getUltimoAlunoCadastrado() {
		List<Aluno> listaAlunos = alunoRepository.findAll();
		return listaAlunos.get(listaAlunos.size() - 1);
	}

	private Long getIdUltimoAlunoCadastrado() {
		return this.getUltimoAlunoCadastrado().getId();
	}

	public List<Disciplina> getDisciplinasCursadas() {
		return this.getUltimoAlunoCadastrado().getDisciplinasCursadas();
	}

	public List<Disciplina> listarDisciplinasObrigatorias() {
		Long id = this.getIdUltimoAlunoCadastrado();
		return disciplinaRepository
				.findByCurso_IdAndTipoDeDisciplina_TipoValorOrderByPesoAscCargaHorariaDescPeriodoAsc(id, 1);
	}

	public List<Disciplina> listarDisciplinasOpcionais() {
		Long id = this.getIdUltimoAlunoCadastrado();
		return disciplinaRepository
				.findByCurso_IdAndTipoDeDisciplina_TipoValorOrderByPesoAscCargaHorariaDescPeriodoAsc(id, 2);
	}

	public List<Disciplina> listarDisciplinasTrilha() {
		Long id = this.getIdUltimoAlunoCadastrado();
		return disciplinaRepository
				.findByCurso_IdAndTipoDeDisciplina_TipoValorOrderByPesoAscCargaHorariaDescPeriodoAsc(id, 3);
	}

	public List<Disciplina> getDisciplinasObrigatoriasFaltantes() {

		List<Disciplina> disciplinasObrigatorias = this.listarDisciplinasObrigatorias();
		List<Disciplina> obrigatoriasFaltantes = disciplinasObrigatorias;

		obrigatoriasFaltantes.removeAll(this.getDisciplinasCursadas());

		return obrigatoriasFaltantes;
	}

}
