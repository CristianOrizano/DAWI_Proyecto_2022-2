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

import com.proyecto.entity.Cliente;
import com.proyecto.entity.Usuario;

import com.proyecto.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioService serUser;
	
	@RequestMapping("/lis")
	public String inicio(Model model) {
		
		List<Usuario> data= serUser.listarTodos();
		
		model.addAttribute("lista",data);
		
		return "CrudUsuario";
	}
	
	@RequestMapping("/grabar")
	public String grabar(Model model,@RequestParam("codigo") int cod,
						 @RequestParam("nombre") String nom,@RequestParam("apellido") String apell,
					     @RequestParam("fecha")String fecha,@RequestParam("login") String login,
					     @RequestParam("clave")String cla,
					     RedirectAttributes redirect) {
		try {
			//crear objeto
			Usuario m=new Usuario();
			m.setCodigo(cod);
			m.setNombre(nom);
			m.setApellido(apell);
			m.setFecha(new SimpleDateFormat("yyyy-MM-dd").parse(fecha));
			m.setLogin(login);
			m.setClave(cla);
			
			serUser.grabar(m);
			
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
		return "redirect:/usuario/lis";
	}
	
	
	
	@RequestMapping("/buscar")
	//al poner esto el valor de retorno del metodo se convierte en json
	@ResponseBody
	//convertir ese medicamento que retorna aun json
	public Usuario buscar(@RequestParam("codigo") int cod) {
		Usuario m= serUser.buscar(cod); 
		
		return m;
	}
	
	//metodo eliminar
			@RequestMapping("/eliminar")
			public String Eliminar(@RequestParam("codigo") int cod,RedirectAttributes redirect) {	
				try {
					serUser.eliminar(cod);
					redirect.addFlashAttribute("MENSAJE","Eliminado exitoso");
					
				} catch (Exception e) {
					redirect.addFlashAttribute("MENSAJE","error eliminar");
					System.out.println("error al eliminar"+e.getMessage());
				}	
				return "redirect:/usuario/lis";
			}
	
	
}
