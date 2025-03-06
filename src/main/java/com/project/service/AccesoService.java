package com.project.service;

import com.project.dto.AccesoDTO;

public interface AccesoService {

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear perfiles
	 */
	public Integer crearPerfil(AccesoDTO accesoDTO) throws Exception;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar los perfiles
	 */
	public Integer modificarPerfil(AccesoDTO accesoDTO) throws Exception;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para inactivar los perfiles
	 */
	public Integer inactivarPerfil(AccesoDTO accesoDTO) throws Exception;

}
