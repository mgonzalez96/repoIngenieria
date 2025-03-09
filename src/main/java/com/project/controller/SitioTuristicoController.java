package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.SitioTuristicoDTO;
import com.project.service.SitioTuristicoService;

@RestController
@RequestMapping("/api/v1/sitioTuristico")
public class SitioTuristicoController {

	@Autowired
	SitioTuristicoService sitioTuristicoService;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar todos los sitios turisticos
	 */
	@PostMapping("/consultaAllSitioTuristico")
	public ResponseEntity<List<SitioTuristicoDTO>> consultaAllSitioTuristico(
			@RequestBody SitioTuristicoDTO sitioTuristicoDTO) throws Exception {
		return ResponseEntity.ok(sitioTuristicoService.consultaAllSitioTuristico(sitioTuristicoDTO));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear los sitios turisticos unidos a una ubicacion
	 */
	@PostMapping("/crearSitioTuristico")
	public ResponseEntity<Integer> crearSitioTuristico(@RequestBody SitioTuristicoDTO sitioTuristicoDTO)
			throws Exception {
		return ResponseEntity.ok(sitioTuristicoService.crearSitioTuristico(sitioTuristicoDTO));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar los sitios turisticos unidos a una
	 *              ubicacion
	 */
	@PutMapping("/modificarSitioTuristico")
	public ResponseEntity<Integer> modificarSitioTuristico(@RequestBody SitioTuristicoDTO sitioTuristicoDTO)
			throws Exception {
		return ResponseEntity.ok(sitioTuristicoService.modificarSitioTuristico(sitioTuristicoDTO));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para cambiar el estado de los sitios turisticos
	 */
	@PutMapping("/cambiarEstadoSitioTuristico")
	public ResponseEntity<Integer> cambiarEstadoSitioTuristico(@RequestBody SitioTuristicoDTO sitioTuristicoDTO)
			throws Exception {
		return ResponseEntity.ok(sitioTuristicoService.cambiarEstadoSitioTuristico(sitioTuristicoDTO));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para poner like al sitio turistico
	 */
	@PutMapping("/likeSitioTuristico")
	public ResponseEntity<Integer> likeSitioTuristico(@RequestBody SitioTuristicoDTO sitioTuristicoDTO)
			throws Exception {
		return ResponseEntity.ok(sitioTuristicoService.likeSitioTuristico(sitioTuristicoDTO));
	}

}
