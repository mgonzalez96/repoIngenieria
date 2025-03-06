package com.project.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import com.project.dto.Perfil;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@EnableAutoConfiguration
class PerfilRepositoryIT {

	@Autowired
	PerfilRepositoryImpl accesoRepositoryImpl;

	@Test
	void test() {
		fail("Not yet implemented");
	}

	@Test
	@Order(1)
	void crearPerfil() throws Exception {
		Perfil perfil = new Perfil();
		perfil.setNombreperfil("Editor");
		;
		assertNotNull(accesoRepositoryImpl.crearPerfil(perfil), "Perfil creado");
	}

	@Test
	@Order(2)
	void modificarPerfil() throws Exception {
		Perfil perfil = new Perfil();
		perfil.setNombreperfil("Editor");
		perfil.setEstado(1);
		perfil.setIdperfil(12);
		assertNotNull(accesoRepositoryImpl.modificarPerfil(perfil), "Perfil modificado");
	}

	@Test
	@Order(3)
	void inactivarPerfil() throws Exception {
		Perfil perfil = new Perfil();
		perfil.setEstado(0);
		perfil.setIdperfil(12);
		assertNotNull(accesoRepositoryImpl.inactivarPerfil(perfil), "Perfil inactivo");
	}

}
