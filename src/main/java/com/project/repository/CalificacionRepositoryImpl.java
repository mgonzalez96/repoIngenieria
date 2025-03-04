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

}
