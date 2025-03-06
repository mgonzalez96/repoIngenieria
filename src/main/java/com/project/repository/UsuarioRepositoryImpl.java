package com.project.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import com.project.dto.UsuarioDTO;

@Repository
public class UsuarioRepositoryImpl extends JdbcDaoSupport {

	public UsuarioDTO usuario = new UsuarioDTO();

	public UsuarioRepositoryImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear usuarios
	 */
	public Integer crearUsuario(UsuarioDTO usuarioDTO) throws Exception {
		try {
			String SQL = " INSERT INTO public.usuario( "
					+ "	 documento, nombreuno, nombredos, apellidouno, apellidodos, correo, "
					+ "  fechanac, celular, usuario, contrasena, estado, idperfil) "
					+ "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1, (select a.idperfil   "
					+ "					                          from public.perfil a    "
					+ "					                          where a.nombreperfil = 'USER')) ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setLong(1, usuarioDTO.getDocumento());
					ps.setString(2, usuarioDTO.getNombreuno());
					ps.setString(3, usuarioDTO.getNombredos());
					ps.setString(4, usuarioDTO.getApellidouno());
					ps.setString(5, usuarioDTO.getApellidodos());
					ps.setString(6, usuarioDTO.getCorreo());
					ps.setDate(7, new java.sql.Date(usuarioDTO.getFechanac().getTime()));
					ps.setLong(8, usuarioDTO.getCelular());
					ps.setString(9, usuarioDTO.getUsuario());
					ps.setString(10, usuarioDTO.getContrasena());
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception UsuarioRepositoryImpl crearUsuario: " + e.toString());
			throw new Exception("Usuario ya existe");
		}
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar usuarios por numero de documento
	 */
	public Integer modificarUsuario(UsuarioDTO usuarioDTO) throws Exception {
		try {
			String SQL = " UPDATE public.usuario " + " SET nombreuno = ?," + "     nombredos = ?," + "     apellidouno = ?,"
					+ "     apellidodos = ?," + "     correo = ?," + "     fechanac = ?," + "     celular = ?, "
					+ "     usuario = ?," + "     contrasena = ?, " + "     estado = ? " + " WHERE documento = ? ";
			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, usuarioDTO.getNombreuno());
					ps.setString(2, usuarioDTO.getNombredos());
					ps.setString(3, usuarioDTO.getApellidouno());
					ps.setString(4, usuarioDTO.getApellidodos());
					ps.setString(5, usuarioDTO.getCorreo());
					ps.setDate(6, new java.sql.Date(usuarioDTO.getFechanac().getTime()));
					ps.setLong(7, usuarioDTO.getCelular());
					ps.setString(8, usuarioDTO.getUsuario());
					ps.setString(9, usuarioDTO.getContrasena());
					ps.setInt(10, usuarioDTO.getEstado());
					ps.setLong(11, usuarioDTO.getDocumento());
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception UsuarioRepositoryImpl modificarUsuario: " + e.toString());
			e.printStackTrace();
			throw new Exception("Error al modificar el usuario");
		}
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar el perfil del usuario
	 */
	public Integer modificarPerfil(UsuarioDTO usuarioDTO) throws Exception {
		try {
			String SQL = " UPDATE usuario SET idperfil = ? WHERE documento = ? ";
			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setLong(1, usuarioDTO.getIdperfil().getIdperfil());
					ps.setLong(2, usuarioDTO.getDocumento());
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception UsuarioRepositoryImpl modificarPerfil: " + e.toString());
			throw new Exception("Error al modificar el perfil del usuario");
		}
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para consultar usuarios por numero de documento
	 */
	public UsuarioDTO consultaUsuarioByDocumento(UsuarioDTO usuarioDTO) throws Exception {
		try {
			String SQL = " SELECT u.documento, u.nombreuno, u.nombredos, u.apellidouno, u.apellidodos,  "
					+ "       u.correo, u.fechanac, u.celular, u.usuario, u.contrasena,  "
					+ "	   u.estado, a.idperfil, a.nombreperfil, a.estado " + " FROM public.usuario u, public.perfil a "
					+ " WHERE a.idperfil = u.idperfil " + " AND u.documento = ? ";
			
			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setLong(1, usuarioDTO.getDocumento());
				}
			};
			return getJdbcTemplate().query(SQL, setter, new consultaUsuarioByDocumentoRowMapper());
		} catch (Exception e) {
			System.err.println("Exception UsuarioRepositoryImpl consultaUsuarioByDocumento: " + e.toString());
			throw new Exception("Usuario no existe, valide el documento ingresado");
		}
	}
	
