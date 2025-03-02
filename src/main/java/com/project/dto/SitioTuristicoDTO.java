package com.project.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SitioTuristicoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer turicodi;
	private String turinomb;
	private String turidire;
	private String turiimag;
	private Integer ubicodi;

}
