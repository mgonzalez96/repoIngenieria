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
		usuario.setEmail("marianaacevedo1996@gmail.com");
		usuario.setFechanac(format.parse("18/07/1996"));
		usuario.setCelular(3151234585L);
		usuario.setFechasys(new Date());

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
		usuario.setEmail("correoprueba@gmail.com");
		usuario.setFechanac(format.parse("18/08/1996"));
		usuario.setCelular(3151234500L);

		Integer resultado = 0;
		resultado = usuarioRepositoryImpl.modificarUsuario(usuario);

		assertNotNull(resultado, "Usuario modificado");
	}

	@Test
	@Order(3)
	void consultaUsuarioByDocumento() throws Exception {
		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setDocumento(1113123457L);

		usuario = usuarioRepositoryImpl.consultaUsuarioByDocumento(usuario);

		assertNotNull(usuario, "Usuario consultado por documento");
	}

	@Test
	@Order(4)
	void consultaAllUsuario() throws Exception {
		List<UsuarioDTO> lista = new ArrayList<>();

		lista = usuarioRepositoryImpl.consultaAllUsuario();

		assertFalse(lista.isEmpty(), "Usuarios consultados");
	}

	

}
