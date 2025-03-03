package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dto.CalificacionDTO;
import com.project.dto.GastrocalificaDTO;
import com.project.repository.CalificacionRepositoryImpl;
import com.project.repository.GastrocalificaRepositoryImpl;

@Service
public class GastrocalificaServiceImpl implements GastrocalificaService {

	@Autowired
	GastrocalificaRepositoryImpl gastrocalificaRepositoryImpl;
	
	@Autowired
	CalificacionRepositoryImpl calificacionRepositoryImpl;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear la calificacion de la gastronomia
	 */
	@Override
	public Integer creardetalGastronomia(GastrocalificaDTO gastrDto) throws Exception {
		Integer retornaCalificacion = 0;
		retornaCalificacion = calificacionRepositoryImpl.crearCalificacion(gastrDto.getCalicodi());
		if(retornaCalificacion == 0) {
			throw new Exception("Error al crear la calificación");
		}
		CalificacionDTO calificacion = new CalificacionDTO();
		calificacion.setCalicodi(retornaCalificacion);
		gastrDto.setCalicodi(calificacion);
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
