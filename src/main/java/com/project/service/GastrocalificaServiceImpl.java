package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dto.GastrocalificaDTO;
import com.project.repository.GastrocalificaRepositoryImpl;

@Service
public class GastrocalificaServiceImpl implements GastrocalificaService {

	@Autowired
	GastrocalificaRepositoryImpl gastrocalificaRepositoryImpl;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear la calificacion de la gastronomia
	 */
	@Override
	public Integer creardetalGastronomia(GastrocalificaDTO gastrDto) throws Exception {
		return gastrocalificaRepositoryImpl.creardetalGastronomia(gastrDto);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar los platos tipicos de la region
	 */
	@Override
	public List<GastrocalificaDTO> consultaCalificaGastronomia() throws Exception {
		return gastrocalificaRepositoryImpl.consultaCalificaGastronomia();
	}

}
