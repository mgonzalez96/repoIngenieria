package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dto.SitioTuristicoDTO;
import com.project.repository.SitioTuristicoRepositoryImpl;

@Service
public class SitioTuristicoServiceImpl implements SitioTuristicoService {

	@Autowired
	SitioTuristicoRepositoryImpl sitioTuristicoRepositoryImpl;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar todos los sitios turisticos
	 */
	@Override
	public List<SitioTuristicoDTO> consultaAllSitioTuristico(SitioTuristicoDTO sitioTuristicoDTO) throws Exception {
		return sitioTuristicoRepositoryImpl.consultaAllSitioTuristico(sitioTuristicoDTO);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear los sitios turisticos unidos a una ubicacion
	 */
	@Override
	public Integer crearSitioTuristico(SitioTuristicoDTO sitioTuristicoDTO) throws Exception {
		return sitioTuristicoRepositoryImpl.crearSitioTuristico(sitioTuristicoDTO);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar los sitios turisticos unidos a una
	 *              ubicacion
	 */
	@Override
	public Integer modificarSitioTuristico(SitioTuristicoDTO sitioTuristicoDTO) throws Exception {
		return sitioTuristicoRepositoryImpl.modificarSitioTuristico(sitioTuristicoDTO);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para cambiar el estado de los sitios turisticos
	 */
	@Override
	public Integer cambiarEstadoSitioTuristico(SitioTuristicoDTO sitioTuristicoDTO) throws Exception {
		return sitioTuristicoRepositoryImpl.cambiarEstadoSitioTuristico(sitioTuristicoDTO);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para poner like al sitio turistico
	 */
	@Override
	public Integer likeSitioTuristico(SitioTuristicoDTO sitioTuristicoDTO) throws Exception {
		return sitioTuristicoRepositoryImpl.likeSitioTuristico(sitioTuristicoDTO);
	}

}
