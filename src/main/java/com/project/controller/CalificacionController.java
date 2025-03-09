package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.CalificacionDTO;
import com.project.dto.GastronomiaDTO;
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

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar los platos tipicos de la region
	 */
	@GetMapping("/consultaCalificaGastronomia")
	public ResponseEntity<List<CalificacionDTO>> consultaCalificaGastronomia() throws Exception {
		return ResponseEntity.ok(calificacionService.consultaCalificaGastronomia());
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método consultar la gastronomia por calificacion
	 */
	@PostMapping("/consGastronomiaByCalificacion")
	public ResponseEntity<List<CalificacionDTO>> consGastronomiaByCalificacion(
			@RequestBody CalificacionDTO calificacion) throws Exception {
		return ResponseEntity.ok(calificacionService.consGastronomiaByCalificacion(calificacion));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Consulta El promedio de calificacion por codigo de gastronomia
	 */
	@PostMapping("/getPromedioCalificacion")
	public ResponseEntity<CalificacionDTO> getPromedioCalificacion(@RequestBody GastronomiaDTO gastcodi) throws Exception {
		return ResponseEntity.ok(calificacionService.getPromedioCalificacion(gastcodi));
	}

}
