package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.project.dto.AccesoDTO;
import com.project.dto.UsuarioDTO;
import com.project.repository.AccesoRepositoryImpl;

@Service
public class AccesoServiceImpl implements AccesoService {

	@Autowired
	AccesoRepositoryImpl accesoRepositoryImpl;

	@Autowired
	private JavaMailSender javaMailSender;

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear el acceso del usuario
	 */
	@Override
	public Integer crearAccesoUsuario(AccesoDTO accesoDTO) throws Exception {
		return accesoRepositoryImpl.crearAccesoUsuario(accesoDTO);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear el acceso del usuario
	 */
	@Override
	public AccesoDTO validaAcceso(AccesoDTO accesoDTO) throws Exception {
		return accesoRepositoryImpl.validaAcceso(accesoDTO);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para recuperar el acceso del usuario
	 */
	@Override
	public AccesoDTO recuperaAcceso(AccesoDTO accesoDTO) throws Exception {
		return accesoRepositoryImpl.recuperaAcceso(accesoDTO);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar el acceso del usuario por documento y
	 *              usuario
	 */
	@Override
	public Integer modificaAcceso(AccesoDTO accesoDTO) throws Exception {
		return accesoRepositoryImpl.modificaAcceso(accesoDTO);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para inactivar el acceso del usuario por documento y
	 *              usuario
	 */
	@Override
	public Integer eliminarAcceso(AccesoDTO accesoDTO) throws Exception {
		return accesoRepositoryImpl.eliminarAcceso(accesoDTO);
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

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para consultar los datos del acceso del usuario por
	 *              documento
	 */
	@Override
	public AccesoDTO datosAcceso(UsuarioDTO usuario) throws Exception {
		return accesoRepositoryImpl.datosAcceso(usuario);
	}
	
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para activar usuarios por numero de documento
	 */
	@Override
	public Integer activarUsuario(UsuarioDTO usuarioDTO) throws Exception {
		return accesoRepositoryImpl.activarUsuario(usuarioDTO);
	}

}
