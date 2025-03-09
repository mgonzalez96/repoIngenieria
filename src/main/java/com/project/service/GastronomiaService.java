package com.project.service;

import java.util.List;
import com.project.dto.CalificacionDTO;
import com.project.dto.GastronomiaDTO;

public interface GastronomiaService {

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar los platos tipicos de la region
	 */
	public List<CalificacionDTO> consultaAllGastronomia(GastronomiaDTO gastronomiaDTO) throws Exception;

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
	 * @Descripcion Método para cambiar el estado de los platos tipicos
	 */
	public Integer cambiarEstadoGastronomia(GastronomiaDTO gastronomiaDTO) throws Exception;
	
	
}
