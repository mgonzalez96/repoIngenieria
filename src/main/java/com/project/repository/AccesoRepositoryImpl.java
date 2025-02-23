package com.project.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.project.DTO.AccesoDTO;
import com.project.DTO.UsuarioDTO;

@Repository
public class AccesoRepositoryImpl extends JdbcDaoSupport {

	// TODO Esta instancia se comentarea, en la proxima entrega implementar el
	// cifrado de la contraseña
	/*
	 * @Autowired private static final BCryptPasswordEncoder passwordEncoder = new
	 * BCryptPasswordEncoder();
	 */

	public AccesoDTO acceso = new AccesoDTO();

	public AccesoRepositoryImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear el acceso del usuario
	 */
	public Integer crearAccesoUsuario(AccesoDTO accesoDTO) throws Exception {
		try {
			String SQL = " INSERT INTO public.acceso( "
					+ "	idacceso, documento, username, password, fechasys, estado, perfil) "
					+ "	VALUES (nextval('sec_acceso'), ?, ?, ?, CURRENT_TIMESTAMP, 1, ?) ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setLong(1, accesoDTO.getDocumento().getDocumento());
					ps.setString(2, accesoDTO.getUsername());
					ps.setString(3, accesoDTO.getPassword());
					ps.setString(4, accesoDTO.getPerfil());
					// TODO Cifrado de contraseña para la proxima entrega
					// ps.setString(3, passwordEncoder.encode(accesoDTO.getPassword()));
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception AccesoRepositoryImpl crearAccesoUsuario: " + e.toString());
			throw new Exception("Usuario ya tiene un acceso");
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
			String SQL = " SELECT username, password, perfil " + "	FROM public.acceso " + "	WHERE username = ? ";

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
				accesoDTO.setPerfil(rs.getString("perfil"));
				try {
					// TODO Cifrado de contraseña para la proxima entrega
					/*
					 * if (!passwordEncoder.matches(acceso.getPassword(), accesoDTO.getPassword()))
					 * { throw new Exception("Usuario y/o Contraseña Invalida..."); }
					 */
					if (!acceso.getPassword().equals(accesoDTO.getPassword())) {
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
			String SQL = " SELECT a.username, a.password, u.email  " + " FROM public.acceso a, public.usuario u  "
					+ " WHERE u.documento = a.documento " + " AND username = ? ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, accesoDTO.getUsername());
				}
			};
			return getJdbcTemplate().query(SQL, setter, new recuperaAccesoResult());
		} catch (Exception e) {
			System.err.println("Exception AccesoRepositoryImpl recuperaAcceso: " + e.toString());
			throw new Exception("No existe usuario con el username ingresado");
		}
	}

	private class recuperaAccesoResult implements ResultSetExtractor<AccesoDTO> {
		@Override
		public AccesoDTO extractData(ResultSet rs) throws SQLException, DataAccessException {
			AccesoDTO accesoDTO = null;
			while (rs.next()) {
				accesoDTO = new AccesoDTO();
				accesoDTO.setUsername(rs.getString(1));
				accesoDTO.setPassword(rs.getString(2));
				accesoDTO.getDocumento().setEmail(rs.getString(3));
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
					// ps.setString(1, passwordEncoder.encode(accesoDTO.getPassword()));
					ps.setString(1, accesoDTO.getPassword());
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

	// ---------------------------------------------------------------------------------------

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para inactivar el acceso del usuario por documento y
	 *              usuario
	 */
	public Integer eliminarAcceso(AccesoDTO accesoDTO) throws Exception {
		try {
			String SQL = " UPDATE public.acceso   " + "     SET estado = 0   " + " WHERE documento = ?   ";
			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setLong(1, accesoDTO.getDocumento().getDocumento());
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception AccesoRepositoryImpl eliminarAcceso: " + e.toString());
			throw new Exception("Error al eliminar el acceso del usuario");
		}
	}
	// ----------------------------------------------------------------------------------

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para consultar los datos del acceso del usuario por
	 *              documento
	 */
	public AccesoDTO datosAcceso(UsuarioDTO usuario) throws Exception {
		try {
			String SQL = " SELECT a.idacceso, a.documento, a.username, a.perfil, "
					+ "       u.nombreuno, u.nombredos, u.apellidouno, u.apellidodos,"
					+ "       a.estado estadoAcceso "
					+ "	FROM public.acceso a, public.usuario u " + "	WHERE u.documento = a.documento "
					+ "	AND u.documento = ? ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setLong(1, usuario.getDocumento());
				}
			};
			return getJdbcTemplate().query(SQL, setter, new datosAccesoResult());
		} catch (Exception e) {
			System.err.println("Exception AccesoRepositoryImpl datosAcceso: " + e.toString());
			throw new Exception("Usuario no existe por documento ingresado");
		}
	}

	private class datosAccesoResult implements ResultSetExtractor<AccesoDTO> {
		@Override
		public AccesoDTO extractData(ResultSet rs) throws SQLException, DataAccessException {
			AccesoDTO accesoDTO = null;
			while (rs.next()) {
				accesoDTO = new AccesoDTO();
				accesoDTO.setIdacceso(rs.getLong("idacceso"));
				accesoDTO.getDocumento().setDocumento(rs.getLong("documento"));
				accesoDTO.setUsername(rs.getString("username"));
				accesoDTO.setPerfil(rs.getString("perfil"));
				accesoDTO.getDocumento().setNombreuno(rs.getString("nombreuno"));
				accesoDTO.getDocumento().setNombredos(rs.getString("nombredos"));
				accesoDTO.getDocumento().setApellidouno(rs.getString("apellidouno"));
				accesoDTO.getDocumento().setApellidodos(rs.getString("apellidodos"));
				accesoDTO.setEstado(rs.getInt("estadoAcceso"));
			}
			return accesoDTO;
		}
	}

//-----------------------------------------------------------------------
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para activar usuarios por numero de documento
	 */
	public Integer activarUsuario(UsuarioDTO usuarioDTO) throws Exception {
		try {
			String SQL = " UPDATE public.acceso " + "	SET estado = 1 " + "	WHERE documento = ? ";
			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setLong(1, usuarioDTO.getDocumento());
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception AccesoRepositoryImpl activarUsuario: " + e.toString());
			throw new Exception("Usuario no existe para activarlo");
		}
	}

}
