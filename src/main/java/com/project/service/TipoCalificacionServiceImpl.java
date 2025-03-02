package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dto.TipoCalificacionDTO;
import com.project.repository.TipoCalificacionRepositoryImpl;

@Service
public class TipoCalificacionServiceImpl implements TipoCalificacionService {

	@Autowired
	TipoCalificacionRepositoryImpl tipoCalificacionRepositoryImpl;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar los tipos de calificaciones
	 */
	@Override
	public List<TipoCalificacionDTO> consultaAllTipoCalificacion() throws Exception {
		return tipoCalificacionRepositoryImpl.consultaAllTipoCalificacion();
	}
}
