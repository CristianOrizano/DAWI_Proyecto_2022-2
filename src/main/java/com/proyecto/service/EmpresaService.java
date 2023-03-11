package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.proyecto.entity.Empresa;

import com.proyecto.repository.EmpresaRepository;

@Service
public class EmpresaService {
       
	@Autowired
	private EmpresaRepository repo;
	
	public List<Empresa> listarTodos(){
		return repo.findAll();
	}
}
