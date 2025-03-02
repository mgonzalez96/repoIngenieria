package com.project.dto;

import java.io.Serializable;
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
	private Integer evencodi;

}
