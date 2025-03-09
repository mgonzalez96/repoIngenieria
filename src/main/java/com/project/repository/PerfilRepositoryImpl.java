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
import com.project.dto.Perfil;

@Repository
public class PerfilRepositoryImpl extends JdbcDaoSupport {

	public PerfilRepositoryImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear perfiles
	 */
	public Integer crearPerfil(Perfil perfil) throws Exception {
		try {
			String SQL = " INSERT INTO public.perfil(idperfil, nombreperfil, estado) "
					+ "	VALUES (nextval('sec_perfil'), ?, 1) ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, perfil.getNombreperfil());
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception PerfilRepositoryImpl crearPerfil: " + e.toString());
			throw new Exception("Error al crear el perfil");
		}
	}
//------------------------------------------------------------------------

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar los perfiles
	 */
	public Integer modificarPerfil(Perfil perfil) throws Exception {
		try {
			String SQL = " UPDATE public.perfil " + "	SET nombreperfil=?, estado=? " + "	WHERE idperfil=? ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, perfil.getNombreperfil());
					ps.setInt(2, perfil.getEstado());
					ps.setLong(3, perfil.getIdperfil());
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception PerfilRepositoryImpl modificarPerfil: " + e.toString());
			throw new Exception("Error al modificar el perfil");
		}
	}

// ------------------------------------------------------------------------

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para cambiar el estado de los perfiles
	 */
	public Integer cambiarEstadoPerfil(Perfil perfil) throws Exception {
		try {
			String SQL = " UPDATE public.perfil SET estado=? WHERE idperfil=? ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, perfil.getEstado());
					ps.setLong(2, perfil.getIdperfil());

				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception PerfilRepositoryImpl cambiarEstadoPerfil: " + e.toString());
			throw new Exception("Error al cambiar el estado del perfil");
		}
	}

// --------------------------------------------------------------------------------------

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar los perfiles por estado
	 */
	public List<Perfil> consultaPerfilByEstado(Perfil perfil) throws Exception {
		try {
			String SQL = " SELECT idperfil, nombreperfil, estado, " + "       case when estado = 1 "
					+ "	    then 'Activo' " + "		else 'Inactivo' " + "		end estadostr " + "FROM public.perfil "
					+ "WHERE estado = ? ";
			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, perfil.getEstado());
				}
			};
			return getJdbcTemplate().query(SQL, setter, consultaPerfilByEstadoRowMapper);
		} catch (Exception e) {
			System.err.println("Exception PerfilRepositoryImpl consultaPerfilByEstado: " + e.toString());
			throw new Exception("No existen perfiles");
		}
	}

	private RowMapper<Perfil> consultaPerfilByEstadoRowMapper = new RowMapper<Perfil>() {
		@Override
		public Perfil mapRow(ResultSet rs, int rowNum) throws SQLException {
			Perfil perfil = null;
			try {
				perfil = new Perfil();
				perfil.setIdperfil(rs.getInt("idperfil"));
				perfil.setNombreperfil(rs.getString("nombreperfil"));
				perfil.setEstado(rs.getInt("estado"));
				perfil.setStrEstado(rs.getString("estadostr"));
			} catch (Exception e) {
				System.err.println("Exception PerfilRepositoryImpl consultaPerfilByEstado_1: " + e.toString());
			}
			return perfil;
		}
	};

}
