package com.project.service;

import com.project.dto.Perfil;

public interface PerfilService {

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear perfiles
	 */
	public Integer crearPerfil(Perfil perfil) throws Exception;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar los perfiles
	 */
	public Integer modificarPerfil(Perfil perfil) throws Exception;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para inactivar los perfiles
	 */
	public Integer inactivarPerfil(Perfil perfil) throws Exception;

}
