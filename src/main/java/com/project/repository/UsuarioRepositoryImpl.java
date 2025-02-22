package com.project.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.project.DTO.UsuarioDTO;

@Repository
public class UsuarioRepositoryImpl {

	private final JdbcTemplate jdbcTemplate;

	public UsuarioRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear usuarios
	 */
	public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) throws Exception {
		try {
			String SQL = " INSERT INTO usuario (documento, nombreUno, nombreDos, apellidoUno, apellidoDos, email, fechaNac, celular, fechaSys) "
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, sysdate) ";
			jdbcTemplate.update(SQL, usuarioDTO.getDocumento(), usuarioDTO.getNombreUno(), usuarioDTO.getNombreDos(),
					usuarioDTO.getApellidoUno(), usuarioDTO.getApellidoDos(), usuarioDTO.getEmail(),
					usuarioDTO.getFechaNac(), usuarioDTO.getCelular());
			return usuarioDTO;
		} catch (Exception e) {
			System.err.println("Exception UsuarioRepositoryImpl crearUsuario: " + e.toString());
			throw new Exception("Error al crear el usuario");
		}

	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar usuarios por numero de documento
	 */
	public Integer modificarUsuario(UsuarioDTO usuarioDTO) throws Exception {
		try {
			String SQL = " UPDATE usuario " + " SET nombreUno = ?," + "     nombreDos = ?," + "     apellidoUno = ?,"
					+ "     apellidoDos = ?," + "     email = ?," + "     fechaNac = ?," + "     celular = ?"
					+ " WHERE documento = ? ";
			return jdbcTemplate.update(SQL, usuarioDTO.getNombreUno(), usuarioDTO.getNombreDos(),
					usuarioDTO.getApellidoUno(), usuarioDTO.getApellidoDos(), usuarioDTO.getEmail(),
					usuarioDTO.getFechaNac(), usuarioDTO.getCelular(), usuarioDTO.getDocumento());
		} catch (Exception e) {
			System.err.println("Exception UsuarioRepositoryImpl modificarUsuario: " + e.toString());
			throw new Exception("Error al modificar el usuario");
		}
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para eliminar usuarios por numero de documento
	 */
	public Integer eliminarUsuario(Integer documento) throws Exception {
		try {
			String SQL = " DELETE FROM usuario WHERE documento = ? ";
			return jdbcTemplate.update(SQL, documento);
		} catch (Exception e) {
			System.err.println("Exception UsuarioRepositoryImpl eliminarUsuario: " + e.toString());
			throw new Exception("Error al eliminar el usuario");
		}
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para consultar usuarios por numero de documento
	 */
	public UsuarioDTO consultaUsuarioByDocumento(UsuarioDTO usuarioDTO) throws Exception {
		try {
			String SQL = " SELECT * FROM usuario WHERE documento = ? ";
			return jdbcTemplate.queryForObject(SQL, consultaUsuarioByDocumentoRowMapper, usuarioDTO.getDocumento());
		} catch (Exception e) {
			System.err.println("Exception UsuarioRepositoryImpl consultaUsuarioByDocumento: " + e.toString());
			throw new Exception("Error al consultar el usuario");
		}
	}

	private RowMapper<UsuarioDTO> consultaUsuarioByDocumentoRowMapper = new RowMapper<UsuarioDTO>() {
		@Override
		public UsuarioDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			UsuarioDTO usuario = null;
			try {
				usuario = new UsuarioDTO();
				usuario.setDocumento(rs.getString("documento"));
				usuario.setNombreUno(rs.getString("nombreUno"));
				usuario.setNombreDos(rs.getString("nombreDos"));
				usuario.setApellidoUno(rs.getString("apellidoUno"));
				usuario.setApellidoDos(rs.getString("apellidoDos"));
				usuario.setEmail(rs.getString("email"));
				usuario.setFechaNac(rs.getDate("fechaNac"));
				usuario.setCelular(rs.getString("celular"));
				usuario.setFechaSys(rs.getDate("fechaSys"));
			} catch (Exception e) {
				System.err.println("Exception UsuarioRepositoryImpl consultaUsuarioByDocumento_1: " + e.toString());
			}
			return usuario;
		}
	};

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para consultar todos los usuarios
	 */
	public List<UsuarioDTO> consultaAllUsuario() throws Exception {
		try {
			String SQL = " SELECT * FROM usuario ";
			return jdbcTemplate.query(SQL, consultaAllUsuarioRowMapper);
		} catch (Exception e) {
			System.err.println("Exception UsuarioRepositoryImpl consultaAllUsuario: " + e.toString());
			throw new Exception("Error al consultar los usuarios");
		}
	}

	private RowMapper<UsuarioDTO> consultaAllUsuarioRowMapper = new RowMapper<UsuarioDTO>() {
		@Override
		public UsuarioDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			UsuarioDTO usuario = null;
			try {
				usuario = new UsuarioDTO();
				usuario.setDocumento(rs.getString("documento"));
				usuario.setNombreUno(rs.getString("nombreUno"));
				usuario.setNombreDos(rs.getString("nombreDos"));
				usuario.setApellidoUno(rs.getString("apellidoUno"));
				usuario.setApellidoDos(rs.getString("apellidoDos"));
				usuario.setEmail(rs.getString("email"));
				usuario.setFechaNac(rs.getDate("fechaNac"));
				usuario.setCelular(rs.getString("celular"));
				usuario.setFechaSys(rs.getDate("fechaSys"));
			} catch (Exception e) {
				System.err.println("Exception UsuarioRepositoryImpl consultaAllUsuario_1: " + e.toString());
			}
			return usuario;
		}
	};

}
