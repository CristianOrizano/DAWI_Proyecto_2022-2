package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.entity.Boleta;
import com.proyecto.entity.DetalleBoleta;

public interface BoletaRepository extends JpaRepository<Boleta, Integer>{

}
