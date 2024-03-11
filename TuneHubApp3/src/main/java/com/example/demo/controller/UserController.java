package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Songs;
import com.example.demo.entities.Users;
import com.example.demo.services.SongsService;
import com.example.demo.services.UsersService;

import jakarta.servlet.http.HttpSession;
@Controller
public class UserController{
	@Autowired
	UsersService userv;
	@Autowired
	SongsService songserv;
	@PostMapping("/register")
	public String addUser(@ModelAttribute Users user) //goes to addUser implemented method present in UserServiceImplementation class from interface of UserService.
	{
		boolean userstatus=userv.emailExists(user.getEmail());//by taking the help of emailExists implemented method present in UserServiceImplementation class we are going to validate the user email as registered as new user or not. 
		if(userstatus==false) {
			userv.addUser(user);
			return "registersuccess";//goes to registersuccess.html page
		}
		else {
		    return "registerfail";//goes to registerfail.html page
		}
	}
	
	@PostMapping("/login")
	public String validateUser(@RequestParam String email,@RequestParam String password,HttpSession session) 
	{
	    if(userv.validateUser(email,password) == true)//by taking the help of validateUser implemented method present in UserServiceImplemention class we are going to validate the user email and password for login successfully.
	    {
	    	session.setAttribute("email",email);
	    	if(userv.getRole(email).equals("admin"))//by taking the help of getRoll implemented method present in UserServiceImplementation calss we are going to validate the role as admin or user for opening the related page.
	    	{
	    	return "adminhome";//goes to adminhome.html page
	    	}
	    	else {
	    	return "customerhome";//goes to customerhome.html page
	    	}
	    }
	    else {
	    	return "loginfail";//goes to loginfail.html page
	    }
	}
	
	@GetMapping("/exploresongs")
	public String exploreSongs(HttpSession session,Model model) {
		String email=(String) session.getAttribute("email");
		Users user=userv.getUser(email);
		boolean userStatus=user.isPremium();
		if(userStatus==true) {
			List<Songs> songslist=songserv.fetchAllSongs();
			model.addAttribute("songslist",songslist);
			return "displaysongs";
		}
		else {
			return "payment";
		}
	}
	
}

