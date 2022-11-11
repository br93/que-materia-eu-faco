package br.com.quemateria.controllers;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.quemateria.dto.aluno.AlunoMapper;
import br.com.quemateria.dto.aluno.ConsultaAlunoDTO;
import br.com.quemateria.dto.aluno.RegistroAlunoDTO;
import br.com.quemateria.entities.Aluno;
import br.com.quemateria.entities.Usuario;
import br.com.quemateria.services.AlunoService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("v1/alunos")
@Validated
@AllArgsConstructor
public class AlunoController {

	private final AlunoService alunoService;
	private final AlunoMapper alunoMapper;
	

	@GetMapping
	public ResponseEntity<ConsultaAlunoDTO> buscarAluno(
			@RequestParam @Length(min = 8, max = 8) @Pattern(regexp = "^[a][0-9]{7}", message = "Formato a0000000") String registro) {
		return ResponseEntity.ok(alunoMapper.toDTO(alunoService.buscarAlunoPorRegistro(registro)));
	}

	@GetMapping("list")
	public ResponseEntity<Page<ConsultaAlunoDTO>> listarAlunos(@PageableDefault Pageable pageable) {
		return ResponseEntity.ok(alunoService.listarAlunos(pageable).map(alunoMapper::toDTO));
	}

	@PostMapping("add")
	public ResponseEntity<ConsultaAlunoDTO> salvarAluno(@Valid @RequestBody RegistroAlunoDTO registroAlunoDTO, Authentication auth){
		Usuario login = (Usuario) auth.getPrincipal();
				
		Aluno aluno = alunoMapper.toEntity(registroAlunoDTO);
		Aluno alunoSalvo = alunoService.salvarAluno(aluno, login.getId());

		return ResponseEntity.ok(alunoMapper.toDTO(alunoSalvo));
	}

	@DeleteMapping("delete/{registro}")
	public ResponseEntity<ConsultaAlunoDTO> deletarAluno(
			@PathVariable @Length(min = 8, max = 8) @Pattern(regexp = "^[a][0-9]{7}", message = "Formato a0000000") String registro, Authentication auth) {
		
		Usuario login = (Usuario) auth.getPrincipal();
		Aluno aluno = alunoService.buscarAluno(login.getId());

        if (aluno.getRegistro().equals(registro)) {
        	alunoService.excluirAluno(registro);
        	return ResponseEntity.noContent().build();
        }
		
        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

	}

}
