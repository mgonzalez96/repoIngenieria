package com.project.service;

import com.project.DTO.AccesoDTO;

public interface AccesoService {

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear el acceso del usuario
	 */
	public Integer crearAccesoUsuario(AccesoDTO accesoDTO) throws Exception;
	
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear el acceso del usuario
	 */
	public AccesoDTO validaAcceso(AccesoDTO accesoDTO) throws Exception;
		
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para recuperar el acceso del usuario
	 */
	public AccesoDTO recuperaAcceso(AccesoDTO accesoDTO) throws Exception;
	
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar el acceso del usuario por documento y
	 *              usuario
	 */
	public Integer modificaAcceso(AccesoDTO accesoDTO) throws Exception;
	
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para inactivar el acceso del usuario por documento y
	 *              usuario
	 */
	public Integer eliminarAcceso(AccesoDTO accesoDTO) throws Exception;
	
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para enviar por correo credenciales olvidadas
	 */
	public void sendEmail(String para, String asunto, String descripcion) throws Exception;

}
