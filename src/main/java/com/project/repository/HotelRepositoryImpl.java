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
import com.project.dto.HotelDTO;

@Repository
public class HotelRepositoryImpl extends JdbcDaoSupport {

	public HotelRepositoryImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para crear los hoteles
	 */
	public Integer crearHotel(HotelDTO hotelDTO) throws Exception {
		try {
			String SQL = " INSERT INTO public.hotel( " + "	hotecodi, hotenomb, hotedesc, ubiccodi, hoteesta) "
					+ "	VALUES (nextval('sec_hotel'), ?, ?, ?, 1) ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, hotelDTO.getHotenomb());
					ps.setString(2, hotelDTO.getHotedesc());
					ps.setInt(3, hotelDTO.getUbiccodi().getUbiccodi());
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception HotelRepositoryImpl crearHotel: " + e.toString());
			throw new Exception("Hotel ya existe");
		}
	}

// -------------------------------------------------------------------------------------
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para modificar los hoteles registrados
	 */
	public Integer modificarHotel(HotelDTO hotelDTO) throws Exception {
		try {
			String SQL = " UPDATE public.hotel  " + "	SET hotenomb=?, hotedesc=?, ubiccodi=?, hoteesta=?  "
					+ "	WHERE hotecodi=? ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, hotelDTO.getHotenomb());
					ps.setString(2, hotelDTO.getHotedesc());
					ps.setInt(3, hotelDTO.getUbiccodi().getUbiccodi());
					ps.setInt(4, hotelDTO.getHoteesta());
					ps.setInt(5, hotelDTO.getHotecodi());
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception HotelRepositoryImpl modificarHotel: " + e.toString());
			throw new Exception("Hotel no existe");
		}
	}

// -------------------------------------------------------------------------------------
	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para inactivar un hotel por codigo
	 */
	public Integer cambiarEstadoHotel(HotelDTO hotelDTO) throws Exception {
		try {
			String SQL = " UPDATE public.hotel  " + "	SET hoteesta= ?  " + "	WHERE hotecodi=? ";

			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, hotelDTO.getHoteesta());
					ps.setInt(2, hotelDTO.getHotecodi());
				}
			};
			return getJdbcTemplate().update(SQL, setter);
		} catch (Exception e) {
			System.err.println("Exception HotelRepositoryImpl cambiarEstadoHotel: " + e.toString());
			throw new Exception("Hotel no existe para cambio de estado");
		}
	}
//--------------------------------------------------------------------------------------

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar los hoteles pasando el estado
	 */
	public List<HotelDTO> consultaHotelByEstado(HotelDTO hotel) throws Exception {
		try {
			String SQL = " SELECT h.hotecodi, h.hotenomb, h.hotedesc,     "
					+ "       u.ubiccodi, u.ubicnomb, h.hoteesta,    " + "	   case when h.hoteesta = 1    "
					+ "	   then 'Activo'    " + "	   else 'Inactivo'    " + "	   end estado    "
					+ " FROM public.hotel h, public.ubicacion u    " + " WHERE u.ubiccodi = h.ubiccodi    "
					+ " AND h.hoteesta = ? ";
			PreparedStatementSetter setter = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, hotel.getHoteesta());
				}
			};
			return getJdbcTemplate().query(SQL, setter, consultaHotelByEstadoRowMapper);
		} catch (Exception e) {
			System.err.println("Exception HotelRepositoryImpl consultaHotelByEstado: " + e.toString());
			throw new Exception("No existen hoteles");
		}
	}

	private RowMapper<HotelDTO> consultaHotelByEstadoRowMapper = new RowMapper<HotelDTO>() {
		@Override
		public HotelDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			HotelDTO hotel = null;
			try {
				hotel = new HotelDTO();
				hotel.setHotecodi(rs.getInt("hotecodi"));
				hotel.setHotenomb(rs.getString("hotenomb"));
				hotel.setHotedesc(rs.getString("hotedesc"));
				hotel.getUbiccodi().setUbiccodi(rs.getInt("ubiccodi"));
				hotel.getUbiccodi().setUbicnomb(rs.getString("ubicnomb"));
				hotel.setHoteesta(rs.getInt("hoteesta"));
				hotel.setEstado(rs.getString("estado"));
			} catch (Exception e) {
				System.err.println("Exception HotelRepositoryImpl consultaHotelByEstado_1: " + e.toString());
			}
			return hotel;
		}
	};

}
