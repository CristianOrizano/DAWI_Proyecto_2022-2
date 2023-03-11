package com.proyecto.entity;

public class Detalle {
	
	public int codigo,cantidad;
	public String descripcion,nomAr;
	public double precio,importe;
	
	
    public String getNomAr() {
		return nomAr;
	}
	public void setNomAr(String nomAr) {
		this.nomAr = nomAr;
	}
    
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
    
    
    
}
