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
import com.project.dto.UbicacionDTO;

@Repository
public class UbicacionRepositoryImpl extends JdbcDaoSupport {

	public UbicacionRepositoryImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar las ubicaciones creadas
	 */
	public List<UbicacionDTO> consultaAllUbicacion() throws Exception {
		try {
			String SQL = " SELECT ubiccodi, ubicnomb FROM public.ubicacion ";
			return getJdbcTemplate().query(SQL, consultaAllTipoEventoRowMapper);
		} catch (Exception e) {
			System.err.println("Exception UbicacionRepositoryImpl consultaAllUbicacion: " + e.toString());
			throw new Exception("No existen ubicaciones creadas");
		}
	}

	private RowMapper<UbicacionDTO> consultaAllTipoEventoRowMapper = new RowMapper<UbicacionDTO>() {
		@Override
		public UbicacionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			UbicacionDTO ubicacion = null;
			try {
				ubicacion = new UbicacionDTO();
				ubicacion.setUbiccodi(rs.getInt("ubiccodi"));
				ubicacion.setUbicnomb(rs.getString("ubicnomb"));
			} catch (Exception e) {
				System.err.println("Exception UbicacionRepositoryImpl consultaAllUbicacion_1: " + e.toString());
			}
			return ubicacion;
		}
	};

//-------------------------------------------------------------------------------------
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear las ubicaciones
	 */
	public Integer crearUbicacion(UbicacionDTO ubicacionDTO) throws Exception {
		try {
			String SQL = " INSERT INTO public.ubicacion("
					+ "	ubiccodi, ubicnomb) VALUES (nextval('sec_ubicacion'), ?) ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, ubicacionDTO.getUbicnomb());
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception UbicacionRepositoryImpl crearUbicacion: " + e.toString());
			throw new Exception("Ubicacion ya existe");
		}
	}

// -------------------------------------------------------------------------------------
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar las ubicaciones por codigo de creacion
	 */
	public Integer modificarUbicacion(UbicacionDTO ubicacionDTO) throws Exception {
		try {
			String SQL = " UPDATE public.ubicacion " + "	SET ubicnomb=? " + "	WHERE ubiccodi=? ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, ubicacionDTO.getUbicnomb());
					ps.setInt(2, ubicacionDTO.getUbiccodi());
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception UbicacionRepositoryImpl modificarUbicacion: " + e.toString());
			throw new Exception("Ubicacion no existe");
		}
	}

// -------------------------------------------------------------------------------------
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para eliminar la Ubicacion
	 */
	public Integer eliminarUbicacion(Integer ubiccodi) throws Exception {
		try {
			String SQL = " DELETE FROM public.ubicacion WHERE ubiccodi = ? ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, ubiccodi);
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception UbicacionRepositoryImpl eliminarUbicacion: " + e.toString());
			throw new Exception("Ubicacion no existe para ser eliminado");
		}
	}
}
