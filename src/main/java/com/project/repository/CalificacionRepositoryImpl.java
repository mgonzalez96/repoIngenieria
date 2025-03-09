package com.project.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import com.project.dto.CalificacionDTO;
import com.project.dto.GastronomiaDTO;
import com.project.dto.TipoCalificacionDTO;
import com.project.dto.UsuarioDTO;

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
			return getJdbcTemplate().query(SQL, new getSecuenciaRowMapper());
		} catch (Exception e) {
			System.err.println("Exception CalificacionRepositoryImpl getSecuencia: " + e.toString());
			return 0;
		}
	}

	private class getSecuenciaRowMapper implements ResultSetExtractor<Integer> {
		@Override
		public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
			Integer secuencia = 0;
			while (rs.next()) {
				secuencia = rs.getInt(1);
			}
			return secuencia;
		}
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear la calificacion de un plato tipico
	 */
	public Integer crearCalificacion(CalificacionDTO calificacionDTO) throws Exception {
		try {
			String SQL = " INSERT INTO public.calificacion(calicodi, califech, caliuser, tipocodi, caliobse, gastcodi)"
					+ "	VALUES (?, CURRENT_TIMESTAMP, ?, ?, ?, ?) ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					calificacionDTO.setCalicodi(getSecuencia());
					ps.setInt(1, calificacionDTO.getCalicodi());
					ps.setLong(2, calificacionDTO.getCaliuser().getDocumento());
					ps.setInt(3, calificacionDTO.getTipocodi().getTipocodi());
					ps.setString(4, calificacionDTO.getCaliobse());
					ps.setInt(5, calificacionDTO.getGastcodi().getGastcodi());
				}
			};
			getJdbcTemplate().update(SQL, setter);
			return calificacionDTO.getCalicodi();
		} catch (Exception e) {
			System.err.println("Exception CalificacionRepositoryImpl crearCalificacion: " + e.toString());
			return 0;
		}
	}

	// ----------------------------------------------------------------------------------

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar la calificacion, gastronomia y usuario
	 */
	public List<CalificacionDTO> consultaCalificaGastronomia() throws Exception {
		try {
			String SQL = " SELECT g.gastcodi, g.gastnomb, g.gastdesc, g.gastimag, g.gastface,     "
					+ "		g.gasturlx, g.gastinst, g.gastesta,   "
					+ "		coalesce(c.calicodi,0)calicodi, c.califech, coalesce(c.caliobse,'-')caliobse, "
					+ "		coalesce(tc.tipocodi,0) tipocodi, coalesce(tc.tiponomb,'-') calificacion, "
					+ "     coalesce(u.documento,0)documento,  " + "		coalesce(u.nombreuno,'-')nombreuno,  "
					+ "	    coalesce(u.nombredos,'-')nombredos, coalesce(u.apellidouno,'-')apellidouno,   "
					+ "        coalesce(u.apellidodos,'-')apellidodos  " + " FROM public.gastronomia g   "
					+ " LEFT JOIN public.calificacion c   " + "  ON g.gastcodi = c.gastcodi    "
					+ " LEFT JOIN public.tipocalificacion tc    " + "  ON tc.tipocodi = c.tipocodi   "
					+ " LEFT JOIN public.usuario u " + "  ON u.documento = c.caliuser ";
			return getJdbcTemplate().query(SQL, consultaCalificaGastronomiaRowMapper);
		} catch (Exception e) {
			System.err.println("Exception CalificacionRepositoryImpl consultaCalificaGastronomia: " + e.toString());
			throw new Exception("No existen platos tipicos creados");
		}
	}

	private RowMapper<CalificacionDTO> consultaCalificaGastronomiaRowMapper = new RowMapper<CalificacionDTO>() {
		@Override
		public CalificacionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			CalificacionDTO calificacion = null;
			UsuarioDTO caliuser = null;
			TipoCalificacionDTO tipocodi = null;
			GastronomiaDTO gastcodi = null;
			try {
				calificacion = new CalificacionDTO();
				caliuser = new UsuarioDTO();
				tipocodi = new TipoCalificacionDTO();
				gastcodi = new GastronomiaDTO();

				gastcodi.setGastcodi(rs.getInt("gastcodi"));
				gastcodi.setGastnomb(rs.getString("gastnomb"));
				gastcodi.setGastdesc(rs.getString("gastdesc"));
				gastcodi.setGastimag(rs.getString("gastimag"));
				gastcodi.setGastface(rs.getString("gastface"));
				gastcodi.setGasturlx(rs.getString("gasturlx"));
				gastcodi.setGastinst(rs.getString("gastinst"));
				gastcodi.setGastesta(rs.getInt("gastesta"));
				if (gastcodi.getGastesta() == 1) {
					gastcodi.setGastestaStr("Activo");
				} else {
					gastcodi.setGastestaStr("Inactivo");
				}
				calificacion.setGastcodi(gastcodi);

				calificacion.setCalicodi(rs.getInt("calicodi"));
				calificacion.setCalifech(rs.getTimestamp("califech"));
				calificacion.setCaliobse(rs.getString("caliobse"));

				tipocodi.setTipocodi(rs.getInt("tipocodi"));
				tipocodi.setTiponomb(rs.getInt("calificacion"));
				calificacion.setTipocodi(tipocodi);

				caliuser.setDocumento(rs.getLong("documento"));
				caliuser.setNombreuno(rs.getString("nombreuno"));
				caliuser.setNombredos(rs.getString("nombredos"));
				caliuser.setApellidouno(rs.getString("apellidouno"));
				caliuser.setApellidodos(rs.getString("apellidodos"));
				calificacion.setCaliuser(caliuser);
			} catch (Exception e) {
				System.err
						.println("Exception CalificacionRepositoryImpl consultaCalificaGastronomia_1: " + e.toString());
			}
			return calificacion;
		}
	};

	// ----------------------------------------------------------------------------------

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método consultar la gastronomia por calificacion
	 */
	public List<CalificacionDTO> consGastronomiaByCalificacion(CalificacionDTO calificacion) throws Exception {
		try {
			String SQL = " SELECT g.gastcodi, g.gastnomb, g.gastdesc, g.gastimag, g.gastface,     "
					+ "		g.gasturlx, g.gastinst, g.gastesta,   "
					+ "		coalesce(c.calicodi,0)calicodi, c.califech, coalesce(c.caliobse,'-')caliobse, "
					+ "		coalesce(tc.tipocodi,0) tipocodi, coalesce(tc.tiponomb,'-') calificacion, "
					+ "     coalesce(u.documento,0)documento,  " + "		coalesce(u.nombreuno,'-')nombreuno,  "
					+ "	    coalesce(u.nombredos,'-')nombredos, coalesce(u.apellidouno,'-')apellidouno,   "
					+ "        coalesce(u.apellidodos,'-')apellidodos  " + " FROM public.gastronomia g   "
					+ " LEFT JOIN public.calificacion c   " + "  ON g.gastcodi = c.gastcodi    "
					+ " LEFT JOIN public.tipocalificacion tc    " + "  ON tc.tipocodi = c.tipocodi   "
					+ " LEFT JOIN public.usuario u " + "  ON u.documento = c.caliuser "
					+ " WHERE tc.tiponomb::Integer = ? ";
			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, calificacion.getTipocodi().getTiponomb());
				}
			};
			return getJdbcTemplate().query(SQL, setter, consGastronomiaByCalificacionRowMapper);
		} catch (Exception e) {
			System.err.println("Exception CalificacionRepositoryImpl consGastronomiaByCalificacion: " + e.toString());
			throw new Exception("No existen platos tipicos creados");
		}
	}

	private RowMapper<CalificacionDTO> consGastronomiaByCalificacionRowMapper = new RowMapper<CalificacionDTO>() {
		@Override
		public CalificacionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			CalificacionDTO calificacion = null;
			UsuarioDTO caliuser = null;
			TipoCalificacionDTO tipocodi = null;
			GastronomiaDTO gastcodi = null;
			try {
				calificacion = new CalificacionDTO();
				caliuser = new UsuarioDTO();
				tipocodi = new TipoCalificacionDTO();
				gastcodi = new GastronomiaDTO();

				gastcodi.setGastcodi(rs.getInt("gastcodi"));
				gastcodi.setGastnomb(rs.getString("gastnomb"));
				gastcodi.setGastdesc(rs.getString("gastdesc"));
				gastcodi.setGastimag(rs.getString("gastimag"));
				gastcodi.setGastface(rs.getString("gastface"));
				gastcodi.setGasturlx(rs.getString("gasturlx"));
				gastcodi.setGastinst(rs.getString("gastinst"));
				gastcodi.setGastesta(rs.getInt("gastesta"));
				if (gastcodi.getGastesta() == 1) {
					gastcodi.setGastestaStr("Activo");
				} else {
					gastcodi.setGastestaStr("Inactivo");
				}
				calificacion.setGastcodi(gastcodi);

				calificacion.setCalicodi(rs.getInt("calicodi"));
				calificacion.setCalifech(rs.getTimestamp("califech"));
				calificacion.setCaliobse(rs.getString("caliobse"));

				tipocodi.setTipocodi(rs.getInt("tipocodi"));
				tipocodi.setTiponomb(rs.getInt("calificacion"));
				calificacion.setTipocodi(tipocodi);

				caliuser.setDocumento(rs.getLong("documento"));
				caliuser.setNombreuno(rs.getString("nombreuno"));
				caliuser.setNombredos(rs.getString("nombredos"));
				caliuser.setApellidouno(rs.getString("apellidouno"));
				caliuser.setApellidodos(rs.getString("apellidodos"));
				calificacion.setCaliuser(caliuser);
			} catch (Exception e) {
				System.err.println(
						"Exception CalificacionRepositoryImpl consGastronomiaByCalificacion_1: " + e.toString());
			}
			return calificacion;
		}
	};
