package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.ActividadDTO;
import com.project.service.ActividadService;

@RestController
@RequestMapping("/api/v1/actividad")
public class ActividadController {

	@Autowired
	ActividadService actividadService;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear las actividades unidad a un tipo de evento
	 */
	@PostMapping("/crearActividad")
	public ResponseEntity<Integer> crearActividad(@RequestBody ActividadDTO actividadDTO) throws Exception {
		return ResponseEntity.ok(actividadService.crearActividad(actividadDTO));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar todas las actividades con estado activo
	 */
	@GetMapping("/consultaAllActividad")
	public ResponseEntity<List<ActividadDTO>> consultaAllActividad() throws Exception {
		return ResponseEntity.ok(actividadService.consultaAllActividad());
	}

}
