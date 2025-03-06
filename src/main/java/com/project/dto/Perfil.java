package com.project.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Perfil implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idperfil;
	private String nombreperfil;
	private Integer estado;
	private String strEstado;

}
