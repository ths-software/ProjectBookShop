package com.projectshop.models;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Buy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	@CreatedDate
	private LocalDateTime buyDate;
	
	// en el html en vez de #dates, usamos el moderno para LocalDateTime: #temporals
	
	/*
	 * "Muchos a uno" Es una anotación de base de datos, define la relación entre entidades.
	 * 
	 * Dice: Muchas entidades de esta clase están asociadas a un solo user.
	 */
	
	@ManyToOne
	private AppUser propietary;

	private BuyState buyState;
	
	
	public Buy()
	{
		buyState = BuyState.ACEPTADO;
	}
	public Buy(AppUser propietary)
	{
		buyState = BuyState.ACEPTADO;
		this.propietary = propietary;
	}

	public Buy(AppUser propietary,BuyState buyState)
	{
		this.buyState = buyState;
		this.propietary = propietary;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public LocalDateTime getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(LocalDateTime buyDate) {
		this.buyDate = buyDate;
	}
	public AppUser getPropietary() {
		return propietary;
	}
	public void setPropietary(AppUser propietary) {
		this.propietary = propietary;
	}

	public BuyState getBuyState() {
		return buyState;
	}

	public void setBuyState(BuyState buyState) {
		this.buyState = buyState;
	}


	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Buy buy = (Buy) o;
		return id == buy.id && Objects.equals(buyDate, buy.buyDate) && Objects.equals(propietary, buy.propietary) && buyState == buy.buyState;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, buyDate, propietary, buyState);
	}

	@Override
	public String toString() {
		return "Buy{" +
				"id=" + id +
				", buyDate=" + buyDate +
				", propietary=" + propietary +
				", buyState=" + buyState +
				'}';
	}
}
