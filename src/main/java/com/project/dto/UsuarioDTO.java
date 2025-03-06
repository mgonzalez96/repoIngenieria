package com.project.dto;

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
public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long documento;
	private String nombreuno;
	private String nombredos;
	private String apellidouno;
	private String apellidodos;
	private String correo;
	@JsonFormat(pattern = "yyyy-MM-dd", locale = "es-CO", timezone = "America/Lima")
	private Date fechanac;
	private Long celular;
	private String usuario;
	private String contrasena;
	private Integer estado;
	private AccesoDTO idacceso = new AccesoDTO();
	private String asunto;
	private String descripcion;

}
