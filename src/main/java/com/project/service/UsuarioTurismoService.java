package com.project.service;

import com.project.dto.UsuarioTurismoDTO;

public interface UsuarioTurismoService {
	
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Crea el like del sitio turistico
	 */
	public Integer crearLikeSitioTuristico(UsuarioTurismoDTO turismo) throws Exception;
	
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Elimina el like del sitio turistico
	 */
	public Integer eliminaLikeSitioTuristico(UsuarioTurismoDTO turismo) throws Exception;
	
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Consulta el like del sitio turistico por documento y codigo de
	 *              sitio
	 */
	public Integer getlikeTurismo(UsuarioTurismoDTO turismo) throws Exception;

}
