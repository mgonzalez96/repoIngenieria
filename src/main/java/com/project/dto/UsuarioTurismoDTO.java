package com.project.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioTurismoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer ustucodi;
	private Integer documento;
	private Integer turicodi;
	private Integer like;
}
