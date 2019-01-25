package com.andy.beltexam.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.andy.beltexam.models.Show;

@Repository
public interface ShowRepository extends CrudRepository<Show, Long>{
	@Query(value="SELECT * FROM shows ORDER BY avg_rating DESC;", nativeQuery=true)
	List<Show> findAll();
}
