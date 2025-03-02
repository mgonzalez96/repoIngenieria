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
import com.project.dto.ActividadDTO;

@Repository
public class ActividadRepositoryImpl extends JdbcDaoSupport {

	public ActividadRepositoryImpl(DataSource dataSource) {
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

//-------------------------------------------------------------------------------------
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear las actividades unidad a un tipo de evento
	 */
	public Integer crearActividad(ActividadDTO actividadDTO) throws Exception {
		try {
			String SQL = " INSERT INTO public.actividad("
					+ "	acticodi, actinomb, actidesc, actiimag, evencodi, ubiccodi, actifein, actifefi, actiesta) "
					+ "	VALUES (nextval('sec_actividad'), ?, ?, ?, ?, ?, ?, ?, 1) ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, actividadDTO.getActinomb());
					ps.setString(2, actividadDTO.getActidesc());
					ps.setString(3, actividadDTO.getActiimag());
					ps.setInt(4, actividadDTO.getEvencodi().getEvencodi());
					ps.setInt(5, actividadDTO.getUbiccodi().getUbiccodi());
					ps.setDate(6, new java.sql.Date(actividadDTO.getActifein().getTime()));
					ps.setDate(7, new java.sql.Date(actividadDTO.getActifefi().getTime()));
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception ActividadRepositoryImpl crearActividad: " + e.toString());
			throw new Exception("Actividad ya existe");
		}
	}

// -------------------------------------------------------------------------------------
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar las actividades
	 */
	public Integer modificarActividad(ActividadDTO actividadDTO) throws Exception {
		try {
			String SQL = " UPDATE public.actividad  " + "	SET actinomb=?, actidesc=?,   "
					+ "	    actiimag=?, evencodi=?, ubiccodi=?,   " + "		actifein=?, actifefi=?  "
					+ "	WHERE acticodi=? ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, actividadDTO.getActinomb());
					ps.setString(2, actividadDTO.getActidesc());
					ps.setString(3, actividadDTO.getActiimag());
					ps.setInt(4, actividadDTO.getEvencodi().getEvencodi());
					ps.setInt(5, actividadDTO.getUbiccodi().getUbiccodi());
					ps.setDate(6, new java.sql.Date(actividadDTO.getActifein().getTime()));
					ps.setDate(7, new java.sql.Date(actividadDTO.getActifefi().getTime()));
					ps.setInt(8, actividadDTO.getActicodi());
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception ActividadRepositoryImpl modificarActividad: " + e.toString());
			throw new Exception("Actividad no existe");
		}
	}

// -------------------------------------------------------------------------------------
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para inactivar una actividad por codigo
	 */
	public Integer inactivarActividad(Integer acticodi) throws Exception {
		try {
			String SQL = " UPDATE public.actividad SET actiesta = 0 WHERE acticodi=? ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, acticodi);
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception ActividadRepositoryImpl inactivarActividad: " + e.toString());
			throw new Exception("Actividad no existe para inactivarla");
		}
	}
}
