package com.project.service;

import java.util.List;

import com.project.dto.CalificacionDTO;
import com.project.dto.GastronomiaDTO;

public interface CalificacionService {

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear la calificacion
	 */
	public Integer crearCalificacion(CalificacionDTO calificacionDTO) throws Exception;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar los platos tipicos de la region
	 */
	public List<CalificacionDTO> consultaCalificaGastronomia() throws Exception;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método consultar la gastronomia por calificacion
	 */
	public List<CalificacionDTO> consGastronomiaByCalificacion(CalificacionDTO calificacion) throws Exception;
	
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Consulta El promedio de calificacion por codigo de gastronomia
	 */
	public CalificacionDTO getPromedioCalificacion(GastronomiaDTO gastcodi) throws Exception;
	
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Lista las calificaciones por usuario
	 */
	public List<CalificacionDTO> listaCalificacionByDocumento(CalificacionDTO calificacion) throws Exception;

}
