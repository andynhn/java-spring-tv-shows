package com.andy.beltexam.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.andy.beltexam.models.Review;
import com.andy.beltexam.models.Show;
import com.andy.beltexam.models.User;
import com.andy.beltexam.services.ShowService;
import com.andy.beltexam.services.UserService;

@Controller
public class Shows {
	
	private ShowService showService;
	private UserService userService;

	public Shows(ShowService showService, UserService userService) {
		this.showService = showService;
		this.userService = userService;
	}
	
	// <--------- DISPLAY PAGE TO CREATE A SHOW ---------->
	@RequestMapping("/shows/new")
	public String newShow(Model model, @ModelAttribute("show") Show show, HttpSession session, RedirectAttributes flash) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			flash.addFlashAttribute("error", "You must be logged in to view that page!");
			return "redirect:/";
		}
		User u = userService.findUserById(userId);
		model.addAttribute("user", u);
		return "newShow.jsp";
	}
	
	// <--------- POST REQUEST TO CREATE A SHOW ---------->
	@PostMapping("/shows")
	public String createShow(@Valid @ModelAttribute("show") Show show, BindingResult result, HttpSession session, RedirectAttributes flash) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			flash.addFlashAttribute("error", "You must be logged in to view that page!");
			return "redirect:/";
		}
		if(result.hasErrors()) {
			return "newShow.jsp";
		} else {
			Show s = showService.create(show);
			Long newShowId = s.getId();
			return String.format("redirect:/shows/%d", newShowId);
		}
	}
	
	// <--------- PAGE SHOWS A SHOW BY ID ---------->
	@RequestMapping("/shows/{id}")
	public String showShow(Model model, @PathVariable("id") Long id, HttpSession session, RedirectAttributes flash, @Valid @ModelAttribute("review") Review review, BindingResult result) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			flash.addFlashAttribute("error", "You must be logged in to view that page!");
			return "redirect:/";
		}
		model.addAttribute("show", showService.findOneById(id));
		User u = userService.findUserById(userId);
		model.addAttribute("user", u);
		return "showShow.jsp";
	}
	
	// <--------- DISPLAY PAGE TO EDIT A SHOW ---------->
	@RequestMapping("/shows/{id}/edit")
	public String editShow(Model model, @PathVariable("id") Long id, HttpSession session, RedirectAttributes flash) {
		Show s = showService.findOneById(id);
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			flash.addFlashAttribute("error", "You must be logged in to view that page!");
			return "redirect:/";
		} else if(userId != s.getUserT().getId()) {
			flash.addFlashAttribute("error", "You cannot edit this show!");
			return "redirect:/shows";
		}
		model.addAttribute("show", s);
		return "editShow.jsp";
	}
	
	// <<--------- POST REQUEST TO UPDATE A SHOW ---------->
	@RequestMapping("/shows/{id}/update")
	public String updateShow(@Valid @ModelAttribute("show") Show show, BindingResult result, @PathVariable("id") Long id, HttpSession session, RedirectAttributes flash) {
		Show s = showService.findOneById(id);
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			flash.addFlashAttribute("error", "You must be logged in to view that page!");
			return "redirect:/";
		} else if(userId != s.getUserT().getId()) {
			flash.addFlashAttribute("error", "You cannot edit this show!");
			return "redirect:/shows";
		}
		
		if(result.hasErrors()) {
			return "editShow.jsp";
		} else {
			showService.update(show);
			return String.format("redirect:/shows/%d", id);
		}
	}
	
	// <---------- DELETE REQUEST TO DELETE A SHOW ---------->
    @RequestMapping(value="/shows/{id}", method=RequestMethod.DELETE)
    public String destroy(@PathVariable("id") Long id, HttpSession session, RedirectAttributes flash) {
    	Show s = showService.findOneById(id);
    	Long userId = (Long) session.getAttribute("userId");
    	if(userId == null) {
    		flash.addFlashAttribute("error", "You must be logged in to view that page!");
    		return "redirect:/";
    	} else if(userId != s.getUserT().getId()) {
    		flash.addFlashAttribute("error", "You cannot edit this show!");
    		return "redirect:/shows";
    	}
    	
    	showService.delete(id);
    	return "redirect:/shows";
    }
	
	
}
