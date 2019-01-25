package com.andy.beltexam.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.andy.beltexam.models.Show;
import com.andy.beltexam.models.User;
import com.andy.beltexam.services.ShowService;
import com.andy.beltexam.services.UserService;
import com.andy.beltexam.validators.UserValidator;

@Controller
public class Users {
	
    private final UserService userService;
    private final UserValidator userValidator;
    private final ShowService showService;
    
    public Users(UserService userService, UserValidator userValidator, ShowService showService) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.showService = showService;
    }
    
    // <---------- DISPLAY LOGIN AND REGISTRATION PAGE ---------->
    @RequestMapping("/")
    public String registerForm(@ModelAttribute("user") User user, HttpSession session) {
  	// redirect user to dashboard if they are already logged in.
  	Long userId = (Long) session.getAttribute("userId");
	  	if(userId != null) {
	  		return "redirect:/shows";
	  	}
	  	return "index.jsp";
    }
    
    // <---------- POST REQUEST TO CREATE A USER USING FORM:FORM ---------->
    @RequestMapping(value="/users", method=RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
    	// validations for email and password are implemented with "@Valid" and "BindingResult result" (based on validations specified in User.java)
    	// the code below checks our custom validation for password and password confirmation (based on code written in UserValidator and messages.properties)
    	userValidator.validate(user, result);
		if(result.hasErrors()) {
			return "index.jsp";
		} else {
			User u = userService.registerUser(user);
			session.setAttribute("userId",  u.getId());;
			return "redirect:/shows";
		}
    }
    
    // <---------- POST REQUEST TO LOGIN A USER USING NORMAL FORM ---------->
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session, @ModelAttribute("user") User user) {
    	// checks if the email and password credentials are valid
    	boolean isAuthenticated = userService.authenticateUser(email, password);
    	if(isAuthenticated) {
    		// if valid, save the user's id into session
    		User u = userService.findByEmail(email);
    		session.setAttribute("userId", u.getId());
    		return "redirect:/shows";
    	} else {
    		model.addAttribute("error", "Invalid Credentials. Please try again.");
    		return "index.jsp";
    	}
    }
    
    // <---------- DISPLAY DASHBOARD TO LOGGED IN USERS ---------->
    @RequestMapping("/shows")
    public String home(HttpSession session, Model model, RedirectAttributes flash) {
        // get userId from session...
    	Long userId = (Long) session.getAttribute("userId");
    	// ...if userId is not in session, create a flash message then REDIRECT to login...
    	if(userId == null) {
    		flash.addFlashAttribute("error", "You must be logged in to view that page!");
    		return "redirect:/login";
    	}
    	// access the user based on the user id that was saved in session and then add that user to the model
    	User u = userService.findUserById(userId);
    	List<Show> shows = showService.findAll();
    	model.addAttribute("user", u);
    	model.addAttribute("shows", shows);
    	return "dashboard.jsp";
    }
    
    // <---------- LOG OUT THE USER BY CLEARING SESSION ---------->
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        // invalidate session
        // redirect to login page
    	session.invalidate();
    	return "redirect:/";
    }
    
}
