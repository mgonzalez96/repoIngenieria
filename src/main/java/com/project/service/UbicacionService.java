package com.project.service;

import java.util.List;

import com.project.dto.UbicacionDTO;

public interface UbicacionService {

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar las ubicaciones creadas
	 */
	public List<UbicacionDTO> consultaAllUbicacion() throws Exception;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear las ubicaciones
	 */
	public Integer crearUbicacion(UbicacionDTO ubicacionDTO) throws Exception;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar las ubicaciones por codigo de creacion
	 */
	public Integer modificarUbicacion(UbicacionDTO ubicacionDTO) throws Exception;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para eliminar la Ubicacion
	 */
	public Integer eliminarUbicacion(Integer ubiccodi) throws Exception;
}
