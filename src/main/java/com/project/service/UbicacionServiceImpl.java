package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dto.UbicacionDTO;
import com.project.repository.UbicacionRepositoryImpl;

@Service
public class UbicacionServiceImpl implements UbicacionService {

	@Autowired
	UbicacionRepositoryImpl ubicacionRepositoryImpl;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar las ubicaciones creadas
	 */
	@Override
	public List<UbicacionDTO> consultaAllUbicacion() throws Exception {
		return ubicacionRepositoryImpl.consultaAllUbicacion();
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear las ubicaciones
	 */
	@Override
	public Integer crearUbicacion(UbicacionDTO ubicacionDTO) throws Exception {
		return ubicacionRepositoryImpl.crearUbicacion(ubicacionDTO);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar las ubicaciones por codigo de creacion
	 */
	@Override
	public Integer modificarUbicacion(UbicacionDTO ubicacionDTO) throws Exception {
		return ubicacionRepositoryImpl.modificarUbicacion(ubicacionDTO);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para eliminar la Ubicacion
	 */
	@Override
	public Integer eliminarUbicacion(Integer ubiccodi) throws Exception {
		return ubicacionRepositoryImpl.eliminarUbicacion(ubiccodi);
	}

}
