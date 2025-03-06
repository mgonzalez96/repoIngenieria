package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.dto.AccesoDTO;
import com.project.service.AccesoService;

@RestController
@RequestMapping("/api/v1/acceso")
public class AccesoController {

	@Autowired
	AccesoService accesoService;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear perfiles
	 */
	@PostMapping("/crearPerfil")
	public ResponseEntity<Integer> crearPerfil(@RequestBody AccesoDTO accesoDTO) throws Exception {
		return ResponseEntity.ok(accesoService.crearPerfil(accesoDTO));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar los perfiles
	 */
	@PutMapping("/modificarPerfil")
	public ResponseEntity<Integer> modificarPerfil(@RequestBody AccesoDTO accesoDTO) throws Exception {
		return ResponseEntity.ok(accesoService.modificarPerfil(accesoDTO));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para inactivar los perfiles
	 */
	@PutMapping("/inactivarPerfil")
	public ResponseEntity<Integer> inactivarPerfil(@RequestBody AccesoDTO accesoDTO) throws Exception {
		return ResponseEntity.ok(accesoService.inactivarPerfil(accesoDTO));
	}

}
