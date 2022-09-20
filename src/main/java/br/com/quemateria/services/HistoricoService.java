package br.com.quemateria.services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import br.com.quemateria.entities.Aluno;
import br.com.quemateria.entities.Disciplina;
import br.com.quemateria.repositories.AlunoRepository;
import br.com.quemateria.repositories.DisciplinaRepository;

@Service
public class HistoricoService {
	
	private AlunoRepository alunoRepository;
	private DisciplinaRepository disciplinaRepository;
	
	public HistoricoService (AlunoRepository alunoRepository, DisciplinaRepository disciplinaRepository) {
		this.alunoRepository = alunoRepository;
		this.disciplinaRepository = disciplinaRepository;
	}
	
	private Aluno getUltimoAlunoCadastrado() {
		List<Aluno> listaAlunos = alunoRepository.findAll();
		return listaAlunos.get(listaAlunos.size()-1);
	}
	
	private Long getIdUltimoAlunoCadastrado() {
		return this.getUltimoAlunoCadastrado().getId();
	}
	
	public Set<Disciplina> getDisciplinasCursadas(){
		return this.getUltimoAlunoCadastrado().getDisciplinasCursadas();
	}
	
	public Set<Disciplina> listarDisciplinasObrigatorias(){
		Long id = this.getIdUltimoAlunoCadastrado();
		return disciplinaRepository.findByCurso_IdAndTipoDeDisciplina_TipoValor(id, 1);
	}
	
	public Set<Disciplina> listarDisciplinasOpcionais(){
		Long id = this.getIdUltimoAlunoCadastrado();
		return disciplinaRepository.findByCurso_IdAndTipoDeDisciplina_TipoValor(id, 2);
	}
	
	public Set<Disciplina> listarDisciplinasTrilha(){
		Long id = this.getIdUltimoAlunoCadastrado();
		return disciplinaRepository.findByCurso_IdAndTipoDeDisciplina_TipoValor(id, 3);
	}
	
	public Set<Disciplina> getDisciplinasObrigatoriasFaltantes(){
		
		Set<Disciplina> disciplinasObrigatorias = this.listarDisciplinasObrigatorias();
		Set<Disciplina> obrigatoriasFaltantes = disciplinasObrigatorias;
		obrigatoriasFaltantes.removeAll(this.getDisciplinasCursadas());
		
		return obrigatoriasFaltantes;
	}

}
