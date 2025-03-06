package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dto.Perfil;
import com.project.repository.PerfilRepositoryImpl;

@Service
public class PerfilServiceImpl implements PerfilService {

	@Autowired
	PerfilRepositoryImpl accesoRepositoryImpl;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear perfiles
	 */
	@Override
	public Integer crearPerfil(Perfil perfil) throws Exception {
		return accesoRepositoryImpl.crearPerfil(perfil);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar los perfiles
	 */
	@Override
	public Integer modificarPerfil(Perfil perfil) throws Exception {
		return accesoRepositoryImpl.modificarPerfil(perfil);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para inactivar los perfiles
	 */
	@Override
	public Integer inactivarPerfil(Perfil perfil) throws Exception {
		return accesoRepositoryImpl.inactivarPerfil(perfil);
	}

}
