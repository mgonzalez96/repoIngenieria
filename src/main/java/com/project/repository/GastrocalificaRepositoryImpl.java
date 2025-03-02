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
import com.project.dto.GastrocalificaDTO;

@Repository
public class GastrocalificaRepositoryImpl extends JdbcDaoSupport {

	public GastrocalificaRepositoryImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear la calificacion de la gastronomia
	 */
	public Integer creardetalGastronomia(GastrocalificaDTO gastrDto) throws Exception {
		try {
			String SQL = " INSERT INTO public.gastrocalifica(" + "	gacacodi, calicodi, gastcodi) "
					+ "	VALUES (nextval('sec_gacacodi'), ?, ?) ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setLong(1, gastrDto.getCalicodi().getCalicodi());
					ps.setLong(2, gastrDto.getGastcodi().getGastcodi());
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception GastrocalificaRepositoryImpl creardetalGastronomia: " + e.toString());
			throw new Exception("error al crear el detalle de la calificacion de la gastronomia");
		}
	}
//----------------------------------------------------------------------------------

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar los platos tipicos de la region
	 */
	public List<GastrocalificaDTO> consultaCalificaGastronomia() throws Exception {
		try {
			String SQL = " SELECT a.gacacodi, c.calicodi, c.califech, c.caliobse, g.gastcodi, g.gastnomb, t.tiponomb, "
					+ "       u.documento, u.nombreuno, coalesce(u.nombredos,'')nombredos, u.apellidouno,  "
					+ "	   coalesce(u.apellidodos,'')apellidodos "
					+ "	FROM public.gastrocalifica a, public.gastronomia g, public.calificacion c, public.tipocalificacion t, "
					+ "	     public.usuario u " + "	WHERE a.calicodi = c.calicodi " + "	AND a.gastcodi = g.gastcodi "
					+ "	AND c.tipocodi = t.tipocodi " + "	AND u.documento = c.caliuser ";
			return getJdbcTemplate().query(SQL, consultaCalificaGastronomiaRowMapper);
		} catch (Exception e) {
			System.err.println("Exception GastrocalificaRepositoryImpl consultaCalificaGastronomia: " + e.toString());
			throw new Exception("No existen platos tipicos creados");
		}
	}

	private RowMapper<GastrocalificaDTO> consultaCalificaGastronomiaRowMapper = new RowMapper<GastrocalificaDTO>() {
		@Override
		public GastrocalificaDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			GastrocalificaDTO gastronomia = null;
			try {
				gastronomia = new GastrocalificaDTO();
				gastronomia.setGacacodi(rs.getInt("gacacodi"));
				gastronomia.getCalicodi().setCalicodi(rs.getInt("calicodi"));
				gastronomia.getCalicodi().setCalifech(rs.getTimestamp("califech"));
				gastronomia.getCalicodi().setCaliobse(rs.getString("caliobse"));
				gastronomia.getGastcodi().setGastcodi(rs.getInt("gastcodi"));
				gastronomia.getGastcodi().setGastnomb(rs.getString("gastnomb"));
				gastronomia.getCalicodi().getTipocodi().setTiponomb(rs.getString("tiponomb"));
				gastronomia.getCalicodi().getCaliuser().setDocumento(rs.getLong("documento"));
				gastronomia.getCalicodi().getCaliuser().setNombreuno(rs.getString("nombreuno"));
				gastronomia.getCalicodi().getCaliuser().setNombredos(rs.getString("nombredos"));
				gastronomia.getCalicodi().getCaliuser().setApellidouno(rs.getString("apellidouno"));
				gastronomia.getCalicodi().getCaliuser().setApellidodos(rs.getString("apellidodos"));
			} catch (Exception e) {
				System.err.println(
						"Exception GastrocalificaRepositoryImpl consultaCalificaGastronomia_1: " + e.toString());
			}
			return gastronomia;
		}
	};

}
