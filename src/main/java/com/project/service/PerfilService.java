package com.project.service;

import java.util.List;

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
	 * @Descripcion Método para cambiar el estado de los perfiles
	 */
	public Integer cambiarEstadoPerfil(Perfil perfil) throws Exception;
	
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar los perfiles por estado
	 */
	public List<Perfil> consultaPerfilByEstado(Perfil perfil) throws Exception;

}
