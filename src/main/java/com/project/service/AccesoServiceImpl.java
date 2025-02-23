package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.DTO.AccesoDTO;
import com.project.repository.AccesoRepositoryImpl;

@Service
public class AccesoServiceImpl implements AccesoService {

	@Autowired
	AccesoRepositoryImpl accesoRepositoryImpl;
		

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear el acceso del usuario
	 */
	@Override
	public AccesoDTO crearAccesoUsuario(AccesoDTO accesoDTO) throws Exception {
		return accesoRepositoryImpl.crearAccesoUsuario(accesoDTO);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear el acceso del usuario
	 */
	@Override
	public AccesoDTO validaAcceso(AccesoDTO accesoDTO) throws Exception {
		return accesoRepositoryImpl.validaAcceso(accesoDTO);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para recuperar el acceso del usuario
	 */
	@Override
	public AccesoDTO recuperaAcceso(AccesoDTO accesoDTO) throws Exception {
		return accesoRepositoryImpl.recuperaAcceso(accesoDTO);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar el acceso del usuario por documento y
	 *              usuario
	 */
	@Override
	public Integer modificaAcceso(AccesoDTO accesoDTO) throws Exception {
		return accesoRepositoryImpl.modificaAcceso(accesoDTO);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para inactivar el acceso del usuario por documento y
	 *              usuario
	 */
	@Override
	public Integer eliminarAcceso(AccesoDTO accesoDTO) throws Exception {
		return accesoRepositoryImpl.eliminarAcceso(accesoDTO);
	}


}
