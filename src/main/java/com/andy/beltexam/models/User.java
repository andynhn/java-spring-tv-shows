package com.andy.beltexam.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Email(message="Email must be valid")
	@Size(min=1, message="Please provide an email")
	private String email;
	
	@Size(min=1, message="Please provide a name")
	private String name;
	
	@Size(min=8, message="Password must be 8 characters or longer")
	private String password;
	
	@Transient
	// uses a custom validation in the UserValidator
	private String passwordConfirmation;
	
	@Column(updatable=false)
	private Date createdAt;
	
	private Date updatedAt;
	
	// ---------- RELATIONSHIPS ----------
	
	// One User to Many Shows
	@OneToMany(mappedBy="userT", fetch=FetchType.LAZY)
	private List<Show> shows;
	
	// One User to Many Reviews
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<Review> reviews;
	
	// ---------- CONSTRUCTORS ----------
	
	public User() {
	}

	public User(Long id,
			@Email(message = "Email must be valid") @Size(min = 1, message = "Please provide an email") String email,
			@Size(min = 1, message = "Please provide a name") String name,
			@Size(min = 8, message = "Password must be 8 characters or longer") String password,
			String passwordConfirmation, Date createdAt, Date updatedAt, List<Show> shows, List<Review> reviews) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.shows = shows;
		this.reviews = reviews;
	}

	// ---------- GETTERS AND SETTERS ----------
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
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

	public List<Show> getShows() {
		return shows;
	}

	public void setShows(List<Show> shows) {
		this.shows = shows;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
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
