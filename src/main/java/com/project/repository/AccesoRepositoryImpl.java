package com.project.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.project.DTO.AccesoDTO;

@Repository
public class AccesoRepositoryImpl extends JdbcDaoSupport {

	@Autowired
	private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public AccesoDTO acceso = new AccesoDTO();

	public AccesoRepositoryImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear el acceso del usuario
	 */
	public AccesoDTO crearAccesoUsuario(AccesoDTO accesoDTO) throws Exception {
		try {
			String SQL = " INSERT INTO public.acceso( " + "	idacceso, documento, username, password, fechasys) "
					+ "	VALUES (nextval('sec_acceso'), ?, ?, ?, CURRENT_TIMESTAMP) ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setLong(1, accesoDTO.getDocumento().getDocumento());
					ps.setString(2, accesoDTO.getUsername());
					ps.setString(3, passwordEncoder.encode(accesoDTO.getPassword()));
				}
			};
			getJdbcTemplate().update(SQL, setter);
			return accesoDTO;
		} catch (Exception e) {
			System.err.println("Exception AccesoRepositoryImpl crearAccesoUsuario: " + e.toString());
			e.printStackTrace();
			throw new Exception("Error al crear el acceso del usuario");
		}
	}

//----------------------------------------------------------------------------------

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear el acceso del usuario
	 */
	public AccesoDTO validaAcceso(AccesoDTO accesoDTO) throws Exception {
		acceso = accesoDTO;
		try {
			String SQL = " SELECT username, password " + "	FROM public.acceso " + "	WHERE username = ? ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, accesoDTO.getUsername());
				}
			};
			return getJdbcTemplate().query(SQL, setter, new validaAccesoResult());
		} catch (Exception e) {
			System.err.println("Exception AccesoRepositoryImpl validaAcceso: " + e.toString());
			throw new Exception("Usuario y/o Contraseña Invalida");
		}
	}

	private class validaAccesoResult implements ResultSetExtractor<AccesoDTO> {
		@Override
		public AccesoDTO extractData(ResultSet rs) throws SQLException, DataAccessException {
			AccesoDTO accesoDTO = null;
			while (rs.next()) {
				accesoDTO = new AccesoDTO();
				accesoDTO.setUsername(rs.getString("username"));
				accesoDTO.setPassword(rs.getString("password"));
				try {
					if (!passwordEncoder.matches(acceso.getPassword(), accesoDTO.getPassword())) {
						throw new Exception("Usuario y/o Contraseña Invalida...");
					}
				} catch (Exception e) {
					System.err.println("Exception AccesoRepositoryImpl validaAccesoResult: " + e.toString());
				}
			}
			return accesoDTO;
		}
	}

//-------------------------------------------------------------------------------------

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para recuperar el acceso del usuario
	 */
	public AccesoDTO recuperaAcceso(AccesoDTO accesoDTO) throws Exception {
		try {
			String SQL = " SELECT password  " + "	FROM public.acceso  " + "	WHERE username = ? ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, accesoDTO.getUsername());
				}
			};
			return getJdbcTemplate().query(SQL, setter, new recuperaAccesoResult());
		} catch (Exception e) {
			System.err.println("Exception AccesoRepositoryImpl recuperaAcceso: " + e.toString());
			throw new Exception("Error al recuperar el acceso del usuario");
		}
	}

	private class recuperaAccesoResult implements ResultSetExtractor<AccesoDTO> {
		@Override
		public AccesoDTO extractData(ResultSet rs) throws SQLException, DataAccessException {
			AccesoDTO accesoDTO = null;
			while (rs.next()) {
				accesoDTO = new AccesoDTO();
				accesoDTO.setPassword(rs.getString(1));

			}
			return accesoDTO;
		}
	}

//---------------------------------------------------------------------------------------

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar el acceso del usuario por documento y
	 *              usuario
	 */
	public Integer modificaAcceso(AccesoDTO accesoDTO) throws Exception {
		try {
			String SQL = " UPDATE public.acceso   " + "     SET password = ?   " + " WHERE username = ?   "
					+ " AND documento = ? ";
			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, passwordEncoder.encode(accesoDTO.getPassword()));
					ps.setString(2, accesoDTO.getUsername());
					ps.setLong(3, accesoDTO.getDocumento().getDocumento());
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception AccesoRepositoryImpl modificaAcceso: " + e.toString());
			throw new Exception("Error al modificar el acceso del usuario");
		}
	}

}
