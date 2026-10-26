package com.projectshop.services;

import com.projectshop.models.AppUser;

import java.util.List;

public interface UserService {
	
	public AppUser userRegister(AppUser u);

	public AppUser userEdit(AppUser u);

	public void delete(Long id);

	public AppUser findById(long id);
	public AppUser emailFilter(String email);

	public List<AppUser> findAll();

	public List<AppUser> findByName(String name);

}
