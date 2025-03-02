package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dto.GastronomiaDTO;
import com.project.repository.GastronomiaRepositoryImpl;

@Service
public class GastronomiaServiceImpl implements GastronomiaService {

	@Autowired
	GastronomiaRepositoryImpl gastronomiaRepositoryImpl;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar los platos tipicos de la region
	 */
	@Override
	public List<GastronomiaDTO> consultaAllGastronomia(GastronomiaDTO gastronomiaDTO) throws Exception {
		return gastronomiaRepositoryImpl.consultaAllGastronomia(gastronomiaDTO);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear los platos tipicos
	 */
	@Override
	public Integer crearGastronomia(GastronomiaDTO gastronomiaDTO) throws Exception {
		return gastronomiaRepositoryImpl.crearGastronomia(gastronomiaDTO);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar los platos tipicos
	 */
	@Override
	public Integer modificarGastronomia(GastronomiaDTO gastronomiaDTO) throws Exception {
		return gastronomiaRepositoryImpl.modificarGastronomia(gastronomiaDTO);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para inactivar los platos tipicos
	 */
	@Override
	public Integer inactivarGastronomia(GastronomiaDTO gastronomiaDTO) throws Exception {
		return gastronomiaRepositoryImpl.inactivarGastronomia(gastronomiaDTO);
	}

}
