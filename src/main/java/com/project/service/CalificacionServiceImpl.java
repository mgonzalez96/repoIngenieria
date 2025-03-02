package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dto.CalificacionDTO;
import com.project.repository.CalificacionRepositoryImpl;

@Service
public class CalificacionServiceImpl implements CalificacionService {

	@Autowired
	CalificacionRepositoryImpl calificacionRepositoryImpl;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear la calificacion
	 */
	@Override
	public Integer crearCalificacion(CalificacionDTO calificacionDTO) throws Exception {
		return calificacionRepositoryImpl.crearCalificacion(calificacionDTO);
	}

}
