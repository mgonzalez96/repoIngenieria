package com.project.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import com.project.dto.AccesoDTO;

@Repository
public class AccesoRepositoryImpl extends JdbcDaoSupport {

	public AccesoRepositoryImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear perfiles
	 */
	public Integer crearPerfil(AccesoDTO accesoDTO) throws Exception {
		try {
			String SQL = " INSERT INTO public.acceso(idacceso, nombreperfil, estado) "
					+ "	VALUES (nextval('sec_acceso'), ?, 1) ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, accesoDTO.getNombreperfil());
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
	public Integer modificarPerfil(AccesoDTO accesoDTO) throws Exception {
		try {
			String SQL = " UPDATE public.acceso " + "	SET nombreperfil=?, estado=? " + "	WHERE idacceso=? ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, accesoDTO.getNombreperfil());
					ps.setInt(2, accesoDTO.getEstado());
					ps.setLong(3, accesoDTO.getIdacceso());

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
	public Integer inactivarPerfil(AccesoDTO accesoDTO) throws Exception {
		try {
			String SQL = " UPDATE public.acceso SET estado=? WHERE idacceso=? ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, accesoDTO.getEstado());
					ps.setLong(2, accesoDTO.getIdacceso());

				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception AccesoRepositoryImpl inactivarPerfil: " + e.toString());
			throw new Exception("Error al inactivar el perfil");
		}
	}

}
