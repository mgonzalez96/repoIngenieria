package com.project.service;

import java.util.List;

import com.project.dto.GastrocalificaDTO;
import com.project.dto.GastronomiaDTO;

public interface GastronomiaService {

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar los platos tipicos de la region
	 */
	public List<GastrocalificaDTO> consultaAllGastronomia(GastronomiaDTO gastronomiaDTO) throws Exception;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear los platos tipicos
	 */
	public Integer crearGastronomia(GastronomiaDTO gastronomiaDTO) throws Exception;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar los platos tipicos
	 */
	public Integer modificarGastronomia(GastronomiaDTO gastronomiaDTO) throws Exception;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para inactivar los platos tipicos
	 */
	public Integer inactivarGastronomia(GastronomiaDTO gastronomiaDTO) throws Exception;

}
