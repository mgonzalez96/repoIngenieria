package com.project.DTO;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccesoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idacceso;
	private UsuarioDTO documento = new UsuarioDTO();
	private String username;
	private String password;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", locale = "es-CO", timezone = "America/Lima")
	private Date fechasys;
	private Integer estado;
	//------Add por lógica---------------//
	private String asunto;
	private String descripcion;

}
