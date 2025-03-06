package com.project.dto;

import java.io.Serializable;
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
	private String nombreperfil;
	private Integer estado;
	private String strEstado;
}
