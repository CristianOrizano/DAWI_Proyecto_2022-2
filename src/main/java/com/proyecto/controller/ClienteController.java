package com.proyecto.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.entity.Categoria;
import com.proyecto.entity.Cliente;
import com.proyecto.entity.Electrodomestico;
import com.proyecto.service.ClienteService;


@Controller
@RequestMapping("/cliente")
public class ClienteController {
	@Autowired
	ClienteService serCli;
	
	@RequestMapping("/lis")
	public String inicio(Model model) {
		
		List<Cliente> data= serCli.listarTodos();
		
		model.addAttribute("lista",data);
		
		return "CrudCliente";
	}
	
	
	@RequestMapping("/grabar")
	public String grabar(Model model,@RequestParam("codigo") int cod,
						 @RequestParam("Sexo") String sexo,@RequestParam("nombre") String nom,
					     @RequestParam("apellido")String ape,@RequestParam("dni") String dni,
					     @RequestParam("fecha")String fech,
					     RedirectAttributes redirect) {
		try {
			//crear objeto
			Cliente m=new Cliente();
			m.setCodigo(cod);
			m.setNombre(nom);
			m.setApellido(ape);
			m.setDni(dni);
			m.setFecha(new SimpleDateFormat("yyyy-MM-dd").parse(fech));
		    System.out.println("Fecha=== "+m.getFecha());
			m.setSexo(sexo);
			
					
			serCli.grabar(m);
			
			if(cod==0) {
				//crear atributo
				//este atributo solo va a servir cuando este redireccionando a una ruta "redirect:/medicamento/lis"
				//porq luego desaparece cuando lo lee en la pagina
				redirect.addFlashAttribute("MENSAJE","Registro exitoso");
			}else {
				//crear atributo
				//este atributo solo va a servir cuando este redireccionando a una ruta "redirect:/medicamento/lis"
				//porq luego desaparece cuando lo lee en la pagina
				redirect.addFlashAttribute("MENSAJE","Actualizado exitoso");
			}
			
		} catch (Exception e) {
			redirect.addFlashAttribute("MENSAJE","error al grabar");
			System.out.println("error al grabar: "+e.getMessage());
		}
		//cuando grabe invoque al controlador y su metodo listar
		return "redirect:/cliente/lis";
	}
	
	
	
	@RequestMapping("/buscar")
	//al poner esto el valor de retorno del metodo se convierte en json
	@ResponseBody
	//convertir ese medicamento que retorna aun json
	public Cliente buscar(@RequestParam("codigo") int cod) {
		Cliente m= serCli.buscar(cod); 
		
		return m;
	}
	
	//metodo eliminar
			@RequestMapping("/eliminar")
			public String Eliminar(@RequestParam("codigo") int cod,RedirectAttributes redirect) {	
				try {
					serCli.eliminar(cod);
					redirect.addFlashAttribute("MENSAJE","Eliminado exitoso");
					
				} catch (Exception e) {
					redirect.addFlashAttribute("MENSAJE","error eliminar");
					System.out.println("error al eliminar"+e.getMessage());
				}	
				return "redirect:/cliente/lis";
			}
		
	

}
