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
import com.project.dto.SitioTuristicoDTO;

@Repository
public class SitioTuristicoRepositoryImpl extends JdbcDaoSupport {

	public SitioTuristicoRepositoryImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar todos los sitios turisticos
	 */
	public List<SitioTuristicoDTO> consultaAllSitioTuristico(SitioTuristicoDTO sitioTuristicoDTO) throws Exception {
		try {
			String SQL = " SELECT s.turicodi, s.turinomb, s.turidire, s.turiimag, u.ubiccodi, "
					+ "       u.ubicnomb, s.turiesta, s.turilike, s.turiface, s.turiurlx, s.turiinst "
					+ "FROM public.sitioturistico s, public.ubicacion u " + "WHERE u.ubiccodi = s.ubiccodi 	 "
					+ "AND S.turiesta = ? ";
			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, sitioTuristicoDTO.getTuriesta());
				}
			};
			return getJdbcTemplate().query(SQL, setter, consultaAllSitioTuristicoRowMapper);
		} catch (Exception e) {
			System.err.println("Exception SitioTuristicoRepositoryImpl consultaAllSitioTuristico: " + e.toString());
			throw new Exception("No existen actividades");
		}
	}

	private RowMapper<SitioTuristicoDTO> consultaAllSitioTuristicoRowMapper = new RowMapper<SitioTuristicoDTO>() {
		@Override
		public SitioTuristicoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			SitioTuristicoDTO sitio = null;
			try {
				sitio = new SitioTuristicoDTO();
				sitio.setTuricodi(rs.getInt("turicodi"));
				sitio.setTurinomb(rs.getString("turinomb"));
				sitio.setTuridire(rs.getString("turidire"));
				sitio.setTuriimag(rs.getString("turiimag"));
				sitio.getUbiccodi().setUbiccodi(rs.getInt("ubiccodi"));
				sitio.getUbiccodi().setUbicnomb(rs.getString("ubicnomb"));
				sitio.setTuriesta(rs.getInt("turiesta"));
				if (sitio.getTuriesta() == 1) {
					sitio.setTuriestaStr("Activo");
				} else {
					sitio.setTuriestaStr("Inactivo");
				}
				sitio.setTurilike(rs.getInt("turilike"));
				sitio.setTuriface(rs.getString("turiface"));
				sitio.setTuriurlx(rs.getString("turiurlx"));
				sitio.setTuriinst(rs.getString("turiinst"));
			} catch (Exception e) {
				System.err
						.println("Exception SitioTuristicoRepositoryImpl consultaAllSitioTuristico_1: " + e.toString());
			}
			return sitio;
		}
	};

// -------------------------------------------------------------------------------------
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear los sitios turisticos unidos a una ubicacion
	 */
	public Integer crearSitioTuristico(SitioTuristicoDTO sitioTuristicoDTO) throws Exception {
		try {
			String SQL = "  INSERT INTO public.sitioturistico("
					+ "	turicodi, turinomb, turidire, turiimag, ubiccodi, turiesta, turilike, turiface,"
					+ " turiurlx, turiinst) " + "	VALUES (nextval('sec_sitioturistico'), ?, ?, ?, ?, 1, ?, ?, ?, ?) ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, sitioTuristicoDTO.getTurinomb());
					ps.setString(2, sitioTuristicoDTO.getTuridire());
					ps.setString(3, sitioTuristicoDTO.getTuriimag());
					ps.setInt(4, sitioTuristicoDTO.getUbiccodi().getUbiccodi());
					ps.setInt(5, sitioTuristicoDTO.getTurilike());
					ps.setString(6, sitioTuristicoDTO.getTuriface());
					ps.setString(7, sitioTuristicoDTO.getTuriurlx());
					ps.setString(8, sitioTuristicoDTO.getTuriinst());
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception SitioTuristicoRepositoryImpl crearSitioTuristico: " + e.toString());
			throw new Exception("Sitio turistico ya existe");
		}
	}

// -------------------------------------------------------------------------------------
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar los sitios turisticos unidos a una
	 *              ubicacion
	 */
	public Integer modificarSitioTuristico(SitioTuristicoDTO sitioTuristicoDTO) throws Exception {
		try {
			String SQL = "  UPDATE public.sitioturistico  "
					+ "	SET turinomb=?, turidire=?, turiimag=?, ubiccodi=?, turilike=?, "
					+ "     turiface=?, turiurlx=?, turiinst=?  " + "	WHERE turicodi=? ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, sitioTuristicoDTO.getTurinomb());
					ps.setString(2, sitioTuristicoDTO.getTuridire());
					ps.setString(3, sitioTuristicoDTO.getTuriimag());
					ps.setInt(4, sitioTuristicoDTO.getUbiccodi().getUbiccodi());
					ps.setInt(5, sitioTuristicoDTO.getTurilike());
					ps.setString(6, sitioTuristicoDTO.getTuriface());
					ps.setString(7, sitioTuristicoDTO.getTuriurlx());
					ps.setString(8, sitioTuristicoDTO.getTuriinst());
					ps.setInt(9, sitioTuristicoDTO.getTuricodi());
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception SitioTuristicoRepositoryImpl modificarSitioTuristico: " + e.toString());
			throw new Exception("Sitio turistico ya existe");
		}
	}

// -------------------------------------------------------------------------------------
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para inactivar los sitios turisticos
	 */
	public Integer inactivarSitioTuristico(SitioTuristicoDTO sitioTuristicoDTO) throws Exception {
		try {
			String SQL = "  UPDATE public.sitioturistico  " + "	SET turiesta=0  " + "	WHERE turicodi=? ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, sitioTuristicoDTO.getTuricodi());
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception SitioTuristicoRepositoryImpl inactivarSitioTuristico: " + e.toString());
			throw new Exception("Sitio turistico no existe para ser inactivado");
		}
	}

// -------------------------------------------------------------------------------------
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para poner like al sitio turistico
	 */
	public Integer likeSitioTuristico(SitioTuristicoDTO sitioTuristicoDTO) throws Exception {
		try {
			String SQL = "  UPDATE public.sitioturistico  " + "	SET turilike=?  " + "	WHERE turicodi=? ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, sitioTuristicoDTO.getTurilike());
					ps.setInt(2, sitioTuristicoDTO.getTuricodi());
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception SitioTuristicoRepositoryImpl likeSitioTuristico: " + e.toString());
			throw new Exception("Sitio turistico no existe");
		}
	}

}
