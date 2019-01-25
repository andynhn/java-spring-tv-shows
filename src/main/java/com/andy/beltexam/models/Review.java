package com.andy.beltexam.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name="reviews")
public class Review {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Min(value=1, message="Invalid Rating")
	@Max(value=5, message="Invalid Rating")
	private Integer rating;
	
	@Column(updatable=false)
	private Date createdAt;
	
	private Date updatedAt;
	
	// ---------- RELATIONSHIPS ----------
	
	// Many Reviews to One User
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	// Many Reviews to One Show
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="show_id")
	private Show show;

	
	// ---------- CONSTRUCTORS ----------
	
	public Review() {
	}

	public Review(Long id,
			@Min(value = 1, message = "Invalid Rating") @Max(value = 5, message = "Invalid Rating") Integer rating,
			User user, Show show, Date createdAt, Date updatedAt) {
		this.id = id;
		this.rating = rating;
		this.user = user;
		this.show = show;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	// ---------- GETTERS AND SETTERS ----------
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
	
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
	
	
}
