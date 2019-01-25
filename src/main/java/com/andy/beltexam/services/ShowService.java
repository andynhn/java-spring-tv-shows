package com.andy.beltexam.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.andy.beltexam.models.Show;
import com.andy.beltexam.repositories.ShowRepository;

@Service
public class ShowService {
	
	private ShowRepository showRepository;

	public ShowService(ShowRepository showRepository) {
		this.showRepository = showRepository;
	}
	
	public Show create(Show show) {
		return showRepository.save(show);
	}
	
	public Show update(Show show) {
		return showRepository.save(show);
	}
	
	public List<Show> findAll() {
		return showRepository.findAll();
	}
	
	public Show findOneById(Long id) {
		Optional<Show> optionalShow = showRepository.findById(id);
		
		if(optionalShow.isPresent()) {
			return optionalShow.get();
		} else {
			return null;
		}
	}
	
	public void delete(Long id) {
		Optional<Show> optionalShow = showRepository.findById(id);
		if(optionalShow.isPresent()) {
			// CascadeType.REMOVE in Show.java will also delete the reviews associated with that show.
			showRepository.deleteById(id);
		} else {
			System.out.println("Show does not exist");
		}
	}
}
