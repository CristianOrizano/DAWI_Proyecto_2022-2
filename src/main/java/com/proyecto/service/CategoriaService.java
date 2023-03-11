package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entity.Categoria;
import com.proyecto.repository.CategoriaRepository;


@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public List<Categoria> listarTodoscate(){
		return repo.findAll();
	}
	
	public List<Categoria> listarcateporEmpre(int cod){
		return repo.buscarporEmpresa(cod);
	}
}
