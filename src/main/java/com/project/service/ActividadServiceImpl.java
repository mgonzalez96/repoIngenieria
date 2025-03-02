package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dto.ActividadDTO;
import com.project.repository.ActividadRepositoryImpl;

@Service
public class ActividadServiceImpl implements ActividadService {

	@Autowired
	ActividadRepositoryImpl actividadRepositoryImpl;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear las actividades unidad a un tipo de evento
	 */
	@Override
	public Integer crearActividad(ActividadDTO actividadDTO) throws Exception {
		return actividadRepositoryImpl.crearActividad(actividadDTO);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar todas las actividades con estado activo
	 */
	@Override
	public List<ActividadDTO> consultaAllActividad(ActividadDTO actividadDTO) throws Exception {
		return actividadRepositoryImpl.consultaAllActividad(actividadDTO);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar las actividades
	 */
	@Override
	public Integer modificarActividad(ActividadDTO actividadDTO) throws Exception {
		return actividadRepositoryImpl.modificarActividad(actividadDTO);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para inactivar una actividad por codigo
	 */
	@Override
	public Integer inactivarActividad(Integer acticodi) throws Exception {
		return actividadRepositoryImpl.inactivarActividad(acticodi);
	}

}
