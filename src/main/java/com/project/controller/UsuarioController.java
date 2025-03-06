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
import com.project.dto.UsuarioDTO;
import com.project.service.AccesoService;
import com.project.service.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	AccesoService accesoService;

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
	 * @Descripcion Método para modificar el perfil del usuario
	 */
	@PutMapping("/modificarPerfil")
	public ResponseEntity<Integer> modificarPerfil(@RequestBody UsuarioDTO usuarioDTO) throws Exception {
		return ResponseEntity.ok(usuarioService.modificarPerfil(usuarioDTO));
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

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para validar el acceso del usuario
	 */
	@PostMapping("/validaAcceso")
	public ResponseEntity<UsuarioDTO> validaAcceso(@RequestBody UsuarioDTO usuarioDTO) throws Exception {
		return ResponseEntity.ok(usuarioService.validaAcceso(usuarioDTO));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para recuperar el acceso del usuario
	 */
	@PostMapping("/recuperaAcceso")
	public ResponseEntity<UsuarioDTO> recuperaAcceso(@RequestBody UsuarioDTO usuarioDTO) throws Exception {
		return ResponseEntity.ok(usuarioService.recuperaAcceso(usuarioDTO));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para inactivar el usuario por documento
	 */
	@PutMapping("/eliminarUsuario")
	public ResponseEntity<Integer> eliminarUsuario(@RequestBody UsuarioDTO usuarioDTO) throws Exception {
		return ResponseEntity.ok(usuarioService.eliminarUsuario(usuarioDTO));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para activar usuarios por numero de documento
	 */
	@PutMapping("/activarUsuario")
	public ResponseEntity<Integer> activarUsuario(@RequestBody UsuarioDTO usuarioDTO) throws Exception {
		return ResponseEntity.ok(usuarioService.activarUsuario(usuarioDTO));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar el acceso del usuario por documento
	 */
	@PutMapping("/modificaAcceso")
	public ResponseEntity<Integer> modificaAcceso(@RequestBody UsuarioDTO usuarioDTO) throws Exception {
		return ResponseEntity.ok(usuarioService.modificaAcceso(usuarioDTO));
	}

	@PostMapping("/olvidoPassword")
	public ResponseEntity<Integer> olvidoPassword(@RequestBody UsuarioDTO usuarioDTO) throws Exception {
		try {
			usuarioDTO = usuarioService.recuperaAcceso(usuarioDTO);
			if (usuarioDTO != null) {
				String detalle = "";
				usuarioDTO.setAsunto("Recuperación Credencial ConéctateConCandelariaValle");

				detalle = "Cordial saludo";
				detalle += "\n";
				detalle += "\n";
				detalle += "A continuación se encuentran los datos de acceso al App ConéctateConCandelariaValle";
				detalle += "\n";
				detalle += "\n";
				detalle += "Usuario: " + usuarioDTO.getUsuario();
				detalle += "\n";
				detalle += "Contraseña: " + usuarioDTO.getContrasena();
				detalle += "\n";
				detalle += "\n";
				detalle += "Este es un correo generado automáticamente, por favor abstenerse de responder este correo";

				usuarioDTO.setDescripcion(detalle);
			}
			usuarioService.sendEmail(usuarioDTO.getCorreo(), usuarioDTO.getAsunto(), usuarioDTO.getDescripcion());
			return ResponseEntity.ok(1);
		} catch (Exception e) {
			System.err.println("Exception UsuarioController olvidoPassword: " + e.toString());
			return ResponseEntity.ok(0);
		}
	}

}
