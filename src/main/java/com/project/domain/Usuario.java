package com.project.domain;

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
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private String documento;
	private String nombreUno;
	private String nombreDos;
	private String apellidoUno;
	private String apellidoDos;
	private String email;
	@JsonFormat(pattern = "yyyy-MM-dd", locale = "es-CO", timezone = "America/Lima")
	private Date fechaNac;
	private String celular;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", locale = "es-CO", timezone = "America/Lima")
	private Date fechaSys;
	private Integer estado;

}
