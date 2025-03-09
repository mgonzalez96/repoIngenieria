package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dto.HotelDTO;
import com.project.repository.HotelRepositoryImpl;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	HotelRepositoryImpl hotelRepositoryImpl;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear los hoteles
	 */
	@Override
	public Integer crearHotel(HotelDTO hotelDTO) throws Exception {
		return hotelRepositoryImpl.crearHotel(hotelDTO);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar los hoteles registrados
	 */
	@Override
	public Integer modificarHotel(HotelDTO hotelDTO) throws Exception {
		return hotelRepositoryImpl.modificarHotel(hotelDTO);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para inactivar un hotel por codigo
	 */
	@Override
	public Integer cambiarEstadoHotel(HotelDTO hotelDTO) throws Exception {
		return hotelRepositoryImpl.cambiarEstadoHotel(hotelDTO);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar los hoteles pasando el estado
	 */
	@Override
	public List<HotelDTO> consultaHotelByEstado(HotelDTO hotel) throws Exception {
		return hotelRepositoryImpl.consultaHotelByEstado(hotel);
	}

}
