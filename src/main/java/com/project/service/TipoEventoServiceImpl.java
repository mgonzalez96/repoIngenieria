package com.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dto.TipoEventoDTO;
import com.project.repository.TipoEventoRepositoryImpl;

@Service
public class TipoEventoServiceImpl implements TipoEventoService {

	@Autowired
	TipoEventoRepositoryImpl tipoEventoRepositoryImpl;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar todos los tipos de eventos creados
	 */
	@Override
	public List<TipoEventoDTO> consultaAllTipoEvento() throws Exception {
		return tipoEventoRepositoryImpl.consultaAllTipoEvento();
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear el tipo de evento
	 */
	@Override
	public Integer crearTipoEvento(TipoEventoDTO tipoEventoDTO) throws Exception {
		return tipoEventoRepositoryImpl.crearTipoEvento(tipoEventoDTO);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar el tipo de evento
	 */
	@Override
	public Integer modificarTipoEvento(TipoEventoDTO tipoEventoDTO) throws Exception {
		return tipoEventoRepositoryImpl.modificarTipoEvento(tipoEventoDTO);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para eliminar un evento por codigo
	 */
	@Override
	public Integer eliminarTipoEvento(Integer evencodi) throws Exception {
		return tipoEventoRepositoryImpl.eliminarTipoEvento(evencodi);
	}

}
