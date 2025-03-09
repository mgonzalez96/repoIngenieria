package com.project.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import com.project.dto.UsuarioTurismoDTO;

@Repository
public class UsuarioTurismoRepositoryImpl extends JdbcDaoSupport {

	public UsuarioTurismoRepositoryImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Crea el like del sitio turistico
	 */
	public Integer crearLikeSitioTuristico(UsuarioTurismoDTO turismo) throws Exception {
		try {
			String SQL = " INSERT INTO public.usuarioturismo(ustucodi, documento, turicodi, likes) "
					+ "	VALUES (nextval('sec_user_turismo'), ?, ?, ?) ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setLong(1, turismo.getDocumento());
					ps.setLong(2, turismo.getTuricodi());
					ps.setInt(3, turismo.getLike());
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception UsuarioTurismoRepositoryImpl crearLikeSitioTuristico: " + e.toString());
			return 0;
		}
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Elimina el like del sitio turistico
	 */
	public Integer eliminaLikeSitioTuristico(UsuarioTurismoDTO turismo) throws Exception {
		try {
			String SQL = " delete from usuarioturismo  " + " where documento = ?  " + " and turicodi = ? ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setLong(1, turismo.getDocumento());
					ps.setLong(2, turismo.getTuricodi());
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception UsuarioTurismoRepositoryImpl eliminaLikeSitioTuristico: " + e.toString());
			return 0;
		}
	}
//-------------------------------------------------------------------------------------------------

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Consulta el like del sitio turistico por documento y codigo de
	 *              sitio
	 */
	public Integer getlikeTurismo(UsuarioTurismoDTO turismo) throws Exception {
		try {
			String SQL = " SELECT coalesce(likes, 0) likes   " + " FROM public.usuarioturismo   "
					+ " WHERE documento = ?   " + " AND turicodi = ? ";
			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setLong(1, turismo.getDocumento());
					ps.setLong(2, turismo.getTuricodi());
				}
			};
			return getJdbcTemplate().query(SQL, setter, new getPromedioCalificacionRowMapper());
		} catch (Exception e) {
			System.err.println("Exception UsuarioTurismoRepositoryImpl getlikeTurismo: " + e.toString());
			throw new Exception("Error al consultar el like del sitio");
		}
	}

	private class getPromedioCalificacionRowMapper implements ResultSetExtractor<Integer> {
		@Override
		public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
			Integer like = 0;
			while (rs.next()) {
				like = rs.getInt("likes");
			}
			return like;
		}
	}

}
