package com.andy.beltexam.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.persistence.CascadeType;

@Entity
@Table(name="shows")
public class Show {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=1, message="Please provide a title")
	private String title;
	
	@Size(min=1, message="Please provide the network")
	private String network;
	
	private Double avgRating;
	
	@Column(updatable=false)
	private Date createdAt;
	
	private Date updatedAt;
	
	// ---------- RELATIONSHIPS ----------
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User userT;
	
	// CascadeType.REMOVE will delete all reviews associated with a TV Show when that show is deleted
	@OneToMany(cascade=CascadeType.REMOVE, mappedBy="show", fetch = FetchType.LAZY)
	private List<Review> reviews;
	
	// ---------- CONSTRUCTORS ----------
	
	public Show() {
	}

	public Show(Long id, @Size(min = 1, message = "Please provide a title") String title,
			@Size(min = 1, message = "Please provide the network") String network, Double avgRating, User userT,
			List<Review> reviews, Date createdAt, Date updatedAt) {
		this.id = id;
		this.title = title;
		this.network = network;
		this.avgRating = avgRating;
		this.userT = userT;
		this.reviews = reviews;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public Double getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(Double avgRating) {
		this.avgRating = avgRating;
	}

	public User getUserT() {
		return userT;
	}

	public void setUserT(User userT) {
		this.userT = userT;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
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
