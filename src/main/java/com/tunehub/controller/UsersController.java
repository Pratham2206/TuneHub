package com.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tunehub.entities.Song;
import com.tunehub.entities.Users;
import com.tunehub.services.SongService;
import com.tunehub.services.UsersService;

import jakarta.servlet.http.HttpSession;

//Adding the user information and checking the user if user is new then user account will be created
//else it will display "User already exists"
@Controller
public class UsersController {
	@Autowired
	UsersService service;
	@Autowired
	SongService songService;
	
	@PostMapping("/register")
	public String addUsers(@ModelAttribute Users user) {
		boolean userStatus=service.emailExists(user.getEmail());
		if(userStatus== false)
		{
			service.addUser(user);
			System.out.println("User added");
		}
		else
		{
			System.out.println("User already exists");
		}
		return "home";
	}
//In this the method will be check the validate mail if the mail is register by admin then it will
//go to admin home or else customerHome page
	@PostMapping("/validate")
	public String validate(@RequestParam("email")String email, 
			@RequestParam("password")String password,
	HttpSession session, Model model)
	{
		String role=service.getRole(email);
		if(service.validateUser(email,password)==true)
		{
			session.setAttribute("email",email);
			if(role.equals("admin"))
			{
				return "adminHome";
			}
			else
			{
				Users user=service.getUser(email);
				boolean userStatus=user.isPremium();
				
				List<Song> songsList=songService.fetchAllSongs();
				
				model.addAttribute("songs", songsList);
				
				model.addAttribute("isPremium",userStatus);
				
				return "customerHome";
			}
		}
		else
		{
			return"login";
		}
		}	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	
	}
	
}


















