package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.DTO.UsuarioDTO;
import com.project.repository.UsuarioRepositoryImpl;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioRepositoryImpl usuarioRepositoryImpl;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear usuarios
	 */
	@Override
	public Integer crearUsuario(UsuarioDTO usuarioDTO) throws Exception {
		return usuarioRepositoryImpl.crearUsuario(usuarioDTO);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar usuarios por numero de documento
	 */
	@Override
	public Integer modificarUsuario(UsuarioDTO usuarioDTO) throws Exception {
		return usuarioRepositoryImpl.modificarUsuario(usuarioDTO);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para consultar usuarios por numero de documento
	 */
	@Override
	public UsuarioDTO consultaUsuarioByDocumento(UsuarioDTO usuarioDTO) throws Exception {
		return usuarioRepositoryImpl.consultaUsuarioByDocumento(usuarioDTO);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para consultar todos los usuarios
	 */
	@Override
	public List<UsuarioDTO> consultaAllUsuario() throws Exception {
		return usuarioRepositoryImpl.consultaAllUsuario();
	}

}
