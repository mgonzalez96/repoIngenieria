package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.Perfil;
import com.project.service.PerfilService;

@RestController
@RequestMapping("/api/v1/perfil")
public class PerfilController {

	@Autowired
	PerfilService accesoService;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear perfiles
	 */
	@PostMapping("/crearPerfil")
	public ResponseEntity<Integer> crearPerfil(@RequestBody Perfil perfil) throws Exception {
		return ResponseEntity.ok(accesoService.crearPerfil(perfil));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar los perfiles
	 */
	@PutMapping("/modificarPerfil")
	public ResponseEntity<Integer> modificarPerfil(@RequestBody Perfil perfil) throws Exception {
		return ResponseEntity.ok(accesoService.modificarPerfil(perfil));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para inactivar los perfiles
	 */
	@PutMapping("/inactivarPerfil")
	public ResponseEntity<Integer> inactivarPerfil(@RequestBody Perfil perfil) throws Exception {
		return ResponseEntity.ok(accesoService.inactivarPerfil(perfil));
	}

}
