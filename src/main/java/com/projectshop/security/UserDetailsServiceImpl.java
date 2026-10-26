package com.projectshop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projectshop.models.AppUser;
import com.projectshop.repositories.UserRepository;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	
	/*
	 * Usaremos esta clase junto con springSecurity y en SecurityConfig para autenticar y hacer login
	 * 
	 * el passwordEncoder en SecurityConfig tiene el password introducida por el user en el formulario,
	 * 	es comprobada en el encoder
	 * 
	 * En el UserServiceDB añadimos un passwordEncoder para que cifre las pass al ser añadidas a la db.
	 */

	@Autowired
	UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		AppUser user = repository.findFirstByEmail(username);
		
		UserBuilder builder = null;
		
		if(user != null)
		{
			builder = User.withUsername(username);
			builder.disabled(false);
			builder.password(user.getPassword());
			builder.authorities(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
			
		}
		else
		{
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		
		return builder.build();
	}

}
