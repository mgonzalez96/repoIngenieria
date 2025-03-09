package com.project.controller;

import java.util.List;

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
	PerfilService perfilService;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear perfiles
	 */
	@PostMapping("/crearPerfil")
	public ResponseEntity<Integer> crearPerfil(@RequestBody Perfil perfil) throws Exception {
		return ResponseEntity.ok(perfilService.crearPerfil(perfil));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar los perfiles
	 */
	@PutMapping("/modificarPerfil")
	public ResponseEntity<Integer> modificarPerfil(@RequestBody Perfil perfil) throws Exception {
		return ResponseEntity.ok(perfilService.modificarPerfil(perfil));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para cambiar el estado de los perfiles
	 */
	@PutMapping("/cambiarEstadoPerfil")
	public ResponseEntity<Integer> cambiarEstadoPerfil(@RequestBody Perfil perfil) throws Exception {
		return ResponseEntity.ok(perfilService.cambiarEstadoPerfil(perfil));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar los perfiles por estado
	 */
	@PostMapping("/consultaPerfilByEstado")
	public ResponseEntity<List<Perfil>> consultaPerfilByEstado(@RequestBody Perfil perfil) throws Exception {
		return ResponseEntity.ok(perfilService.consultaPerfilByEstado(perfil));
	}

}
