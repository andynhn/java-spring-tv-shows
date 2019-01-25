package com.andy.beltexam.services;

import org.springframework.stereotype.Service;

import com.andy.beltexam.models.Review;
import com.andy.beltexam.repositories.ReviewRepository;

@Service
public class ReviewService {
	
	private ReviewRepository reviewRepository;

	public ReviewService(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}
	
	public Review create(Review review) {
		return reviewRepository.save(review);
	}
}
