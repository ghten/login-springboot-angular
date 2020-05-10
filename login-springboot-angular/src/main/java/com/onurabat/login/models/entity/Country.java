package com.onurabat.login.models.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity 
@Table(name = "countries")
public class Country implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	@Column(nullable = false, columnDefinition="char(2)")
	@NotEmpty
    private String code;

	@Column(nullable = false, length = 50)
	@NotEmpty
    private String name;

	@Column(name = "native",nullable = false, length = 50)
	@NotEmpty
    private String original;

	@Column(nullable = false, length = 15)
    private String phone;
	
	@Column(nullable = false, length = 50)
	@NotEmpty
    private String capital;
	
	@Column(nullable = false)
	private Boolean ue;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("continentId")
    @JoinColumn(name = "continents_code", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Continent continent;
    
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public Boolean getUe() {
		return ue;
	}

	public void setUe(Boolean ue) {
		this.ue = ue;
	}

	public Continent getContinent() {
		return continent;
	}

	public void setContinent(Continent continent) {
		this.continent = continent;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", code=" + code + ", name=" + name + ", original=" + original + ", phone=" + phone
				+ ", capital=" + capital + ", ue=" + ue + ", continent=" + continent + "]";
	}
}