	private class consultaUsuarioByDocumentoRowMapper implements ResultSetExtractor<UsuarioDTO> {
		@Override
		public UsuarioDTO extractData(ResultSet rs) throws SQLException, DataAccessException {
			UsuarioDTO usuario = null;
			while (rs.next()) {
				usuario = new UsuarioDTO();
				usuario.setDocumento(rs.getLong("documento"));
				usuario.setNombreuno(rs.getString("nombreuno"));
				usuario.setNombredos(rs.getString("nombredos"));
				usuario.setApellidouno(rs.getString("apellidouno"));
				usuario.setApellidodos(rs.getString("apellidodos"));
				usuario.setCorreo(rs.getString("correo"));
				usuario.setFechanac(rs.getDate("fechanac"));
				usuario.setCelular(rs.getLong("celular"));
				usuario.setUsuario(rs.getString("usuario"));
				usuario.setContrasena(rs.getString("contrasena"));
				usuario.setEstado(rs.getInt("estado"));
				usuario.getIdperfil().setIdperfil(rs.getInt("idperfil"));
				usuario.getIdperfil().setNombreperfil(rs.getString("nombreperfil"));
				usuario.getIdperfil().setEstado(rs.getInt("estado"));
				if (usuario.getIdperfil().getEstado() == 1) {
					usuario.getIdperfil().setStrEstado("Activo");
				} else {
					usuario.getIdperfil().setStrEstado("Inactivo");
				}
			}
			return usuario;
		}
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para consultar todos los usuarios
	 */
	public List<UsuarioDTO> consultaAllUsuario() throws Exception {
		try {
			String SQL = " SELECT u.documento, u.nombreuno, u.nombredos, u.apellidouno, u.apellidodos,   "
					+ "       u.correo, u.fechanac, u.celular, u.usuario, u.contrasena,   "
					+ "	   u.estado, a.idperfil, a.nombreperfil, a.estado  "
					+ "FROM public.usuario u, public.perfil a  " + "WHERE a.idperfil = u.idperfil ";
			return getJdbcTemplate().query(SQL, consultaAllUsuarioRowMapper);
		} catch (Exception e) {
			System.err.println("Exception UsuarioRepositoryImpl consultaAllUsuario: " + e.toString());
			throw new Exception("No existen usuarios activos");
		}
	}

	private RowMapper<UsuarioDTO> consultaAllUsuarioRowMapper = new RowMapper<UsuarioDTO>() {
		@Override
		public UsuarioDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			UsuarioDTO usuario = null;
			try {
				usuario = new UsuarioDTO();
				usuario.setDocumento(rs.getLong("documento"));
				usuario.setNombreuno(rs.getString("nombreuno"));
				usuario.setNombredos(rs.getString("nombredos"));
				usuario.setApellidouno(rs.getString("apellidouno"));
				usuario.setApellidodos(rs.getString("apellidodos"));
				usuario.setCorreo(rs.getString("correo"));
				usuario.setFechanac(rs.getDate("fechanac"));
				usuario.setCelular(rs.getLong("celular"));
				usuario.setUsuario(rs.getString("usuario"));
				usuario.setContrasena(rs.getString("contrasena"));
				usuario.setEstado(rs.getInt("estado"));
				usuario.getIdperfil().setIdperfil(rs.getInt("idperfil"));
				usuario.getIdperfil().setNombreperfil(rs.getString("nombreperfil"));
				usuario.getIdperfil().setEstado(rs.getInt("estado"));
				if (usuario.getIdperfil().getEstado() == 1) {
					usuario.getIdperfil().setStrEstado("Activo");
				} else {
					usuario.getIdperfil().setStrEstado("Inactivo");
				}
			} catch (Exception e) {
				System.err.println("Exception UsuarioRepositoryImpl consultaAllUsuario_1: " + e.toString());
			}
			return usuario;
		}
	};

