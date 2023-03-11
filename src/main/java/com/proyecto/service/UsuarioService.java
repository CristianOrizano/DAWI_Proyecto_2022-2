package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entity.Electrodomestico;
import com.proyecto.entity.Enlace;
import com.proyecto.entity.Usuario;
import com.proyecto.repository.ElectrodomesticoRepository;
import com.proyecto.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repo;
	
	//graba o actualiza
	public void grabar(Usuario bean) {
		repo.save(bean);
	}
	public void eliminar(Integer cod) {
		repo.deleteById(cod);
	}
	public Usuario buscar(Integer cod) {
		return repo.findById(cod).orElse(null);
	}
	public List<Usuario> listarTodos(){
		return repo.findAll();
	}
	
	
	
	public Usuario loginUsuario(String vLogin) {
		return repo.iniciarSesion(vLogin);
	}
	public List<Enlace> enlacesDelUsuario(int rol){
		return repo.TraerEnlaceUsuario(rol);
	}
	

}
