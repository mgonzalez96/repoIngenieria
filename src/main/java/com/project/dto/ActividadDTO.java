package com.project.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActividadDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer acticodi;
	private String actinomb;
	private String actidesc;
	private String actiimag;
	private TipoEventoDTO evencodi = new TipoEventoDTO();
	private UbicacionDTO ubiccodi = new UbicacionDTO();
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", locale = "es-CO", timezone = "America/Lima")
	private Date actifein;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", locale = "es-CO", timezone = "America/Lima")
	private Date actifefi;
	private Integer actiesta;
	private String actiface;
	private String actiurlx;
	private String actiinst;
	private String actiestaStr;

}
