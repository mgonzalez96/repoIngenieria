package com.project.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.dto.AccesoDTO;
import com.project.dto.UsuarioDTO;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@EnableAutoConfiguration
class UsuarioRepositoryIT {

	@Autowired
	UsuarioRepositoryImpl usuarioRepositoryImpl;

	@Test
	void test() {
		fail("Not yet implemented");
	}

	@Test
	@Order(1)
	void crearUsuario() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setDocumento(1113123451L);
		usuario.setNombreuno("Mariana");
		usuario.setNombredos("");
		usuario.setApellidouno("Acevedo");
		usuario.setApellidodos("Gonzalez");
		usuario.setCorreo("marianaacevedo1996@gmail.com");
		usuario.setFechanac(format.parse("18/07/1996"));
		usuario.setCelular(3151234585L);
		usuario.setUsuario("TengoAfan");
		usuario.setContrasena("1234");
		assertNotNull(usuarioRepositoryImpl.crearUsuario(usuario), "Usuario creado");
	}

	@Test
	@Order(2)
	void modificarUsuario() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setDocumento(1113123451L);
		usuario.setNombreuno("Mariana");
		usuario.setNombredos("Maria");
		usuario.setApellidouno("Gonzalez");
		usuario.setApellidodos("Acevedo");
		usuario.setCorreo("marianaacevedo1996@gmail.com");
		usuario.setFechanac(format.parse("18/08/1996"));
		usuario.setCelular(3151234500L);
		usuario.setUsuario("TengoAfan1");
		usuario.setContrasena("12345");
		usuario.setEstado(1);

		Integer resultado = 0;
		resultado = usuarioRepositoryImpl.modificarUsuario(usuario);

		assertNotNull(resultado, "Usuario modificado");
	}
	
	@Test
	@Order(3)
	void modificarPerfil() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setDocumento(1113123451L);
		usuario.setNombreuno("Mariana");
		usuario.setNombredos("Maria");
		usuario.setApellidouno("Gonzalez");
		usuario.setApellidodos("Acevedo");
		usuario.setCorreo("marianaacevedo1996@gmail.com");
		usuario.setFechanac(format.parse("18/08/1996"));
		usuario.setCelular(3151234500L);
		usuario.setUsuario("TengoAfan1");
		usuario.setContrasena("12345");
		usuario.setEstado(1);

		Integer resultado = 0;
		resultado = usuarioRepositoryImpl.modificarPerfil(usuario);

		assertNotNull(resultado, "Perfil del usuario modificado");
	}

	@Test
	@Order(4)
	void consultaUsuarioByDocumento() throws Exception {
		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setDocumento(1113123457L);

		usuario = usuarioRepositoryImpl.consultaUsuarioByDocumento(usuario);

		assertNotNull(usuario, "Usuario consultado por documento");
	}

	@Test
	@Order(5)
	void consultaAllUsuario() throws Exception {
		List<UsuarioDTO> lista = new ArrayList<>();
		lista = usuarioRepositoryImpl.consultaAllUsuario();
		assertFalse(lista.isEmpty(), "Usuarios consultados");
	}

	@Test
	@Order(6)
	void validaAcceso() throws Exception {
		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setUsuario("1113123457");
		usuario.setContrasena("1234");
		assertNotNull(usuarioRepositoryImpl.validaAcceso(usuario), "Acceso válido");
	}
	
	@Test
	@Order(7)
	void recuperaAcceso() throws Exception {
		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setUsuario("1113123457");
		assertNotNull(usuarioRepositoryImpl.recuperaAcceso(usuario), "Acceso válido");
	}
	

}
