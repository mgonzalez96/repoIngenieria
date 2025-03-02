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
import com.project.dto.TipoEventoDTO;

@Repository
public class ActividadRepositoryImpl extends JdbcDaoSupport {

	public ActividadRepositoryImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar todos los tipos de eventos creados
	 */
	public List<ActividadDTO> consultaAllActividad() throws Exception {
		try {
			String SQL = "  ";
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

			} catch (Exception e) {
				System.err.println("Exception ActividadRepositoryImpl consultaAllActividad_1: " + e.toString());
			}
			return actividadDTO;
		}
	};

//-------------------------------------------------------------------------------------
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear el tipo de evento
	 */
	public Integer crearTipoEvento(TipoEventoDTO tipoEventoDTO) throws Exception {
		try {
			String SQL = " INSERT INTO public.tipoevento( " + "	evencodi, evennomb) "
					+ "	VALUES (nextval('tipo_evento_evencodi_seq'), ?) ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, tipoEventoDTO.getEvennomb());
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception TipoEventoRepositoryImpl crearTipoEvento: " + e.toString());
			throw new Exception("Tipo de Evento ya existe");
		}
	}

// -------------------------------------------------------------------------------------
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar el tipo de evento
	 */
	public Integer modificarTipoEvento(TipoEventoDTO tipoEventoDTO) throws Exception {
		try {
			String SQL = " UPDATE public.tipoevento " + "	SET evennomb=? " + "	WHERE evencodi = ? ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, tipoEventoDTO.getEvennomb());
					ps.setInt(2, tipoEventoDTO.getEvencodi());
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception TipoEventoRepositoryImpl modificarTipoEvento: " + e.toString());
			throw new Exception("Tipo de Evento no existe");
		}
	}

// -------------------------------------------------------------------------------------
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar el tipo de evento
	 */
	public Integer eliminarTipoEvento(Integer evencodi) throws Exception {
		try {
			String SQL = " DELETE FROM public.tipoevento WHERE evencodi = ? ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, evencodi);
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception TipoEventoRepositoryImpl eliminarTipoEvento: " + e.toString());
			throw new Exception("Tipo de Evento no existe para ser eliminado");
		}
	}

}
