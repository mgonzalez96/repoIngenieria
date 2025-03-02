package com.project.service;

import java.util.List;

import com.project.dto.SitioTuristicoDTO;

public interface SitioTuristicoService {

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar todos los sitios turisticos
	 */
	public List<SitioTuristicoDTO> consultaAllSitioTuristico(SitioTuristicoDTO sitioTuristicoDTO) throws Exception;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear los sitios turisticos unidos a una ubicacion
	 */
	public Integer crearSitioTuristico(SitioTuristicoDTO sitioTuristicoDTO) throws Exception;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar los sitios turisticos unidos a una
	 *              ubicacion
	 */
	public Integer modificarSitioTuristico(SitioTuristicoDTO sitioTuristicoDTO) throws Exception;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para inactivar los sitios turisticos
	 */
	public Integer inactivarSitioTuristico(SitioTuristicoDTO sitioTuristicoDTO) throws Exception;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para poner like al sitio turistico
	 */
	public Integer likeSitioTuristico(SitioTuristicoDTO sitioTuristicoDTO) throws Exception;

}
