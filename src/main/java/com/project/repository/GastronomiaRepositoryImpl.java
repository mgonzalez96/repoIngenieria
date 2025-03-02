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

import com.project.dto.GastronomiaDTO;

@Repository
public class GastronomiaRepositoryImpl extends JdbcDaoSupport {

	public GastronomiaRepositoryImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar los platos tipicos de la region
	 */
	public List<GastronomiaDTO> consultaAllGastronomia(GastronomiaDTO gastronomiaDTO) throws Exception {
		try {
			String SQL = " SELECT gastcodi, gastnomb, gastdesc, gastimag, gastface, gasturlx, gastinst, gastesta "
					+ "	FROM public.gastronomia " + " WHERE gastesta = ?";
			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, gastronomiaDTO.getGastesta());
				}
			};
			return getJdbcTemplate().query(SQL, setter, consultaAllGastronomiaRowMapper);
		} catch (Exception e) {
			System.err.println("Exception GastronomiaRepositoryImpl consultaAllGastronomia: " + e.toString());
			throw new Exception("No existen platos tipicos creados");
		}
	}

	private RowMapper<GastronomiaDTO> consultaAllGastronomiaRowMapper = new RowMapper<GastronomiaDTO>() {
		@Override
		public GastronomiaDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			GastronomiaDTO gastronomia = null;
			try {
				gastronomia = new GastronomiaDTO();
				gastronomia.setGastcodi(rs.getInt("gastcodi"));
				gastronomia.setGastnomb(rs.getString("gastnomb"));
				gastronomia.setGastdesc(rs.getString("gastdesc"));
				gastronomia.setGastimag(rs.getString("gastimag"));
				gastronomia.setGastface(rs.getString("gastface"));
				gastronomia.setGasturlx(rs.getString("gasturlx"));
				gastronomia.setGastinst(rs.getString("gastinst"));
				gastronomia.setGastesta(rs.getInt("gastesta"));
				if (gastronomia.getGastesta() == 1) {
					gastronomia.setGastestaStr("Activo");
				} else {
					gastronomia.setGastestaStr("Inactiva");
				}
			} catch (Exception e) {
				System.err.println("Exception GastronomiaRepositoryImpl consultaAllGastronomia_1: " + e.toString());
			}
			return gastronomia;
		}
	};

// -------------------------------------------------------------------------------------
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear los platos tipicos
	 */
	public Integer crearGastronomia(GastronomiaDTO gastronomiaDTO) throws Exception {
		try {
			String SQL = " INSERT INTO public.gastronomia("
					+ "	gastcodi, gastnomb, gastdesc, gastimag, gastface, gasturlx, gastinst, gastesta) "
					+ "	VALUES (nextval('sec_gastronomia'), ?, ?, ?, ?, ?, ?, 1)  ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, gastronomiaDTO.getGastnomb());
					ps.setString(2, gastronomiaDTO.getGastdesc());
					ps.setString(3, gastronomiaDTO.getGastimag());
					ps.setString(4, gastronomiaDTO.getGastface());
					ps.setString(5, gastronomiaDTO.getGasturlx());
					ps.setString(6, gastronomiaDTO.getGastinst());
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception GastronomiaRepositoryImpl crearGastronomia: " + e.toString());
			throw new Exception("Gastronomia ya existe");
		}
	}

// -------------------------------------------------------------------------------------
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar los platos tipicos
	 */
	public Integer modificarGastronomia(GastronomiaDTO gastronomiaDTO) throws Exception {
		try {
			String SQL = " UPDATE public.gastronomia "
					+ "	SET gastnomb=?, gastdesc=?, gastimag=?, gastface=?, gasturlx=?, gastinst=? "
					+ "	WHERE gastcodi=? ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, gastronomiaDTO.getGastnomb());
					ps.setString(2, gastronomiaDTO.getGastdesc());
					ps.setString(3, gastronomiaDTO.getGastimag());
					ps.setString(4, gastronomiaDTO.getGastface());
					ps.setString(5, gastronomiaDTO.getGasturlx());
					ps.setString(6, gastronomiaDTO.getGastinst());
					ps.setInt(7, gastronomiaDTO.getGastcodi());
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception GastronomiaRepositoryImpl modificarGastronomia: " + e.toString());
			throw new Exception("Gastronomia no existe");
		}
	}

// -------------------------------------------------------------------------------------
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para inactivar los platos tipicos
	 */
	public Integer inactivarGastronomia(GastronomiaDTO gastronomiaDTO) throws Exception {
		try {
			String SQL = " UPDATE public.gastronomia " + "	SET gastesta=0 " + "	WHERE gastcodi=? ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, gastronomiaDTO.getGastcodi());
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception GastronomiaRepositoryImpl inactivarGastronomia: " + e.toString());
			throw new Exception("Gastronomia no existe para ser inactivada");
		}
	}

}
