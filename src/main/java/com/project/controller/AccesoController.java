package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.DTO.AccesoDTO;
import com.project.DTO.UsuarioDTO;
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
	public ResponseEntity<Integer> crearAccesoUsuario(@RequestBody AccesoDTO accesoDTO) throws Exception {
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

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para inactivar el acceso del usuario por documento y
	 *              usuario
	 */
	@PutMapping("/eliminarAcceso")
	public ResponseEntity<Integer> eliminarAcceso(@RequestBody AccesoDTO accesoDTO) throws Exception {
		return ResponseEntity.ok(accesoService.eliminarAcceso(accesoDTO));
	}

	@PostMapping("/olvidoPassword")
	public ResponseEntity<Integer> olvidoPassword(@RequestBody AccesoDTO accesoDTO) throws Exception {
		try {
			accesoDTO = accesoService.recuperaAcceso(accesoDTO);
			if (accesoDTO != null) {
				String detalle = "";
				accesoDTO.setAsunto("Recuperación Credencial ConéctateConCandelariaValle");

				detalle = "Cordial saludo";
				detalle += "\n";
				detalle += "\n";
				detalle += "A continuación se encuentran los datos de acceso al App ConéctateConCandelariaValle";
				detalle += "\n";
				detalle += "\n";
				detalle += "Usuario: " + accesoDTO.getUsername();
				detalle += "\n";
				detalle += "Contraseña: " + accesoDTO.getPassword();
				detalle += "\n";
				detalle += "\n";
				detalle += "Este es un correo generado automáticamente, por favor abstenerse de responder este correo";

				accesoDTO.setDescripcion(detalle);
			}
			accesoService.sendEmail(accesoDTO.getDocumento().getEmail(), accesoDTO.getAsunto(),
					accesoDTO.getDescripcion());
			return ResponseEntity.ok(1);
		} catch (Exception e) {
			System.err.println("Exception AccesoController olvidoPassword: " + e.toString());
			return ResponseEntity.ok(0);
		}
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para consultar los datos del acceso del usuario por
	 *              documento
	 */
	@PostMapping("/datosAcceso")
	public ResponseEntity<AccesoDTO> datosAcceso(@RequestBody UsuarioDTO usuario) throws Exception {
		return ResponseEntity.ok(accesoService.datosAcceso(usuario));
	}

}
