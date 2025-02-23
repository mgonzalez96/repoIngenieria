package com.project.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.DTO.UsuarioDTO;
import com.project.service.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear usuarios
	 */
	@PostMapping("/crearUsuario")
	public ResponseEntity<Integer> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) throws Exception {
		return ResponseEntity.ok(usuarioService.crearUsuario(usuarioDTO));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar usuarios por numero de documento
	 */
	@PutMapping("/modificarUsuario")
	public ResponseEntity<Integer> modificarUsuario(@RequestBody UsuarioDTO usuarioDTO) throws Exception {
		return ResponseEntity.ok(usuarioService.modificarUsuario(usuarioDTO));
	}


	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para consultar usuarios por numero de documento
	 */
	@PostMapping("/consultaUsuarioByDocumento")
	public ResponseEntity<UsuarioDTO> consultaUsuarioByDocumento(@RequestBody UsuarioDTO usuarioDTO) throws Exception {
		return ResponseEntity.ok(usuarioService.consultaUsuarioByDocumento(usuarioDTO));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para consultar todos los usuarios
	 */
	@GetMapping("/consultaAllUsuario")
	public ResponseEntity<List<UsuarioDTO>> consultaAllUsuario() throws Exception {
		return ResponseEntity.ok(usuarioService.consultaAllUsuario());
	}

	

}
