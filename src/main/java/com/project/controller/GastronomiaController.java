package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.GastrocalificaDTO;
import com.project.dto.GastronomiaDTO;
import com.project.service.GastronomiaService;

@RestController
@RequestMapping("/api/v1/gastronomia")
public class GastronomiaController {

	@Autowired
	GastronomiaService gastronomiaService;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar los platos tipicos de la region
	 */
	@PostMapping("/consultaAllGastronomia")
	public ResponseEntity<List<GastrocalificaDTO>> consultaAllGastronomia(@RequestBody GastronomiaDTO gastronomiaDTO)
			throws Exception {
		return ResponseEntity.ok(gastronomiaService.consultaAllGastronomia(gastronomiaDTO));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear los platos tipicos
	 */
	@PostMapping("/crearGastronomia")
	public ResponseEntity<Integer> crearGastronomia(@RequestBody GastronomiaDTO gastronomiaDTO) throws Exception {
		return ResponseEntity.ok(gastronomiaService.crearGastronomia(gastronomiaDTO));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar los platos tipicos
	 */
	@PutMapping("/modificarGastronomia")
	public ResponseEntity<Integer> modificarGastronomia(@RequestBody GastronomiaDTO gastronomiaDTO) throws Exception {
		return ResponseEntity.ok(gastronomiaService.modificarGastronomia(gastronomiaDTO));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para inactivar los platos tipicos
	 */
	@PutMapping("/inactivarGastronomia")
	public ResponseEntity<Integer> inactivarGastronomia(@RequestBody GastronomiaDTO gastronomiaDTO) throws Exception {
		return ResponseEntity.ok(gastronomiaService.inactivarGastronomia(gastronomiaDTO));
	}

}
