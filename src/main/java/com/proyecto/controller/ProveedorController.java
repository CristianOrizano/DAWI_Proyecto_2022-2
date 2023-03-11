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
import com.proyecto.entity.Proveedor;
import com.proyecto.service.ClienteService;
import com.proyecto.service.ProveedorService;

@Controller
@RequestMapping("/proveedor")
public class ProveedorController {
	
	@Autowired
	ProveedorService serpro;
	
	@RequestMapping("/lis")
	public String inicio(Model model) {
		
		List<Proveedor> data= serpro.listarTodos();
		
		model.addAttribute("lista",data);
		
		return "CrudProveedor";
	}
	
	@RequestMapping("/grabar")
	public String grabar(Model model,@RequestParam("codigo") int cod,
						@RequestParam("nombre") String nom,
					     @RequestParam("apellido")String ape,@RequestParam("dni") String dni,
					     @RequestParam("direccion")String dire, @RequestParam("fono")int fono,
					     RedirectAttributes redirect) {
		try {
			//crear objeto
			Proveedor pro=new Proveedor();
			pro.setCodigo(cod);
			pro.setNombre(nom);
			pro.setApellido(ape);
			pro.setDni(dni);
			pro.setDireccion(dire);
			pro.setFono(fono);
		  
			serpro.grabar(pro);
			
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
		return "redirect:/proveedor/lis";
	}
	
	
	@RequestMapping("/buscar")
	//al poner esto el valor de retorno del metodo se convierte en json
	@ResponseBody
	//convertir ese medicamento que retorna aun json
	public Proveedor buscar(@RequestParam("codigo") int cod) {
		Proveedor m= serpro.buscar(cod); 
		
		return m;
	}
	
	//metodo eliminar
			@RequestMapping("/eliminar")
			public String Eliminar(@RequestParam("codigo") int cod,RedirectAttributes redirect) {	
				try {
					serpro.eliminar(cod);
					redirect.addFlashAttribute("MENSAJE","Eliminado exitoso");
					
				} catch (Exception e) {
					redirect.addFlashAttribute("MENSAJE","error eliminar");
					System.out.println("error al eliminar"+e.getMessage());
				}	
				return "redirect:/proveedor/lis";
			}
	

}
