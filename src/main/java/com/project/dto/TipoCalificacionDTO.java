package com.project.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoCalificacionDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer tipocodi;
	private Integer tiponomb;
	//private String tiponomb;


}
