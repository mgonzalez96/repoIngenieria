package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.CalificacionDTO;
import com.project.service.CalificacionService;

@RestController
@RequestMapping("/api/v1/calificacion")
public class CalificacionController {

	@Autowired
	CalificacionService calificacionService;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear la calificacion
	 */
	@PostMapping("/crearCalificacion")
	public ResponseEntity<Integer> crearCalificacion(@RequestBody CalificacionDTO calificacionDTO) throws Exception {
		return ResponseEntity.ok(calificacionService.crearCalificacion(calificacionDTO));
	}

}
