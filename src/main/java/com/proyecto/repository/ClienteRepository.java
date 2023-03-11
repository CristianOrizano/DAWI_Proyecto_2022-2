package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.entity.Cliente;
import com.proyecto.entity.Electrodomestico;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
