package com.project.service;

import java.util.List;

import com.project.dto.TipoCalificacionDTO;

public interface TipoCalificacionService {

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar los tipos de calificaciones
	 */
	public List<TipoCalificacionDTO> consultaAllTipoCalificacion() throws Exception;
}
