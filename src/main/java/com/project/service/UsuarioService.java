package com.project.service;

import java.util.List;

import com.project.DTO.UsuarioDTO;

public interface UsuarioService {
	
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear usuarios
	 */
	public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) throws Exception;
	
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar usuarios por numero de documento
	 */
	public Integer modificarUsuario(UsuarioDTO usuarioDTO) throws Exception;
	
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para eliminar usuarios por numero de documento
	 */
	public Integer eliminarUsuario(Integer documento) throws Exception;
	
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para consultar usuarios por numero de documento
	 */
	public UsuarioDTO consultaUsuarioByDocumento(UsuarioDTO usuarioDTO) throws Exception;
	
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para consultar todos los usuarios
	 */
	public List<UsuarioDTO> consultaAllUsuario() throws Exception;
	

}
