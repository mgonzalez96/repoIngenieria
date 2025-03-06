package com.project.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

import com.project.dto.Persona;

import javax.sql.DataSource;

@Repository
public class PersonaRepositoryImpl extends JdbcDaoSupport {

	public PersonaRepositoryImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear Personas
	 */
	public Integer crearPersona(Persona persona) throws Exception {
		try {
			String SQL = " INSERT INTO public.persona( "
					+ "	idpersona, nombre1, nombre2, apellido1, apellido2, direccion, movil, correo_electronico, idperfil, usuario, contrasena, estado) "
					+ "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, persona.getIdpersona());
					ps.setString(2, persona.getNombre1());
					ps.setString(3, persona.getNombre2());
					ps.setString(4, persona.getApellido1());
					ps.setString(5, persona.getApellido2());
					ps.setString(6, persona.getDireccion());
					ps.setString(7, persona.getMovil());
					ps.setString(8, persona.getCorreo_electronico());
					ps.setInt(9, persona.getIdperfil());
					ps.setString(10, persona.getUsuario());
					ps.setString(11, persona.getContrasena());
					ps.setInt(12, persona.getEstado());
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception PersonaRepositoryImpl crearPersona: " + e.toString());
			throw new Exception("Persona ya existe");
		}
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar personas por numero de documento
	 */
	public Integer modificarPersona(Persona persona) throws Exception {
		try {
			String SQL = " UPDATE persona " + " SET nombre1 = ?," + "     nombre2 = ?," + "     apellido1 = ?,"
					+ "     apellido2 = ?," + "     direccion = ?," + "     movil = ?," + "     correo_electronico = ?,"
					+ " idperfil = ?, usuario = ?, contrasena = ?" + " WHERE idpersona = ? ";
			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, persona.getNombre1());
					ps.setString(2, persona.getNombre2());
					ps.setString(3, persona.getApellido1());
					ps.setString(4, persona.getApellido2());
					ps.setString(5, persona.getDireccion());
					ps.setString(6, persona.getMovil());
					ps.setString(7, persona.getCorreo_electronico());
					ps.setInt(8, persona.getIdperfil());
					ps.setString(9, persona.getUsuario());
					ps.setString(10, persona.getContrasena());
					ps.setString(11, persona.getIdpersona());
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception PersonaRepositoryImpl modificarPersona: " + e.toString());
			throw new Exception("Error al modificar la persona");
		}
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para consultar personas por numero de documento
	 */
	public Persona consultaPersonaByDocumento(Persona persona) throws Exception {
		try {
			String SQL = " SELECT * FROM persona WHERE idpersona = ? ";
			return getJdbcTemplate().queryForObject(SQL, consultaPersonaByDocumentoRowMapper, persona.getIdpersona());
		} catch (Exception e) {
			System.err.println("Exception PersonaRepositoryImpl consultaPersonaByDocumento: " + e.toString());
			throw new Exception("Persona no existe, valide el documento ingresado");
		}
	}

	private RowMapper<Persona> consultaPersonaByDocumentoRowMapper = new RowMapper<Persona>() {
		@Override
		public Persona mapRow(ResultSet rs, int rowNum) throws SQLException {
			Persona persona = null;
			try {
				persona = new Persona();
				persona.setIdpersona(rs.getString("idpersona"));
				persona.setNombre1(rs.getString("nombre1"));
				persona.setNombre2(rs.getString("nombre2"));
				persona.setApellido1(rs.getString("apellido1"));
				persona.setApellido2(rs.getString("apellido2"));
				persona.setDireccion(rs.getString("direccion"));
				persona.setMovil(rs.getString("movil"));
				persona.setCorreo_electronico(rs.getString("correo_electronico"));
				persona.setIdperfil(rs.getInt("idperfil"));
				persona.setUsuario(rs.getString("usuario"));
				persona.setContrasena(rs.getString("contrasena"));
				persona.setEstado(rs.getInt("estado"));
			} catch (Exception e) {
				System.err.println("Exception PersonaRepositoryImpl consultaPersonaByDocumento_1: " + e.toString());
			}
			return persona;
		}
	};

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para consultar todos las personas
	 */
	public List<Persona> consultaAllPersona() throws Exception {
		try {
			String SQL = " SELECT * FROM persona ";
			return getJdbcTemplate().query(SQL, consultaAllPersonaRowMapper);
		} catch (Exception e) {
			System.err.println("Exception PersonaRepositoryImpl consultaAllPersona: " + e.toString());
			throw new Exception("No existen personas activas");
		}
	}

	private RowMapper<Persona> consultaAllPersonaRowMapper = new RowMapper<Persona>() {
		@Override
		public Persona mapRow(ResultSet rs, int rowNum) throws SQLException {
			Persona persona = null;
			try {
				persona = new Persona();
				persona.setIdpersona(rs.getString("idpersona"));
				persona.setNombre1(rs.getString("nombre1"));
				persona.setNombre2(rs.getString("nombre2"));
				persona.setApellido1(rs.getString("apellido1"));
				persona.setApellido2(rs.getString("apellido2"));
				persona.setDireccion(rs.getString("direccion"));
				persona.setMovil(rs.getString("movil"));
				persona.setCorreo_electronico(rs.getString("correo_electronico"));
				persona.setIdperfil(rs.getInt("idperfil"));
				persona.setUsuario(rs.getString("usuario"));
				persona.setContrasena(rs.getString("contrasena"));
				persona.setEstado(rs.getInt("estado"));
			} catch (Exception e) {
				System.err.println("Exception PersonaRepositoryImpl consultaAllPersona_1: " + e.toString());
			}
			return persona;
		}
	};

}
