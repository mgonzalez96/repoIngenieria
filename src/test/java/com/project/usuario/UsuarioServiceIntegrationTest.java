package com.project.usuario;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

import com.project.DTO.UsuarioDTO;
import com.project.service.UsuarioService;

/*@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)*/

@SpringBootTest(classes = TestConfig.class)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsuarioServiceIntegrationTest {
	
	@Autowired
	private UsuarioService usuarioService;

	/*@Test
	void test() {
		fail("Not yet implemented");
	}*/

	@Test
	public void testCrearUsuario() throws Exception {
		UsuarioDTO usuario = new UsuarioDTO();
		UsuarioDTO usuarioResult = new UsuarioDTO();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		usuario.setDocumento("1113123456");
		usuario.setNombreUno("Mariana");
		usuario.setNombreDos("");
		usuario.setApellidoUno("Acevedo");
		usuario.setApellidoDos("Gonzalez");
		usuario.setEmail("marianaacevedo1996@gmail.com");
	    usuario.setFechaNac(format.parse("18/07/1996"));
	    usuario.setCelular("3151234585");
	    
	    usuarioResult = usuarioService.crearUsuario(usuario);
	    
	    assertNotNull(usuarioResult.getDocumento());
	    assertNotNull(usuarioResult.getNombreUno());
	    assertNotNull(usuarioResult.getNombreDos());
	    assertNotNull(usuarioResult.getApellidoUno());
	    assertNotNull(usuarioResult.getApellidoDos());
	    assertNotNull(usuarioResult.getEmail());
	    assertNotNull(usuarioResult.getFechaNac());
	    assertNotNull(usuarioResult.getCelular());
	    
	    /*assertEquals("1113123456", usuarioResult.getDocumento());
	    assertEquals("Mariana", usuarioResult.getNombreUno());
	    assertEquals("", usuarioResult.getNombreDos());
	    assertEquals("Acevedo", usuarioResult.getApellidoUno());
	    assertEquals("Gonzalez", usuarioResult.getApellidoDos());
	    assertEquals("marianaacevedo1996", usuarioResult.getEmail());
	    assertEquals("18/07/1996", usuarioResult.getFechaNac());
	    assertEquals("3151234585", usuarioResult.getCelular());*/
	}
	
	
}
