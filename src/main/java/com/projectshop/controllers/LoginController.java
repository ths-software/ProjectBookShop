package com.projectshop.controllers;

import com.projectshop.security.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.projectshop.models.AppUser;
import com.projectshop.services.UserService;
import com.projectshop.storage.StorageService;

@Controller
public class LoginController {
	
	@Autowired
	UserService userService;

	/*
	@Autowired
	StorageService storageService;
	*/
	
	@GetMapping("/")
	public String getWelcomePage()
	{
		return "redirect:/public/";
	}
	
	@GetMapping("/auth/login")
	public String getLoginPage()
	{
		return "login";
	}

	@GetMapping("/auth/signup")
	public String getRegisterPage(Model model)
	{
		model.addAttribute("user", new AppUser());
		return "signingForm";
	}


	@PostMapping("/auth/register")
	public String registerUser(@Validated @ModelAttribute("user") AppUser user, BindingResult bindingResult, Model model)
	{
		if(bindingResult.hasErrors())
		{
			return "signingForm";
		}

		try {
			user.setRole(Roles.USER);
			userService.userRegister(user);
        }
		catch (Exception e)
		{
			bindingResult.rejectValue("email","email.exists", e.getMessage());
			return "signingForm";
		}

		return "redirect:/auth/login";
	}

	/*
	@PostMapping("/auth/register")
	public String registerUser(Model model, AppUser user,
			@RequestParam("file") MultipartFile file)
	{
		
		if(!file.isEmpty())
		{
			String image = storageService.store(file);
			user.setAvatar(MvcUriComponentsBuilder
					.fromMethodName(FilesController.class, "serveFile", image).build().toUriString());
		}
		
		userService.userRegister(user);
		return "redirect:/auth/login";
	}
	 */

}
