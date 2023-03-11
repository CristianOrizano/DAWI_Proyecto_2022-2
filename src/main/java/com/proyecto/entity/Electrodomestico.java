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

@Entity
@Table(name = "tb_Electrodomesticos")
public class Electrodomestico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codigo")
	private Integer codigo;
	
	@ManyToOne
	@JoinColumn(name="ide_cat")
	private Categoria cate;//ASOC.
	
	@Column(name="descripcion")
	private String nombre;
	
	@Column(name="stock")
	private int stock;
	
	@Column(name="precio")
	private double prec;
	
	@Column(name="Marca")
	private String marca;
	
	@Column(name="estado")
	private int estado;
	
	@Column(name="nom_archivo")
	private String nombreArchivo;
		
	//Relación UNO a MUCHOS ""
	@OneToMany(mappedBy = "elec")
	private List<DetalleBoleta> listaDetalleBol;
	
	
	
	

	public String getNombreArchivo() {
		return nombreArchivo;
	}


	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}



	public Integer getCodigo() {
		return codigo;
	}

	
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Categoria getCate() {
		return cate;
	}

	public void setCate(Categoria cate) {
		this.cate = cate;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrec() {
		return prec;
	}

	public void setPrec(double prec) {
		this.prec = prec;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	

	

	
	
}
