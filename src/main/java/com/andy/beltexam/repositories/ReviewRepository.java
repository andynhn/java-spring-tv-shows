package com.andy.beltexam.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.andy.beltexam.models.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long>{

}
