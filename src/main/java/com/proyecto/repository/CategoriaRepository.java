package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.entity.Categoria;

public interface CategoriaRepository  extends JpaRepository<Categoria, Integer> {
	//hql==>manejo de select
	//?1==>parametro. ordinal
	//:nom==>para. nombrado
//tp alias de tipo medicamento
	@Query("select c from Categoria c where c.empresa.codigoempre=?1")
	public List<Categoria> buscarporEmpresa(int codLab);

}
