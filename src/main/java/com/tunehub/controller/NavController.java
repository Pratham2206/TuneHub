package com.tunehub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController 
{
	//Mapping between the login page
	@GetMapping("/login")
	public String login()
	{
		return "login";
	}
	
	//Mapping between the registration page
	@GetMapping("/registration")
	public String  registration()
	{
		return "registration";
	}
	
	//Mapping between the newSong page
	@GetMapping("/newSong")
	public String  newSong()
	{
		return "newSong";
	}
	
	//Mapping between the createPlaylist page
	@GetMapping("/addPlaylist")
	public String createPlaylist() {
		return "addPlaylist";
	}
}
