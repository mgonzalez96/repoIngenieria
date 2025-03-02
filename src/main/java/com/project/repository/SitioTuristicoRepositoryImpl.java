package com.project.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.project.dto.ActividadDTO;

@Repository
public class SitioTuristicoRepositoryImpl extends JdbcDaoSupport {

	public SitioTuristicoRepositoryImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar todas las actividades con estado activo
	 */
	public List<ActividadDTO> consultaAllActividad() throws Exception {
		try {
			String SQL = " SELECT acticodi, actinomb, actidesc, actiimag, actifein fechainicio, "
					+ "       actifefi fechafin, t.evencodi, t.evennomb evento, "
					+ "	   u.ubiccodi, u.ubicnomb ubicacion "
					+ " FROM public.actividad a, public.ubicacion u, public.tipoevento t "
					+ " WHERE u.ubiccodi = a.ubiccodi " + " AND t.evencodi = a.evencodi " + " AND a.actiesta = 1 ";
			return getJdbcTemplate().query(SQL, consultaAllActividadRowMapper);
		} catch (Exception e) {
			System.err.println("Exception ActividadRepositoryImpl consultaAllActividad: " + e.toString());
			throw new Exception("No existen actividades");
		}
	}

	private RowMapper<ActividadDTO> consultaAllActividadRowMapper = new RowMapper<ActividadDTO>() {
		@Override
		public ActividadDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			ActividadDTO actividadDTO = null;
			try {
				actividadDTO = new ActividadDTO();
				actividadDTO.setActicodi(rs.getInt("acticodi"));
				actividadDTO.setActinomb(rs.getString("actinomb"));
				actividadDTO.setActidesc(rs.getString("actidesc"));
				actividadDTO.setActiimag(rs.getString("actiimag"));
				actividadDTO.setActifein(rs.getDate("fechainicio"));
				actividadDTO.setActifefi(rs.getDate("fechafin"));
				actividadDTO.getEvencodi().setEvencodi(rs.getInt("evencodi"));
				actividadDTO.getEvencodi().setEvennomb(rs.getString("evento"));
				actividadDTO.getUbiccodi().setUbiccodi(rs.getInt("ubiccodi"));
				actividadDTO.getUbiccodi().setUbicnomb(rs.getString("ubicacion"));
				actividadDTO.setActiesta(1);
			} catch (Exception e) {
				System.err.println("Exception ActividadRepositoryImpl consultaAllActividad_1: " + e.toString());
			}
			return actividadDTO;
		}
	};
	
	
}
