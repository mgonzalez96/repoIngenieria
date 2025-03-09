package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.dto.HotelDTO;
import com.project.service.HotelService;

@RestController
@RequestMapping("/api/v1/hotel")
public class HotelController {

	@Autowired
	HotelService hotelService;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear los hoteles
	 */
	@PostMapping("/crearHotel")
	public ResponseEntity<Integer> crearHotel(@RequestBody HotelDTO hotelDTO) throws Exception {
		return ResponseEntity.ok(hotelService.crearHotel(hotelDTO));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar los hoteles registrados
	 */
	@PutMapping("/modificarHotel")
	public ResponseEntity<Integer> modificarHotel(@RequestBody HotelDTO hotelDTO) throws Exception {
		return ResponseEntity.ok(hotelService.modificarHotel(hotelDTO));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para inactivar un hotel por codigo
	 */
	@PutMapping("/cambiarEstadoHotel")
	public ResponseEntity<Integer> cambiarEstadoHotel(@RequestBody HotelDTO hotelDTO) throws Exception {
		return ResponseEntity.ok(hotelService.cambiarEstadoHotel(hotelDTO));
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar los hoteles pasando el estado
	 */
	@PostMapping("/consultaHotelByEstado")
	public ResponseEntity<List<HotelDTO>> consultaHotelByEstado(@RequestBody HotelDTO hotel) throws Exception {
		return ResponseEntity.ok(hotelService.consultaHotelByEstado(hotel));
	}

}
