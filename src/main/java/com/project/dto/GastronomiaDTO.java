package com.project.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GastronomiaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer gastcodi;
	private String gastnomb;
	private String gastdesc;
	private String gastimag;

}
