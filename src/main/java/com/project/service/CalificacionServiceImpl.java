package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dto.CalificacionDTO;
import com.project.dto.GastronomiaDTO;
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

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar los platos tipicos de la region
	 */
	@Override
	public List<CalificacionDTO> consultaCalificaGastronomia() throws Exception {
		return calificacionRepositoryImpl.consultaCalificaGastronomia();
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método consultar la gastronomia por calificacion
	 */
	@Override
	public List<CalificacionDTO> consGastronomiaByCalificacion(CalificacionDTO calificacion) throws Exception {
		return calificacionRepositoryImpl.consGastronomiaByCalificacion(calificacion);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Consulta El promedio de calificacion por codigo de gastronomia
	 */
	@Override
	public CalificacionDTO getPromedioCalificacion(GastronomiaDTO gastcodi) throws Exception {
		return calificacionRepositoryImpl.getPromedioCalificacion(gastcodi);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Lista las calificaciones por usuario
	 */
	@Override
	public List<CalificacionDTO> listaCalificacionByDocumento(CalificacionDTO calificacion) throws Exception {
		return calificacionRepositoryImpl.listaCalificacionByDocumento(calificacion);
	}

}
