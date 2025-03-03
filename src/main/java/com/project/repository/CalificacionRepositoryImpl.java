package com.project.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
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
	 * @Descripcion Consulta la secuencia para crear la calificacion
	 */
	private Integer getSecuencia() {
		try {
			String SQL = " select nextval('sec_calificacion') ";
			return getJdbcTemplate().queryForObject(SQL, getSecuenciaRowMapper);
		} catch (Exception e) {
			System.err.println("Exception CalificacionRepositoryImpl getSecuencia: " + e.toString());
			return 0;
		}
	}
	private RowMapper<Integer> getSecuenciaRowMapper = new RowMapper<Integer>() {
		@Override
		public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Integer secuencia = 0;
			try {
				secuencia = rs.getInt(1);
			} catch (Exception e) {
				System.err.println("Exception CalificacionRepositoryImpl getSecuencia_1: " + e.toString());
			}
			return secuencia;
		}
	};

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear la calificacion
	 */
	public Integer crearCalificacion(CalificacionDTO calificacionDTO) throws Exception {
		try {
			String SQL = " INSERT INTO public.calificacion(calicodi, califech, caliuser, tipocodi, caliobse)"
					+ "	VALUES (?, CURRENT_TIMESTAMP, ?, ?, ?) ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					calificacionDTO.setCalicodi(getSecuencia());
					ps.setInt(1, calificacionDTO.getCalicodi());
					ps.setLong(2, calificacionDTO.getCaliuser().getDocumento());
					ps.setInt(3, calificacionDTO.getTipocodi().getTipocodi());
					ps.setString(4, calificacionDTO.getCaliobse());
				}
			};
			getJdbcTemplate().update(SQL, setter);
			return calificacionDTO.getCalicodi();
		} catch (Exception e) {
			System.err.println("Exception CalificacionRepositoryImpl crearCalificacion: " + e.toString());
			return 0;
		}
	}

}
