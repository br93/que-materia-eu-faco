package br.com.quemateria.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.quemateria.entities.HorarioAula;
import br.com.quemateria.exceptions.CustomNotFoundException;
import br.com.quemateria.repositories.HorarioAulaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HorarioAulaService {

	private final HorarioAulaRepository horarioAulaRepository;
	private final ValidacaoService validacaoService;

	public final Logger logger = LoggerFactory.getLogger(HorarioAulaService.class);

	public HorarioAula salvarAula(HorarioAula horarioAula) {
		logger.info("Acessando método salvarAula...");

		logger.info("Retornando horario-aula " + horarioAula.getTurma().getCodigo() + "dia " + 
				horarioAula.getDia().getDia() + " horário: " + horarioAula.getHorario().getFaixa() + 
				"!");
		return horarioAulaRepository.save(horarioAula);
	}

	public Page<HorarioAula> listarAulas(Pageable pageable) {
		logger.info("Acessando método listarAulas...");
		
		logger.info("Listando todos os horários-aula cadastrados!");
		return horarioAulaRepository.findAll(pageable);
	}

	public HorarioAula buscarAula(Long id) {
		logger.info("Acessando método buscarAula...");
		Optional<HorarioAula> buscarPorId = horarioAulaRepository.findById(id);
		
		if (!buscarPorId.isPresent())
			logger.info("Horário-aula não encontrado!");
		
		logger.info("Horário-aula " + buscarPorId.get().getTurma().getCodigo() + "dia " + 
				buscarPorId.get().getDia().getDia() + " horário: " + 
				buscarPorId.get().getHorario().getFaixa() + " encontrado!");

		return buscarPorId.orElseThrow(() -> new CustomNotFoundException("Aula não encontrada"));
	}

	public List<HorarioAula> listarAulaPorDisciplinaCodigo(String disciplinaCodigo) {
		logger.info("Acessando método listarAulaPorDisciplinaCodigo...");
		
		logger.info("Listando todos os horários-aula da disciplina " + disciplinaCodigo + "!");
		return horarioAulaRepository.findAllByTurma_Disciplina_Codigo(disciplinaCodigo);
	}

	public List<HorarioAula> listarAulaPorTurmaCodigo(String codigo) {
		logger.info("Acessando método listarAulaPorTurmaCodigo...");
		
		logger.info("Listando todos os horários-aula da turma " + codigo + "!");
		return horarioAulaRepository.findAllByTurma_Codigo(codigo);
	}

	public void excluirAula(Long id) {
		logger.info("Acessando método excluirAula...");
		HorarioAula horarioAula = this.buscarAula(id);
		
		if (horarioAula != null)
			logger.info("Disciplina " + horarioAula.getTurma().getCodigo() + "dia " + 
					horarioAula.getDia().getDia() + " horário: " + 
					horarioAula.getHorario().getFaixa() + " deletada com sucesso!");
		horarioAulaRepository.delete(horarioAula);
	}
	
	public void validarParametros(Long id) {
		logger.info("Acessando método validarParametros...");
		
		validacaoService.validarId(id);
		
		logger.info("Parâmetros validados!");
	}
	
	public void validarParametros(String codigoTurma) {
		logger.info("Acessando método validarParametros...");
		
		validacaoService.validarCodigoTurma(codigoTurma);
		
		logger.info("Parâmetros validados!");
	}
	
	public void validarParametros(String codigoTurma, Integer dia, String horario) {
		logger.info("Acessando método validarParametros...");

		validacaoService.validarCodigoTurma(codigoTurma);
		validacaoService.validarDia(dia);
		validacaoService.validarHorario(horario);

		logger.info("Parâmetros validados!");
	}
}
