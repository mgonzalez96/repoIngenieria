package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.UbicacionDTO;
import com.project.service.UbicacionService;

@RestController
@RequestMapping("/api/v1/ubicacion")
public class UbicacionController {

	@Autowired
	UbicacionService ubicacionService;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar las ubicaciones creadas
	 */
	@GetMapping("/consultaAllUbicacion")
	public ResponseEntity<List<UbicacionDTO>> consultaAllUbicacion() throws Exception {
		return ResponseEntity.ok(ubicacionService.consultaAllUbicacion());
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear las ubicaciones
	 */
	@PostMapping("/crearUbicacion")
	public ResponseEntity<Integer> crearUbicacion(@RequestBody UbicacionDTO ubicacionDTO) throws Exception {
		return ResponseEntity.ok(ubicacionService.crearUbicacion(ubicacionDTO));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar las ubicaciones por codigo de creacion
	 */
	@PutMapping("/modificarUbicacion")
	public ResponseEntity<Integer> modificarUbicacion(@RequestBody UbicacionDTO ubicacionDTO) throws Exception {
		return ResponseEntity.ok(ubicacionService.modificarUbicacion(ubicacionDTO));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para eliminar la Ubicacion
	 */
	@DeleteMapping("/eliminarUbicacion/{ubiccodi}")
	public ResponseEntity<Integer> eliminarUbicacion(@PathVariable Integer ubiccodi) throws Exception {
		return ResponseEntity.ok(ubicacionService.eliminarUbicacion(ubiccodi));
	}

}
