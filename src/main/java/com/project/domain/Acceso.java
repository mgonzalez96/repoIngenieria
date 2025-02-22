package com.project.domain;

import java.io.Serializable;
import java.util.Date;

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
public class Acceso implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private Integer idAcceso;
	private Usuario documento;
	private String username;
	private Byte[] password;
	private Date fechaSys;

}
