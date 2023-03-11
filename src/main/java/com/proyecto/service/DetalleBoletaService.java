package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entity.DetalleBoleta;
import com.proyecto.entity.Electrodomestico;
import com.proyecto.repository.DetalleBoletaRepository;
import com.proyecto.repository.ElectrodomesticoRepository;

@Service
public class DetalleBoletaService {
	
	@Autowired
	private DetalleBoletaRepository repo;
	
	public List<DetalleBoleta> listarDeta(){
		return repo.findAll();
	}
	
	public List<DetalleBoleta> listarDetallesfi(int cod){
		return repo.listadeta(cod);
	}

}
