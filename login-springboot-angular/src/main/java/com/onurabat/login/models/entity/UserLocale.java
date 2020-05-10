package com.onurabat.login.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.onurabat.login.models.entity.Role;

@Entity
@Table(name = "users")
public class UserLocale implements Serializable {

	@Id
	private Long id;

	@Column(nullable = false, length = 20)
	@NotEmpty
	private String name;

	@Column(nullable = false, length = 20)
	@NotEmpty
	private String surname;

	@Email
	@Column(name = "email", unique = true, nullable = false, length = 60)
	@NotEmpty
	private String email;

	@Column(nullable = false, length = 60)
	@NotEmpty
	private String password;
	
	@Column(nullable = false)
	private Boolean enabled;

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updated_at;

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date created_at;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "users_roles",
    joinColumns = { @JoinColumn(name = "users_id") },
    inverseJoinColumns = { @JoinColumn(name = "roles_id") })
	private Set<Role> role = new HashSet<>();
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companies_id", nullable = false)
    private Company company;
	
	private static final long serialVersionUID = 1L;

	public UserLocale() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserLocale [id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + ", password="
				+ password + ", enabled=" + enabled + ", updated_at=" + updated_at + ", created_at=" + created_at
				+ ", role=" + role + ", company=" + company + "]";
	}
}
