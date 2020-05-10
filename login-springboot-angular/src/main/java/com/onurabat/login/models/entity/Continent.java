package com.onurabat.login.models.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity 
@Table(name = "continents")
public class Continent implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String code;
	
	@Column(nullable = false, length = 15)
	@NotEmpty
    private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
}
