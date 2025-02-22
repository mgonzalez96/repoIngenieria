package com.project.DTO;

import java.io.Serializable;
import java.util.Date;

import com.project.domain.Usuario;

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
public class AccesoDTO implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private Integer idAcceso;
	private Usuario documento;
	private String username;
	private Byte[] password;
	private Date fechaSys;

}
