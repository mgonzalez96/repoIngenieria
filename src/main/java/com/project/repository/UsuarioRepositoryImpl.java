package com.project.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import com.project.DTO.UsuarioDTO;

@Repository
public class UsuarioRepositoryImpl extends JdbcDaoSupport {

	public UsuarioRepositoryImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear usuarios
	 */
	public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) throws Exception {
		try {
			String SQL = " INSERT INTO public.usuario( "
					+ "	documento, nombreuno, nombredos, apellidouno, apellidodos, email, fechanac, celular, fechasys) "
					+ "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP) ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setLong(1, usuarioDTO.getDocumento());
					ps.setString(2, usuarioDTO.getNombreuno());
					ps.setString(3, usuarioDTO.getNombredos());
					ps.setString(4, usuarioDTO.getApellidouno());
					ps.setString(5, usuarioDTO.getApellidodos());
					ps.setString(6, usuarioDTO.getEmail());
					ps.setDate(7, new java.sql.Date(usuarioDTO.getFechanac().getTime()));
					ps.setLong(8, usuarioDTO.getCelular());
				}
			};
			getJdbcTemplate().update(SQL, setter);
			return usuarioDTO;
		} catch (Exception e) {
			System.err.println("Exception UsuarioRepositoryImpl crearUsuario: " + e.toString());
			e.printStackTrace();
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
			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, usuarioDTO.getNombreuno());
					ps.setString(2, usuarioDTO.getNombredos());
					ps.setString(3, usuarioDTO.getApellidouno());
					ps.setString(4, usuarioDTO.getApellidodos());
					ps.setString(5, usuarioDTO.getEmail());
					ps.setDate(6, new java.sql.Date(usuarioDTO.getFechanac().getTime()));
					ps.setLong(7, usuarioDTO.getCelular());
					ps.setLong(8, usuarioDTO.getDocumento());
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception UsuarioRepositoryImpl modificarUsuario: " + e.toString());
			throw new Exception("Error al modificar el usuario");
		}
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para eliminar usuarios por numero de documento
	 */
	public Integer eliminarUsuario(Long documento) throws Exception {
		try {
			String SQL = " DELETE FROM usuario WHERE documento = ? ";
			return getJdbcTemplate().update(SQL, documento);
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
			return getJdbcTemplate().queryForObject(SQL, consultaUsuarioByDocumentoRowMapper,
					usuarioDTO.getDocumento());
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
				usuario.setDocumento(rs.getLong("documento"));
				usuario.setNombreuno(rs.getString("nombreUno"));
				usuario.setNombredos(rs.getString("nombreDos"));
				usuario.setApellidouno(rs.getString("apellidoUno"));
				usuario.setApellidodos(rs.getString("apellidoDos"));
				usuario.setEmail(rs.getString("email"));
				usuario.setFechanac(rs.getDate("fechaNac"));
				usuario.setCelular(rs.getLong("celular"));
				usuario.setFechasys(rs.getDate("fechaSys"));
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
			return getJdbcTemplate().query(SQL, consultaAllUsuarioRowMapper);
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
				usuario.setDocumento(rs.getLong("documento"));
				usuario.setNombreuno(rs.getString("nombreUno"));
				usuario.setNombredos(rs.getString("nombreDos"));
				usuario.setApellidouno(rs.getString("apellidoUno"));
				usuario.setApellidodos(rs.getString("apellidoDos"));
				usuario.setEmail(rs.getString("email"));
				usuario.setFechanac(rs.getDate("fechaNac"));
				usuario.setCelular(rs.getLong("celular"));
				usuario.setFechasys(rs.getDate("fechaSys"));
			} catch (Exception e) {
				System.err.println("Exception UsuarioRepositoryImpl consultaAllUsuario_1: " + e.toString());
			}
			return usuario;
		}
	};

}
