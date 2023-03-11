package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entity.Electrodomestico;
import com.proyecto.repository.ElectrodomesticoRepository;

@Service
public class ElectrodomesticoService {
	
	@Autowired
	private ElectrodomesticoRepository repo;
	
	//graba o actualiza
	public void grabar(Electrodomestico bean) {
		repo.save(bean);
	}
	public void eliminar(Integer cod) {
		repo.deleteById(cod);
	}
	public Electrodomestico buscar(Integer cod) {
		return repo.findById(cod).orElse(null);
	}
	public List<Electrodomestico> listarTodos(){
		return repo.findAll();
	}
	
	public void actualizarIMG(String nomAr,Integer cod) {
		repo.actualizarFoto(nomAr, cod);
	}
	
	//disminuir el stock
	public void actualizarstock(int stock,int codigo) {
		repo.actualizarStock(stock, codigo);
	}
	

}
