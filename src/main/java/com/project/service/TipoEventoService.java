package com.project.service;

import java.util.List;

import com.project.dto.TipoEventoDTO;

public interface TipoEventoService {

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar todos los tipos de eventos creados
	 */
	public List<TipoEventoDTO> consultaAllTipoEvento() throws Exception;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear el tipo de evento
	 */
	public Integer crearTipoEvento(TipoEventoDTO tipoEventoDTO) throws Exception;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar el tipo de evento
	 */
	public Integer modificarTipoEvento(TipoEventoDTO tipoEventoDTO) throws Exception;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para eliminar un evento por codigo
	 */
	public Integer eliminarTipoEvento(Integer evencodi) throws Exception;

}
