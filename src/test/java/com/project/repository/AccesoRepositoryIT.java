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

import com.project.DTO.AccesoDTO;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@EnableAutoConfiguration
class AccesoRepositoryIT {

	@Autowired
	AccesoRepositoryImpl accesoRepositoryImpl;

	@Test
	void test() {
		fail("Not yet implemented");
	}

	@Test
	@Order(1)
	void crearAccesoUsuario() throws Exception {
		AccesoDTO acceso = new AccesoDTO();
		acceso.getDocumento().setDocumento(1113123457L);
		acceso.setUsername("1113123457");
		acceso.setPassword("1234");

		acceso = accesoRepositoryImpl.crearAccesoUsuario(acceso);

		assertNotNull(acceso, "Acceso del usuario creado");
	}

	@Test
	@Order(2)
	void validaAcceso() throws Exception {
		AccesoDTO acceso = new AccesoDTO();
		acceso.setUsername("1113123457");
		acceso.setPassword("1234");

		assertNotNull(accesoRepositoryImpl.validaAcceso(acceso), "Acceso válido");
	}

	@Test
	@Order(3)
	void recuperaAcceso() throws Exception {
		AccesoDTO acceso = new AccesoDTO();
		acceso.setUsername("1113123457");

		acceso = accesoRepositoryImpl.recuperaAcceso(acceso);

		assertNotNull(acceso, "Acceso recuperado");
	}

	@Test
	@Order(4)
	void modificaAcceso() throws Exception {
		AccesoDTO acceso = new AccesoDTO();
		acceso.setUsername("1113123457");
		acceso.getDocumento().setDocumento(1113123457L);
		acceso.setPassword("1234");

		assertNotNull(accesoRepositoryImpl.modificaAcceso(acceso), "Acceso recuperado");
	}

}
