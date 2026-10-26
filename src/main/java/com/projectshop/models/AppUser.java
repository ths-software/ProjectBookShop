package com.projectshop.models;

import java.time.LocalDateTime;
import java.util.Objects;

import com.projectshop.security.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/*
 * 
 * @Temporal está deprecated, y parece que Date es demasiado antiguo, por eso lo está.
 * Cambiaremos por java.time en vez de java.util.Date
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "appUser")
public class AppUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String surName;

	/* Avatar desactivado, rehacer hashcode, equals y tostring

		private String avatar;
	 */

	
	/*	Antiguo y deprecated
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date suscriptionDate;
	*/
	
	@CreatedDate	// guarda automaticamente la fecha de creación
	private LocalDateTime suscriptionDate;

	@Column(unique = true)	// para que no haya duplicados
	@NotEmpty
	private String email;
	@NotEmpty
	private String password;

	private Roles role;

	
	public AppUser()
	{
		
	}
	
	public AppUser(String name, String surName, String email, String password, Roles role)
	{
		this.name = name;
		this.surName = surName;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	/*
	public String getAvatar() {
		return avatar;
	}
	 */

	/*
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	 */

	public LocalDateTime getSuscriptionDate() {
		return suscriptionDate;
	}

	public void setSuscriptionDate(LocalDateTime suscriptionDate) {
		this.suscriptionDate = suscriptionDate;
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

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		AppUser appUser = (AppUser) o;
		return id == appUser.id && Objects.equals(name, appUser.name) && Objects.equals(surName, appUser.surName) && Objects.equals(suscriptionDate, appUser.suscriptionDate) && Objects.equals(email, appUser.email) && Objects.equals(password, appUser.password) && role == appUser.role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, surName, suscriptionDate, email, password, role);
	}

	@Override
	public String toString() {
		return "AppUser{" +
				"id=" + id +
				", name='" + name + '\'' +
				", surName='" + surName + '\'' +
				", suscriptionDate=" + suscriptionDate +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", role=" + role +
				'}';
	}
}
