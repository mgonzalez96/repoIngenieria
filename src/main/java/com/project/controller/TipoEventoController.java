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

import com.project.dto.TipoEventoDTO;
import com.project.service.TipoEventoService;

@RestController
@RequestMapping("/api/v1/tipoEvento")
public class TipoEventoController {

	@Autowired
	TipoEventoService tipoEventoService;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar todos los tipos de eventos creados
	 */
	@GetMapping("/consultaAllTipoEvento")
	public ResponseEntity<List<TipoEventoDTO>> consultaAllTipoEvento() throws Exception {
		return ResponseEntity.ok(tipoEventoService.consultaAllTipoEvento());
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear el tipo de evento
	 */
	@PostMapping("/crearTipoEvento")
	public ResponseEntity<Integer> crearTipoEvento(@RequestBody TipoEventoDTO tipoEventoDTO) throws Exception {
		return ResponseEntity.ok(tipoEventoService.crearTipoEvento(tipoEventoDTO));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar el tipo de evento
	 */
	@PutMapping("/modificarTipoEvento")
	public ResponseEntity<Integer> modificarTipoEvento(@RequestBody TipoEventoDTO tipoEventoDTO) throws Exception {
		return ResponseEntity.ok(tipoEventoService.modificarTipoEvento(tipoEventoDTO));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para eliminar un evento por codigo
	 */
	@DeleteMapping("/eliminarTipoEvento/{evencodi}")
	public ResponseEntity<Integer> eliminarTipoEvento(@PathVariable Integer evencodi) throws Exception {
		return ResponseEntity.ok(tipoEventoService.eliminarTipoEvento(evencodi));
	}

}
