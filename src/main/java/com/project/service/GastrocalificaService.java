package com.project.service;

import java.util.List;

import com.project.dto.GastrocalificaDTO;

public interface GastrocalificaService {
	
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear la calificacion de la gastronomia
	 */
	public Integer creardetalGastronomia(GastrocalificaDTO gastrDto) throws Exception;
	
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar los platos tipicos de la region
	 */
	public List<GastrocalificaDTO> consultaCalificaGastronomia() throws Exception;

}
