package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dto.UsuarioTurismoDTO;
import com.project.repository.UsuarioTurismoRepositoryImpl;

@Service
public class UsuarioTurismoServiceImpl implements UsuarioTurismoService {

	@Autowired
	UsuarioTurismoRepositoryImpl usuarioTurismoRepositoryImpl;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Crea el like del sitio turistico
	 */
	@Override
	public Integer crearLikeSitioTuristico(UsuarioTurismoDTO turismo) throws Exception {
		return usuarioTurismoRepositoryImpl.crearLikeSitioTuristico(turismo);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Elimina el like del sitio turistico
	 */
	@Override
	public Integer eliminaLikeSitioTuristico(UsuarioTurismoDTO turismo) throws Exception {
		return usuarioTurismoRepositoryImpl.eliminaLikeSitioTuristico(turismo);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Consulta el like del sitio turistico por documento y codigo de
	 *              sitio
	 */
	@Override
	public Integer getlikeTurismo(UsuarioTurismoDTO turismo) throws Exception {
		return usuarioTurismoRepositoryImpl.getlikeTurismo(turismo);
	}

}
