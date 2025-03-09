package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	@PostMapping("/consultaAllActividad")
	public ResponseEntity<List<ActividadDTO>> consultaAllActividad(@RequestBody ActividadDTO actividadDTO) throws Exception {
		return ResponseEntity.ok(actividadService.consultaAllActividad(actividadDTO));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar las actividades
	 */
	@PutMapping("/modificarActividad")
	public ResponseEntity<Integer> modificarActividad(@RequestBody ActividadDTO actividadDTO) throws Exception {
		return ResponseEntity.ok(actividadService.modificarActividad(actividadDTO));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para cambiar el estado de una actividad por codigo
	 */
	@PutMapping("/cambiarEstadoActividad")
	public ResponseEntity<Integer> cambiarEstadoActividad(@RequestBody ActividadDTO actividadDTO) throws Exception {
		return ResponseEntity.ok(actividadService.cambiarEstadoActividad(actividadDTO));
	}
	

}