//---------------------------------------------------------------------------------------------------

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Consulta El promedio de calificacion por codigo de gastronomia
	 */
	public CalificacionDTO getPromedioCalificacion(GastronomiaDTO gastcodi) throws Exception {
		try {
			String SQL = " SELECT g.gastcodi, ROUND(AVG(tc.tiponomb::integer)) AS promedio " + " FROM public.usuario u "
					+ " LEFT JOIN calificacion c  " + "  ON u.documento = c.caliuser "
					+ " LEFT JOIN tipocalificacion tc  " + "  ON tc.tipocodi = c.tipocodi "
					+ " LEFT JOIN gastronomia g  " + "  ON g.gastcodi = c.gastcodi " + " WHERE g.gastcodi = ? "
					+ " GROUP BY g.gastcodi ";
			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, gastcodi.getGastcodi());
				}
			};
			return getJdbcTemplate().query(SQL, setter, new getPromedioCalificacionRowMapper());
		} catch (Exception e) {
			System.err.println("Exception CalificacionRepositoryImpl getPromedioCalificacion: " + e.toString());
			throw new Exception("Error al consultar el promedio del plato tipico");
		}
	}

	private class getPromedioCalificacionRowMapper implements ResultSetExtractor<CalificacionDTO> {
		@Override
		public CalificacionDTO extractData(ResultSet rs) throws SQLException, DataAccessException {
			CalificacionDTO calificacion = null;
			while (rs.next()) {
				calificacion = new CalificacionDTO();
				calificacion.getGastcodi().setGastcodi(rs.getInt("gastcodi"));
				calificacion.setPromedio(rs.getInt("promedio"));
			}
			return calificacion;
		}
	}

