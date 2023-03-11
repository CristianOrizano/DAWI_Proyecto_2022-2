package com.proyecto.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DetalleBoletaPK implements Serializable {
	
	@Column(name = "num_fact")
	private int numeroBoleta;
	
	@Column(name = "codigo")
	private int codigoElec;
	
	
	public int getNumeroBoleta() {
		return numeroBoleta;
	}
	public void setNumeroBoleta(int numeroBoleta) {
		this.numeroBoleta = numeroBoleta;
	}
	public int getCodigoElec() {
		return codigoElec;
	}
	public void setCodigoElec(int codigoElec) {
		this.codigoElec = codigoElec;
	}
	@Override
	public int hashCode() {
		return Objects.hash(codigoElec, numeroBoleta);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetalleBoletaPK other = (DetalleBoletaPK) obj;
		return codigoElec == other.codigoElec && numeroBoleta == other.numeroBoleta;
	}
	
	
	
}
