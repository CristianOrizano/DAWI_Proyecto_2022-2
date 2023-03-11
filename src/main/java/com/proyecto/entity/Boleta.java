package com.proyecto.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_factura")
public class Boleta implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "num_fact")
	private int numeroBoleta;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_emi")
	private Date fechaEmision;
	
	@Column(name = "total")
	private double monto;
	
	//Relación MUCHOS a UNO "Usuario"
		@ManyToOne
		@JoinColumn(name = "cod_tra")
		private Usuario usuario;
		
		//Relación MUCHOS a UNO "Cliente"
		@ManyToOne
		@JoinColumn(name = "cod_cli")
		private Cliente cliente;
		
		//Relación UNO a MUCHOS "ConceptoHasBoleta"
		@OneToMany(mappedBy = "boleta")
		private List<DetalleBoleta> listaDetalleBol;

		public int getNumeroBoleta() {
			return numeroBoleta;
		}

		public void setNumeroBoleta(int numeroBoleta) {
			this.numeroBoleta = numeroBoleta;
		}

		public Date getFechaEmision() {
			return fechaEmision;
		}

		public void setFechaEmision(Date fechaEmision) {
			this.fechaEmision = fechaEmision;
		}

		public double getMonto() {
			return monto;
		}

		public void setMonto(double monto) {
			this.monto = monto;
		}

		public Usuario getUsuario() {
			return usuario;
		}

		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}

		public Cliente getCliente() {
			return cliente;
		}

		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}

		public List<DetalleBoleta> getListaDetalleBol() {
			return listaDetalleBol;
		}

		public void setListaDetalleBol(List<DetalleBoleta> listaDetalleBol) {
			this.listaDetalleBol = listaDetalleBol;
		}
	
		
		
	

}
