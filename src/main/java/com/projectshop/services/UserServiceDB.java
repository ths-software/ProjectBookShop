package com.projectshop.services;

import com.projectshop.security.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.projectshop.models.AppUser;
import com.projectshop.repositories.UserRepository;

import java.util.List;

@Service
@Primary
public class UserServiceDB implements UserService {
	
	@Autowired
	UserRepository repository;
	
	@Autowired
	PasswordEncoder passwordEncoder; // hay que encriptar la pass para que el springSec compruebe las pass. correctamente
	// inyectamos la interfaz de PasswordEncoder

	@Override
	public AppUser userRegister(AppUser u) {
		u.setPassword(passwordEncoder.encode(u.getPassword()));

		if(repository.findFirstByEmail(u.getEmail()) != null)
		{
			throw new IllegalArgumentException("El usuario ya existe");
		}

		repository.save(u);
		return u;
	}

	@Override
	public AppUser userEdit(AppUser u) {

		repository.save(u);
		return u;
	}

	@Override
	public void delete(Long id) {

		AppUser u = findById(id);

		if(u.getRole() != Roles.OWNER)
		{
			repository.deleteById(id);
		}


	}


	@Override
	public AppUser emailFilter(String email) {
		
		return repository.findFirstByEmail(email);
	}

	@Override
	public List<AppUser> findAll() {
		return repository.findAll();
	}

	@Override
	public List<AppUser> findByName(String name) {
		return repository.findByNameContainsIgnoreCase(name);
	}


	@Override
	public AppUser findById(long id) {
		
		return repository.findById(id).orElse(null);
	}

}
