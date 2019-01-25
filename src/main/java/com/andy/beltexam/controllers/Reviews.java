package com.andy.beltexam.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.andy.beltexam.models.Review;
import com.andy.beltexam.models.Show;
import com.andy.beltexam.models.User;
import com.andy.beltexam.services.ReviewService;
import com.andy.beltexam.services.ShowService;
import com.andy.beltexam.services.UserService;

@Controller
public class Reviews {
	
	private ReviewService reviewService;
	private ShowService showService;
	private UserService userService;

	public Reviews(ReviewService reviewService, ShowService showService, UserService userService) {
		this.reviewService = reviewService;
		this.showService = showService;
		this.userService = userService;
	}
	
	@PostMapping("/shows/{id}/review")
	public String review(@Valid @ModelAttribute("review") Review review, BindingResult result, Model model, @PathVariable("id") Long id, HttpSession session, RedirectAttributes flash) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			flash.addFlashAttribute("error", "You must be logged in to view that page!");
			return String.format("redirect:/shows/%d", id);
		}
		if(result.hasErrors()) {
			// REGENERATE SHOW PAGE BY RE-ADDING SHOW AND LOGGED IN USER TO THE PAGE MODEL.
			model.addAttribute("show", showService.findOneById(id));
			model.addAttribute("user", userService.findUserById(userId));
			return "showShow.jsp";
		} else {
			User u = userService.findUserById(userId);
			Show s = showService.findOneById(id);
			List<Review> reviews = s.getReviews();
			// CHECK TO SEE IF THE LOGGED IN USER ALREADY REVIEWED THAT SHOW
			for(Review r: reviews) {
				if(r.getUser().getId() == u.getId()) {
					flash.addFlashAttribute("error", "You already reviewed this show!");
					return String.format("redirect:/shows/%d", id);
				}
			}
			
			// IF WE MAKE IT HERE, CREATE THE REVIEW...
			review.setId(null);
			Review newR = reviewService.create(review);
			// IF THIS IS THE FIRST REVIEW, SET THE AVG RATING AS THE NEW REVIEW'S RATING
			if(s.getAvgRating() == null) {
				s.setAvgRating(newR.getRating()/1.0);
				showService.update(s);
			} else {
				// IF THIS IS NOT THE FIRST REVIEW, LOOP THROUGH THE RATINGS AND RECALCULATE THE AVERAGE
				Double sum = 0.0;
				for(Review r: reviews) {
					System.out.println("rating: " + r.getRating());
					sum += r.getRating();
				}
				// the for loop above does not include the current rating, so need to add it at the end
				sum += newR.getRating();
				System.out.println(sum);
				// ...and also need to add it to the total size...
				System.out.println("average: " + sum/(reviews.size() + 1));
				s.setAvgRating(sum/(reviews.size()+1));
				showService.update(s);
			}
			return String.format("redirect:/shows/%d", id);
		}
	}
}
