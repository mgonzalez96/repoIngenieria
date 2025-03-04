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

import com.project.dto.CalificacionDTO;
import com.project.dto.GastronomiaDTO;

@Repository
public class GastronomiaRepositoryImpl extends JdbcDaoSupport {

	public GastronomiaRepositoryImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

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

	// ---------------------------------------------------------------------------------
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar los platos tipicos de la region
	 */
	public List<CalificacionDTO> consultaAllGastronomia(GastronomiaDTO gastronomiaDTO) throws Exception {
		try {
			String SQL = " SELECT g.gastcodi, g.gastnomb, g.gastdesc, g.gastimag, g.gastface,     "
					+ "		g.gasturlx, g.gastinst, g.gastesta,   "
					+ "		coalesce(c.calicodi,0)calicodi, c.califech, coalesce(c.caliobse,'-')caliobse, "
					+ "		coalesce(tc.tiponomb,'-') calificacion   " + " FROM public.gastronomia g   "
					+ " LEFT JOIN public.calificacion c   " + "  ON g.gastcodi = c.gastcodi    "
					+ " LEFT JOIN public.tipocalificacion tc    " + "  ON tc.tipocodi = c.tipocodi   "
					+ " WHERE  gastesta = ? ";
			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, gastronomiaDTO.getGastesta());
				}
			};
			return getJdbcTemplate().query(SQL, setter, consultaAllGastronomiaRowMapper);
		} catch (Exception e) {
			System.err.println("Exception CalificacionRepositoryImpl consultaAllGastronomia: " + e.toString());
			throw new Exception("No existen platos tipicos creados");
		}
	}

	private RowMapper<CalificacionDTO> consultaAllGastronomiaRowMapper = new RowMapper<CalificacionDTO>() {
		@Override
		public CalificacionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			CalificacionDTO gastronomia = null;
			try {
				gastronomia = new CalificacionDTO();
				gastronomia.getGastcodi().setGastcodi(rs.getInt("gastcodi"));
				gastronomia.getGastcodi().setGastnomb(rs.getString("gastnomb"));
				gastronomia.getGastcodi().setGastdesc(rs.getString("gastdesc"));
				gastronomia.getGastcodi().setGastimag(rs.getString("gastimag"));
				gastronomia.getGastcodi().setGastface(rs.getString("gastface"));
				gastronomia.getGastcodi().setGasturlx(rs.getString("gasturlx"));
				gastronomia.getGastcodi().setGastinst(rs.getString("gastinst"));
				gastronomia.getGastcodi().setGastesta(rs.getInt("gastesta"));
				if (gastronomia.getGastcodi().getGastesta() == 1) {
					gastronomia.getGastcodi().setGastestaStr("Activo");
				} else {
					gastronomia.getGastcodi().setGastestaStr("Inactiva");
				}
				gastronomia.setCalicodi(rs.getInt("calicodi"));
				if (rs.getTimestamp("califech") != null) {
					gastronomia.setCalifech(rs.getTimestamp("califech"));
				} else {
					gastronomia.setCalifech(null);
				}
				gastronomia.setCaliobse(rs.getString("caliobse"));
				gastronomia.getTipocodi().setTiponomb(rs.getInt("calificacion"));
			} catch (Exception e) {
				System.err.println("Exception CalificacionRepositoryImpl consultaAllGastronomia_1: " + e.toString());
			}
			return gastronomia;
		}
	};

}
