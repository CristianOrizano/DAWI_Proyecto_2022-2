package com.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.proyecto.entity.Enlace;
import com.proyecto.entity.Usuario;
import com.proyecto.service.UsuarioService;

@SessionAttributes({"ENLACES","CODIGOUSUARIO","Nombre","ROL"})
@Controller
public class UserController {
	
	@Autowired
	private UsuarioService servicio;
        
	@RequestMapping("/login")
	public String login(Model model){
		
		
		return "login";
	}
	
	@RequestMapping("/inicio")
	public String intranet(Authentication  auth,Model model){
		       //obtener nombre del usuario
				String vLogin=auth.getName(); //nombre usuario que se loguio
				//invoco al metodo login para que me traiga el usuario
				Usuario u=servicio.loginUsuario(vLogin);
		   //una ves me devuelva el usuario segun nombre(saco el codigorol de ese nombre usuario)
				//luego lo mando como parametro para que traiga los enlaces del rol
				List<Enlace> lista=servicio.enlacesDelUsuario(u.getRol().getCodrol());
				//envio la lista de enlaces como atributoSession almacenado en ENLACES(ses)
				model.addAttribute("ENLACES",lista);
				model.addAttribute("Nombre",u.getNombre()+" "+u.getApellido());
				model.addAttribute("ROL",u.getRol().getDescripcion());
				model.addAttribute("CODIGOUSUARIO",u.getCodigo());
		
		return "inicio";
	}
	


}
