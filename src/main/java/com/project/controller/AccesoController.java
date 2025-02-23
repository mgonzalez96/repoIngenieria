package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.DTO.AccesoDTO;
import com.project.service.AccesoService;

@RestController
@RequestMapping("/api/v1/acceso")
public class AccesoController {

	@Autowired
	AccesoService accesoService;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear el acceso del usuario
	 */
	@PostMapping("/crearAccesoUsuario")
	public ResponseEntity<AccesoDTO> crearAccesoUsuario(@RequestBody AccesoDTO accesoDTO) throws Exception {
		return ResponseEntity.ok(accesoService.crearAccesoUsuario(accesoDTO));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear el acceso del usuario
	 */
	@PostMapping("/validaAcceso")
	public ResponseEntity<AccesoDTO> validaAcceso(@RequestBody AccesoDTO accesoDTO) throws Exception {
		return ResponseEntity.ok(accesoService.validaAcceso(accesoDTO));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para recuperar el acceso del usuario
	 */
	@PostMapping("/recuperaAcceso")
	public ResponseEntity<AccesoDTO> recuperaAcceso(@RequestBody AccesoDTO accesoDTO) throws Exception {
		return ResponseEntity.ok(accesoService.recuperaAcceso(accesoDTO));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar el acceso del usuario por documento y
	 *              usuario
	 */
	@PutMapping("/modificaAcceso")
	public ResponseEntity<Integer> modificaAcceso(@RequestBody AccesoDTO accesoDTO) throws Exception {
		return ResponseEntity.ok(accesoService.modificaAcceso(accesoDTO));
	}

}
