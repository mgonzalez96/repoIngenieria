package com.project.usuario;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.project.DTO.UsuarioDTO;
import com.project.repository.UsuarioRepositoryImpl;
import com.project.service.UsuarioService;

@TestConfiguration
public class TestConfig {
	
	@Autowired
	UsuarioRepositoryImpl usuarioRepositoryImpl;

    @Bean
    public UsuarioService usuarioService() {
        return new UsuarioService() {
			
			@Override
			public Integer modificarUsuario(UsuarioDTO usuarioDTO) throws Exception {
				return usuarioRepositoryImpl.modificarUsuario(usuarioDTO);
			}
			
			@Override
			public Integer eliminarUsuario(Integer documento) throws Exception {
				return usuarioRepositoryImpl.eliminarUsuario(documento);
			}
			
			@Override
			public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) throws Exception {
				return usuarioRepositoryImpl.crearUsuario(usuarioDTO);
			}
			
			@Override
			public UsuarioDTO consultaUsuarioByDocumento(UsuarioDTO usuarioDTO) throws Exception {
				return usuarioRepositoryImpl.consultaUsuarioByDocumento(usuarioDTO);
			}
			
			@Override
			public List<UsuarioDTO> consultaAllUsuario() throws Exception {
				return usuarioRepositoryImpl.consultaAllUsuario();
			}
		};
    }
    
}
