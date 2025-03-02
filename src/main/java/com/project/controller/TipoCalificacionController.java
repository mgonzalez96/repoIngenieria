package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.TipoCalificacionDTO;
import com.project.service.TipoCalificacionService;

@RestController
@RequestMapping("/api/v1/tipoCalificacion")
public class TipoCalificacionController {

	@Autowired
	TipoCalificacionService tipoCalificacionService;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar los tipos de calificaciones
	 */
	@GetMapping("/consultaAllTipoCalificacion")
	public ResponseEntity<List<TipoCalificacionDTO>> consultaAllTipoCalificacion() throws Exception {
		return ResponseEntity.ok(tipoCalificacionService.consultaAllTipoCalificacion());
	}
}
