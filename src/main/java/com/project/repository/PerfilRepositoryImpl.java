package com.project.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.jdbc.core.PreparedStatementSetter;
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
			System.err.println("Exception AccesoRepositoryImpl crearPerfil: " + e.toString());
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
			System.err.println("Exception AccesoRepositoryImpl modificarPerfil: " + e.toString());
			throw new Exception("Error al modificar el perfil");
		}
	}

// ------------------------------------------------------------------------

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para inactivar los perfiles
	 */
	public Integer inactivarPerfil(Perfil perfil) throws Exception {
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
			System.err.println("Exception AccesoRepositoryImpl inactivarPerfil: " + e.toString());
			throw new Exception("Error al inactivar el perfil");
		}
	}

}
