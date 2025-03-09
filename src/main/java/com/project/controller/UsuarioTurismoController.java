package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.UsuarioTurismoDTO;
import com.project.service.UsuarioTurismoService;

@RestController
@RequestMapping("/api/v1/usuarioTurismo")
public class UsuarioTurismoController {

	@Autowired
	UsuarioTurismoService usuarioTurismoService;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Crea el like del sitio turistico
	 */
	@PostMapping("/crearLikeSitioTuristico")
	public ResponseEntity<Integer> crearLikeSitioTuristico(@RequestBody UsuarioTurismoDTO turismo) throws Exception {
		return ResponseEntity.ok(usuarioTurismoService.crearLikeSitioTuristico(turismo));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Elimina el like del sitio turistico
	 */
	@PostMapping("/eliminaLikeSitioTuristico")
	public ResponseEntity<Integer> eliminaLikeSitioTuristico(@RequestBody UsuarioTurismoDTO turismo) throws Exception {
		return ResponseEntity.ok(usuarioTurismoService.eliminaLikeSitioTuristico(turismo));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Consulta el like del sitio turistico por documento y codigo de
	 *              sitio
	 */
	@PostMapping("/getlikeTurismo")
	public ResponseEntity<Integer> getlikeTurismo(@RequestBody UsuarioTurismoDTO turismo) throws Exception {
		return ResponseEntity.ok(usuarioTurismoService.getlikeTurismo(turismo));
	}

}
