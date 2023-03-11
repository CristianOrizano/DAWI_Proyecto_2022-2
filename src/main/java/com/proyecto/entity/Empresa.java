package com.proyecto.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_Empresa")
public class Empresa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ide_empre")
	private Integer codigoempre;
	
	@Column(name="descripcion")
	private String nombre;
	
	@JsonIgnore
	@OneToMany(mappedBy = "empresa")//ASOC nombre tipo
	private List<Categoria> listacategoria;

	

	public Integer getCodigoempre() {
		return codigoempre;
	}

	public void setCodigoempre(Integer codigoempre) {
		this.codigoempre = codigoempre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Categoria> getListacategoria() {
		return listacategoria;
	}

	public void setListacategoria(List<Categoria> listacategoria) {
		this.listacategoria = listacategoria;
	}
	
	
	

}
