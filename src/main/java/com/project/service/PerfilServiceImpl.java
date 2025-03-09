package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dto.Perfil;
import com.project.repository.PerfilRepositoryImpl;

@Service
public class PerfilServiceImpl implements PerfilService {

	@Autowired
	PerfilRepositoryImpl perfilRepositoryImpl;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear perfiles
	 */
	@Override
	public Integer crearPerfil(Perfil perfil) throws Exception {
		return perfilRepositoryImpl.crearPerfil(perfil);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar los perfiles
	 */
	@Override
	public Integer modificarPerfil(Perfil perfil) throws Exception {
		return perfilRepositoryImpl.modificarPerfil(perfil);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para cambiar el estado de los perfiles
	 */
	@Override
	public Integer cambiarEstadoPerfil(Perfil perfil) throws Exception {
		return perfilRepositoryImpl.cambiarEstadoPerfil(perfil);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar los perfiles por estado
	 */
	@Override
	public List<Perfil> consultaPerfilByEstado(Perfil perfil) throws Exception {
		return perfilRepositoryImpl.consultaPerfilByEstado(perfil);
	}

}
