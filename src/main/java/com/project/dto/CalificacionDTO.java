package com.project.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalificacionDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer calicodi;
	private Date califech;
	private UsuarioDTO caliuser;
	private TipoCalificacionDTO tipocodi;
	private String caliobse;
	private GastronomiaDTO gastcodi;

}
