package com.project.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.project.dto.CalificacionDTO;

@Repository
public class CalificacionRepositoryImpl extends JdbcDaoSupport {

	public CalificacionRepositoryImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear la calificacion
	 */
	public Integer crearCalificacion(CalificacionDTO calificacionDTO) throws Exception {
		try {
			String SQL = " INSERT INTO public.calificacion(calicodi, califech, caliuser, tipocodi, caliobse)"
					+ "	VALUES (nextval('sec_calificacion'), CURRENT_TIMESTAMP, ?, ?, ?) ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setLong(1, calificacionDTO.getCaliuser().getDocumento());
					ps.setInt(2, calificacionDTO.getTipocodi().getTipocodi());
					ps.setString(3, calificacionDTO.getCaliobse());
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception CalificacionRepositoryImpl crearCalificacion: " + e.toString());
			e.printStackTrace();
			throw new Exception("Calificacion ya existe");
		}
	}

}
