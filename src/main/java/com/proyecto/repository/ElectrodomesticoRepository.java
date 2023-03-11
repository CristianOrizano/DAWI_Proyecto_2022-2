package com.proyecto.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.entity.Electrodomestico;

public interface ElectrodomesticoRepository extends JpaRepository<Electrodomestico, Integer> {
	
	@Transactional
	@Modifying
	@Query ("update Electrodomestico e set e.nombreArchivo=?1 where e.codigo=?2")
	public void actualizarFoto(String nomAr,Integer cod);
	
	@Transactional
	@Modifying
	@Query ("update Electrodomestico e set e.stock= e.stock - ?1 where e.codigo=?2")
	public void actualizarStock(int can,int codigo);
	
	
}
