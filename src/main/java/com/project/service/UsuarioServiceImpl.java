package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.project.dto.UsuarioDTO;
import com.project.repository.UsuarioRepositoryImpl;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioRepositoryImpl usuarioRepositoryImpl;

	@Autowired
	private JavaMailSender javaMailSender;

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
	 * @Descripcion Método para modificar el perfil del usuario
	 */
	@Override
	public Integer modificarPerfil(UsuarioDTO usuarioDTO) throws Exception {
		return usuarioRepositoryImpl.modificarPerfil(usuarioDTO);
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

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para validar el acceso del usuario
	 */
	@Override
	public UsuarioDTO validaAcceso(UsuarioDTO usuario) throws Exception {
		usuario = usuarioRepositoryImpl.validaAcceso(usuario);
		if (usuario == null) {
			throw new Exception("Usuario y/o Contraseña Invalidos...");
		} else {
			return usuario;
		}
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para recuperar el acceso del usuario
	 */
	@Override
	public UsuarioDTO recuperaAcceso(UsuarioDTO usuarioDTO) throws Exception {
		return usuarioRepositoryImpl.recuperaAcceso(usuarioDTO);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para inactivar el usuario por documento
	 */
	@Override
	public Integer eliminarUsuario(UsuarioDTO usuarioDTO) throws Exception {
		return usuarioRepositoryImpl.eliminarUsuario(usuarioDTO);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para activar usuarios por numero de documento
	 */
	@Override
	public Integer activarUsuario(UsuarioDTO usuarioDTO) throws Exception {
		return usuarioRepositoryImpl.activarUsuario(usuarioDTO);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar el acceso del usuario por documento
	 */
	@Override
	public Integer modificaAcceso(UsuarioDTO usuarioDTO) throws Exception {
		return usuarioRepositoryImpl.modificaAcceso(usuarioDTO);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para enviar por correo credenciales olvidadas
	 */
	@Override
	public void sendEmail(String para, String asunto, String descripcion) throws Exception {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(para);
			message.setSubject(asunto);
			message.setText(descripcion);
			javaMailSender.send(message);
		} catch (Exception e) {
			System.err.println("Exception AccesoServiceImpl sendEmail: " + e.toString());
			e.printStackTrace();
			throw new Exception("Error al enviar el email");
		}
	}

}
