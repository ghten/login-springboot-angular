package com.onurabat.login.models.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity 
@Table(name = "langs")
public class Lang implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	@Column(nullable = false, length = 2)
	@NotEmpty
    private String code;

	@Column(nullable = false, length = 5)
    private String long_code;
	
	@Column(nullable = false, length = 50)
	@NotEmpty
    private String name;

	@Column(name = "native",nullable = false, length = 50)
	@NotEmpty
    private String original;

	@Column(nullable = false)
	@NotEmpty
	private Boolean rtl;
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLong_code() {
		return long_code;
	}

	public void setLong_code(String long_code) {
		this.long_code = long_code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public Boolean getRtl() {
		return rtl;
	}

	public void setRtl(Boolean rtl) {
		this.rtl = rtl;
	}
}
