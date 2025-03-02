package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.GastrocalificaDTO;
import com.project.service.GastrocalificaService;

@RestController
@RequestMapping("/api/v1/gastrocalifica")
public class GastrocalificaController {

	@Autowired
	GastrocalificaService gastrocalificaService;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear la calificacion de la gastronomia
	 */
	@PostMapping("/creardetalGastronomia")
	public ResponseEntity<Integer> creardetalGastronomia(@RequestBody GastrocalificaDTO gastrDto) throws Exception {
		return ResponseEntity.ok(gastrocalificaService.creardetalGastronomia(gastrDto));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar los platos tipicos de la region
	 */
	@GetMapping("/consultaCalificaGastronomia")
	public ResponseEntity<List<GastrocalificaDTO>> consultaCalificaGastronomia() throws Exception {
		return ResponseEntity.ok(gastrocalificaService.consultaCalificaGastronomia());
	}
}
