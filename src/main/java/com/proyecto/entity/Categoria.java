package com.proyecto.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_Categoria")
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ide_cat")
	private Integer codigocate;
	
	@Column(name="descripcion")
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name="ide_empre")
	private Empresa empresa;//ASOC.
	
	@JsonIgnore
	@OneToMany(mappedBy = "cate")//ASOC nombre tipo
	private List<Electrodomestico> listaElectro;
	

	public Integer getCodigocate() {
		return codigocate;
	}

	public void setCodigocate(Integer codigocate) {
		this.codigocate = codigocate;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Electrodomestico> getListaElectro() {
		return listaElectro;
	}

	public void setListaElectro(List<Electrodomestico> listaElectro) {
		this.listaElectro = listaElectro;
	}
	
	

}
