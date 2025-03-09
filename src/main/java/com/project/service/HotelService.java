package com.project.service;

import java.util.List;

import com.project.dto.HotelDTO;

public interface HotelService {
	
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear los hoteles
	 */
	public Integer crearHotel(HotelDTO hotelDTO) throws Exception;
	
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar los hoteles registrados
	 */
	public Integer modificarHotel(HotelDTO hotelDTO) throws Exception;
	
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para inactivar un hotel por codigo
	 */
	public Integer cambiarEstadoHotel(HotelDTO hotelDTO) throws Exception;
	
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar los hoteles pasando el estado
	 */
	public List<HotelDTO> consultaHotelByEstado(HotelDTO hotel) throws Exception;

}
