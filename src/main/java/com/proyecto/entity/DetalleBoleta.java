package com.proyecto.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_detalle")
public class DetalleBoleta implements Serializable{
	
	@EmbeddedId
	private DetalleBoletaPK pk;
	
	public DetalleBoletaPK getPk() {
		return pk;
	}

	public void setPk(DetalleBoletaPK pk) {
		this.pk = pk;
	}

	@ManyToOne
	@JoinColumn(name="num_fact",referencedColumnName = "num_fact",insertable = false,updatable =false)
	private Boleta boleta;//ASOCI.
	
	//Relación MUCHOS  a UNO "Boleta"
	@ManyToOne
	@JoinColumn(name="codigo",referencedColumnName = "codigo",insertable = false,updatable =false)
	private Electrodomestico elec;//ASOCI.
	
	@Column(name = "cantidad")
	private int cantidad;
	
	@Column(name = "preciovta")
	private double precio;
	

	public Boleta getBoleta() {
		return boleta;
	}

	public void setBoleta(Boleta boleta) {
		this.boleta = boleta;
	}

	public Electrodomestico getElec() {
		return elec;
	}

	public void setElec(Electrodomestico elec) {
		this.elec = elec;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	

}
