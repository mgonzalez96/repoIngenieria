package com.project.service;

import java.util.List;

import com.project.dto.ActividadDTO;

public interface ActividadService {

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear las actividades unidad a un tipo de evento
	 */
	public Integer crearActividad(ActividadDTO actividadDTO) throws Exception;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar todas las actividades con estado activo
	 */
	public List<ActividadDTO> consultaAllActividad() throws Exception;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar las actividades
	 */
	public Integer modificarActividad(ActividadDTO actividadDTO) throws Exception;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para inactivar una actividad por codigo
	 */
	public Integer inactivarActividad(Integer acticodi) throws Exception;
}
