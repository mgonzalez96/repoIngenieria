package com.project.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UbicacionDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer ubiccodi;
	private String ubicnomb;

}
