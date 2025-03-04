package com.project.service;

import java.util.List;

import com.project.dto.CalificacionDTO;

public interface CalificacionService {

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear la calificacion
	 */
	public Integer crearCalificacion(CalificacionDTO calificacionDTO) throws Exception;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar los platos tipicos de la region
	 */
	public List<CalificacionDTO> consultaCalificaGastronomia() throws Exception;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método consultar la gastronomia por calificacion
	 */
	public List<CalificacionDTO> consGastronomiaByCalificacion(CalificacionDTO calificacion) throws Exception;

}
