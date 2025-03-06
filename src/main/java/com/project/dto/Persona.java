package com.project.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	private String idpersona;
	private String nombre1;
	private String nombre2;
	private String apellido1;
	private String apellido2;
	private String direccion;
	private String movil;
	private String correo_electronico;
	private Integer idperfil;
	private String usuario;
	private String contrasena;
	private Integer estado;
}
