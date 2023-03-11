package com.proyecto;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.proyecto.entity.Categoria;

import com.proyecto.service.CategoriaService;

@SpringBootTest
class ProyectoDawiApplicationTests {

	@Autowired 
	CategoriaService sercate;
	
	@Test
	void contextLoads() {
		List<Categoria> categ= sercate.listarTodoscate();
		for (Categoria c : categ) {
			System.out.println("--"+c.getNombre());
		}
	}

}