	// ----------------------------------------------------------------------------------

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear el acceso del usuario
	 */
	public UsuarioDTO validaAcceso(UsuarioDTO user) throws Exception {
		usuario = user;
		try {
			String SQL = " select u.documento, u.usuario, u.contrasena, a.idperfil, a.nombreperfil    "
					+ " from public.usuario u, public.perfil a    " + " WHERE a.idperfil = u.idperfil    "
					+ " AND u.usuario = ? ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, user.getUsuario());
				}
			};
			return getJdbcTemplate().query(SQL, setter, new validaAccesoResult());
		} catch (Exception e) {
			System.err.println("Exception UsuarioRepositoryImpl validaAcceso: " + e.toString());
			throw new Exception("Usuario y/o Contraseña Invalida");
		}
	}

	private class validaAccesoResult implements ResultSetExtractor<UsuarioDTO> {
		@Override
		public UsuarioDTO extractData(ResultSet rs) throws SQLException, DataAccessException {
			UsuarioDTO user = null;
			while (rs.next()) {
				user = new UsuarioDTO();
				user.setDocumento(rs.getLong("documento"));
				user.setUsuario(rs.getString("usuario"));
				user.setContrasena(rs.getString("contrasena"));
				user.getIdperfil().setIdperfil(rs.getInt("idperfil"));
				user.getIdperfil().setNombreperfil(rs.getString("nombreperfil"));
				try {
					if (user.getUsuario().equals(usuario.getUsuario())
							&& user.getContrasena().equals(usuario.getContrasena())) {
					} else {
						user = null;
					}
				} catch (Exception e) {
					System.err.println("Exception UsuarioRepositoryImpl validaAccesoResult: " + e.toString());
				}
			}
			return user;
		}
	}
// -------------------------------------------------------------------------------------

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para recuperar el acceso del usuario
	 */
	public UsuarioDTO recuperaAcceso(UsuarioDTO usuarioDTO) throws Exception {
		try {
			String SQL = " SELECT usuario, contrasena, correo  " + " FROM public.usuario  " + " WHERE usuario = ? ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, usuarioDTO.getUsuario());
				}
			};
			return getJdbcTemplate().query(SQL, setter, new recuperaAccesoResult());
		} catch (Exception e) {
			System.err.println("Exception UsuarioRepositoryImpl recuperaAcceso: " + e.toString());
			throw new Exception("No existe usuario con el username ingresado");
		}
	}

	private class recuperaAccesoResult implements ResultSetExtractor<UsuarioDTO> {
		@Override
		public UsuarioDTO extractData(ResultSet rs) throws SQLException, DataAccessException {
			UsuarioDTO usuarioDTO = null;
			while (rs.next()) {
				usuarioDTO = new UsuarioDTO();
				usuarioDTO.setUsuario(rs.getString("usuario"));
				usuarioDTO.setContrasena(rs.getString("contrasena"));
				usuarioDTO.setCorreo(rs.getString("correo"));
			}
			return usuarioDTO;
		}
	}

// ---------------------------------------------------------------------------------------

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para inactivar el usuario por documento 
	 */
	public Integer eliminarUsuario(UsuarioDTO usuario) throws Exception {
		try {
			String SQL = " UPDATE public.usuario SET estado = 0  WHERE documento = ?   ";
			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setLong(1, usuario.getDocumento());
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception UsuarioRepositoryImpl eliminarUsuario: " + e.toString());
			throw new Exception("Error al eliminar el eliminar del usuario");
		}
	}

// -----------------------------------------------------------------------
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para activar usuarios por numero de documento
	 */
	public Integer activarUsuario(UsuarioDTO usuarioDTO) throws Exception {
		try {
			String SQL = " UPDATE public.usuario SET estado = 1 WHERE documento = ? ";
			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setLong(1, usuarioDTO.getDocumento());
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception UsuarioRepositoryImpl activarUsuario: " + e.toString());
			throw new Exception("Usuario no existe para activarlo");
		}
	}

// ---------------------------------------------------------------------------------------

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar el acceso del usuario por documento y
	 *              usuario
	 */
	public Integer modificaAcceso(UsuarioDTO usuario) throws Exception {
		try {
			String SQL = " UPDATE public.usuario SET contrasena = ? WHERE usuario = ?   " + " AND documento = ? ";
			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, usuario.getContrasena());
					ps.setString(2, usuario.getUsuario());
					ps.setLong(3, usuario.getDocumento());
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception UsuarioRepositoryImpl modificaAcceso: " + e.toString());
			throw new Exception("Error al modificar el acceso del usuario");
		}
	}

}