//---------------------------------------------------------------------------------------------------

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Lista las calificaciones por usuario
	 */
	public List<CalificacionDTO> listaCalificacionByDocumento(CalificacionDTO calificacion) throws Exception {
		try {
			String SQL = " SELECT g.gastcodi, g.gastnomb, c.caliuser, "
					+ "       u.nombreuno, u.apellidouno, "
					+ "       t.tiponomb,  "
					+ "         case  "
					+ "	       when tiponomb = '5' then 'Excelente' "
					+ "	       when tiponomb = '4' then 'Bueno' "
					+ "	       when tiponomb = '3' then 'Regular' "
					+ "	       when tiponomb = '2' then 'Malo' "
					+ "	     else 'Pésimo' "
					+ "	     end  as calificacion "
					+ " FROM public.calificacion c "
					+ " INNER JOIN public.tipocalificacion t "
					+ "  ON t.tipocodi = c.tipocodi "
					+ " INNER JOIN public.gastronomia g "
					+ "  ON g.gastcodi = c.gastcodi "
					+ " INNER JOIN public.usuario u "
					+ "  ON u.documento = c.caliuser "
					+ " WHERE c.caliuser = ? "
					+ " order by 3 desc ";
			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setLong(1, calificacion.getCaliuser().getDocumento());
				}
			};
			return getJdbcTemplate().query(SQL, setter, listaCalificacionByDocumentoRowMapper);
		} catch (Exception e) {
			System.err.println("Exception CalificacionRepositoryImpl listaCalificacionByDocumento: " + e.toString());
			throw new Exception("No existen calificaciones del usuario");
		}
	}

	private RowMapper<CalificacionDTO> listaCalificacionByDocumentoRowMapper = new RowMapper<CalificacionDTO>() {
		@Override
		public CalificacionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			CalificacionDTO calificacion = null;
			try {
				calificacion = new CalificacionDTO();
				calificacion.getGastcodi().setGastcodi(rs.getInt("gastcodi"));
				calificacion.getGastcodi().setGastnomb(rs.getString("gastnomb"));
				calificacion.getCaliuser().setDocumento(rs.getLong("caliuser"));
				calificacion.getCaliuser().setNombreuno(rs.getString("nombreuno"));
				calificacion.getCaliuser().setApellidouno(rs.getString("apellidouno"));
				calificacion.getTipocodi().setTiponomb(rs.getInt("tiponomb"));
				calificacion.getTipocodi().setTiponombStr(rs.getString("calificacion"));
			} catch (Exception e) {
				System.err.println(
						"Exception CalificacionRepositoryImpl listaCalificacionByDocumento_1: " + e.toString());
			}
			return calificacion;
		}
	};

}
