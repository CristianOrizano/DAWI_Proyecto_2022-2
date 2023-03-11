package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.entity.Cliente;
import com.proyecto.entity.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

}
