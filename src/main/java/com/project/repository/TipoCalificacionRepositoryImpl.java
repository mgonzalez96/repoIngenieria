package com.project.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import com.project.dto.TipoCalificacionDTO;

@Repository
public class TipoCalificacionRepositoryImpl extends JdbcDaoSupport {

	public TipoCalificacionRepositoryImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	/**
	 * @Usuario Mariana Acevedo
	 * @Descripcion Método para listar los tipos de calificaciones
	 */
	public List<TipoCalificacionDTO> consultaAllTipoCalificacion() throws Exception {
		try {
			String SQL = " SELECT tipocodi, tiponomb FROM public.tipocalificacion ";
			return getJdbcTemplate().query(SQL, consultaAllTipoCalificacionRowMapper);
		} catch (Exception e) {
			System.err.println("Exception TipoCalificacionRepositoryImpl consultaAllTipoCalificacion: " + e.toString());
			throw new Exception("No existen tipos de calificacion");
		}
	}

	private RowMapper<TipoCalificacionDTO> consultaAllTipoCalificacionRowMapper = new RowMapper<TipoCalificacionDTO>() {
		@Override
		public TipoCalificacionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			TipoCalificacionDTO tipoDto = null;
			try {
				tipoDto = new TipoCalificacionDTO();
				tipoDto.setTipocodi(rs.getInt("tipocodi"));
				tipoDto.setTiponomb(rs.getInt("tiponomb"));
			} catch (Exception e) {
				System.err.println(
						"Exception TipoCalificacionRepositoryImpl consultaAllTipoCalificacion_1: " + e.toString());
			}
			return tipoDto;
		}
	};

}
