package com.proyecto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	//este metodo es la configuracion para 
	//trabajar con nuestro propio login
    //si no ponemos este metodo(por defecto nos sale el login de spring)
	@Bean
	public  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		//no vamos a autenticar en funcion a un formulario(login personalizado)
		/*http.authorizeHttpRequests((auth)-> auth.anyRequest().authenticated())
		.formLogin(form-> form.loginPage("/login")
		.permitAll().defaultSuccessUrl("/medicamento/lista"));*/
	
		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests((auth) -> {
			//vamos acceder ala ruta medicamento simpre y cuando su rol adm
			auth.anyRequest().authenticated();})
		  .formLogin(form -> {
			try {
						form.loginPage("/login")
								// si todo es correcto ingresamos a esta ruta
								.permitAll().defaultSuccessUrl("/inicio")
								// .failureUrl("/loginerrord")
								.and().logout()
								// .logoutSuccessUrl("/intranet");
								// .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
								.logoutSuccessUrl("/login?logout").permitAll();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
	    return http.build();	
	}
	
	/*
	@Bean
	public UserDetailsService userDetailsService(BCryptPasswordEncoder encoder) {
		//crear objeto de la InMemoryUserDetailsManager
		InMemoryUserDetailsManager manager=new InMemoryUserDetailsManager();
	//necesitamos encriptar el password para ello implemento el metodo BCryptPasswordEncoder
		manager.createUser(User.withUsername("luis").password(encoder.encode("123")).roles("USER").build());
		manager.createUser(User.withUsername("carlos").password(encoder.encode("456")).roles("ADMIN").build());
		
		return manager;
	} */


}
