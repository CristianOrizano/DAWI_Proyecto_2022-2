package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entity.Cliente;
import com.proyecto.entity.Proveedor;
import com.proyecto.repository.ClienteRepository;
import com.proyecto.repository.ProveedorRepository;

@Service
public class ProveedorService {
	
	@Autowired
	private ProveedorRepository repo;
	
	//graba o actualiza
	public void grabar(Proveedor bean) {
		repo.save(bean);
	}
	
	//eliminar
	public void eliminar(Integer cod) {
		repo.deleteById(cod);
	}
	//buscar para editar
	public Proveedor buscar(Integer cod) {
		return repo.findById(cod).orElse(null);
	}
	
	//listado
	public List<Proveedor> listarTodos(){
		return repo.findAll();
	}

}
