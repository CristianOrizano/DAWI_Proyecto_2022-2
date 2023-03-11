package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.entity.DetalleBoleta;
import com.proyecto.entity.Usuario;

public interface DetalleBoletaRepository extends JpaRepository<DetalleBoleta, Integer>{

	
	@Query("select de from DetalleBoleta de where de.boleta.numeroBoleta=?1")
	public List<DetalleBoleta> listadeta(int codi);
}
