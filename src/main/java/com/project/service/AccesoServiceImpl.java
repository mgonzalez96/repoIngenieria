package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.dto.AccesoDTO;
import com.project.repository.AccesoRepositoryImpl;

@Service
public class AccesoServiceImpl implements AccesoService {

	@Autowired
	AccesoRepositoryImpl accesoRepositoryImpl;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear perfiles
	 */
	@Override
	public Integer crearPerfil(AccesoDTO accesoDTO) throws Exception {
		return accesoRepositoryImpl.crearPerfil(accesoDTO);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar los perfiles
	 */
	@Override
	public Integer modificarPerfil(AccesoDTO accesoDTO) throws Exception {
		return accesoRepositoryImpl.modificarPerfil(accesoDTO);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para inactivar los perfiles
	 */
	@Override
	public Integer inactivarPerfil(AccesoDTO accesoDTO) throws Exception {
		return accesoRepositoryImpl.inactivarPerfil(accesoDTO);
	}

}
