package com.project.service;

import java.util.List;

import com.project.dto.UsuarioDTO;

public interface UsuarioService {

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear usuarios
	 */
	public Integer crearUsuario(UsuarioDTO usuarioDTO) throws Exception;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar usuarios por numero de documento
	 */
	public Integer modificarUsuario(UsuarioDTO usuarioDTO) throws Exception;
	
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar el perfil del usuario
	 */
	public Integer modificarPerfil(UsuarioDTO usuarioDTO) throws Exception;
	
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar el perfil del usuario
	 */
	public Integer eliminarUsuario(UsuarioDTO usuarioDTO) throws Exception;

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
	
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear el acceso del usuario
	 */
	public UsuarioDTO validaAcceso(UsuarioDTO user) throws Exception;
	
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para recuperar el acceso del usuario
	 */
	public UsuarioDTO recuperaAcceso(UsuarioDTO usuarioDTO) throws Exception ;
	
	
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para activar usuarios por numero de documento
	 */
	public Integer activarUsuario(UsuarioDTO usuarioDTO) throws Exception;
	
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar el acceso del usuario por documento y
	 *              usuario
	 */
	public Integer modificaAcceso(UsuarioDTO usuario) throws Exception;
	
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para enviar por correo credenciales olvidadas
	 */
	public void sendEmail(String para, String asunto, String descripcion) throws Exception;

}
