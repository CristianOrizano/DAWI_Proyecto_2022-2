package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entity.Cliente;
import com.proyecto.entity.Electrodomestico;
import com.proyecto.repository.ClienteRepository;
import com.proyecto.repository.ElectrodomesticoRepository;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	//graba o actualiza
	public void grabar(Cliente bean) {
		repo.save(bean);
	}
	
	//eliminar
	public void eliminar(Integer cod) {
		repo.deleteById(cod);
	}
	//buscar para editar
	public Cliente buscar(Integer cod) {
		return repo.findById(cod).orElse(null);
	}
	
	//listado
	public List<Cliente> listarTodos(){
		return repo.findAll();
	}
	

}
