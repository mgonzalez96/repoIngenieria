package com.project.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer hotecodi;
	private String hotenomb;
	private String hotedesc;
	private UbicacionDTO ubiccodi = new UbicacionDTO();
	private Integer hoteesta;
	private String estado;

}
