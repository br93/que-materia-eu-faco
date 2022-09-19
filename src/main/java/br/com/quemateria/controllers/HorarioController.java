package br.com.quemateria.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quemateria.dto.horario.ConsultaHorarioDTO;
import br.com.quemateria.dto.horario.HorarioMapper;
import br.com.quemateria.services.HorarioService;

@RestController
@RequestMapping("v1/horarios")
public class HorarioController {
	
	private final HorarioService horarioService;
	private final HorarioMapper horarioMapper;
	
	public HorarioController(HorarioService horarioService, HorarioMapper horarioMapper) {
		this.horarioService = horarioService;
		this.horarioMapper = horarioMapper;
	}
	
	@GetMapping("list")
	public ResponseEntity<Page<ConsultaHorarioDTO>> listarHorarios (@PageableDefault Pageable pageable){
		return ResponseEntity.ok(horarioService.listarHorarios(pageable).map(horarioMapper::toDTO));
	}

}
