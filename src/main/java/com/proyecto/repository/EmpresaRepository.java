package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.entity.Categoria;
import com.proyecto.entity.Empresa;

public interface EmpresaRepository  extends JpaRepository<Empresa, Integer> {
	
	
}
