package com.proyecto.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.proyecto.entity.Usuario;
import com.proyecto.repository.UsuarioRepository;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails obj=null;	
		//incocamos al metodo para loguiarnos
		Usuario bean=repo.iniciarSesion(username);

		Set<GrantedAuthority>rol=new HashSet<GrantedAuthority>();
		//aqui sacamos el nombre del rol
		rol.add(new SimpleGrantedAuthority(bean.getRol().getDescripcion()));
		
		obj =new User(username,bean.getClave(),rol);
		
		return obj;
	}

}
