package com.ericardo.home.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class User {
	
	/*************************************** ATTRIBUTES **************************************/
	@Id
	@GeneratedValue
	private Long id;
	
	@Size(min=1, max=124, message="Please provide a first name")
	private String firstname;
	
	@Size(min=1, max=124, message="Please provide a last name")
	private String lastname;
	
	@Size(min=1, max=124, message="Please provide a username")
	private String username;
	
	@Email(message="Invalid E-mail format. Ex: user@user.com")
	private String email;
	
	@Size(min=8, max=124, message="Password must be between 8 and 124 characters")
	private String password;
	
	@Transient																						
	@Size(min=8, max=124, message="Password confirmation must be between 8 and 124 characters")
	private String confirm;
	
	private boolean realtor;
	
	private boolean admin;

	@DateTimeFormat(pattern="MM:dd:yyyy HH:mm:ss")
	private Date createdAt;
	
	@DateTimeFormat(pattern="MM:dd:yyyy HH:mm:ss")
	private Date updatedAt;
	
	/************************************* RELATIONSHIPS *************************************/
	
	/************************************* with listing **************************************/
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<Listing> listings;
	
	public List<Listing> getListings() {
		return listings;
	}

	public void setListings(List<Listing> listings) {
		this.listings = listings;
	}
	
	/************************************* with picture **************************************/

	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<Picture> pictures;
	
	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}
	
	/********************** many to many with listing for favorite images *******************/
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "user_fave_properties",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "listing_id")
	)
	private List<Listing> faveListings;

	public List<Listing> getFaveListings() {
		return faveListings;
	}

	public void setFaveListings(List<Listing> faveListings) {
		this.faveListings = faveListings;
	}

	/********************************* PERSIST AND UPDATE  **********************************/
	
	@PrePersist
	public void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	public void onUpdate() {
		this.updatedAt = new Date();
	}
	
	/******************************** SETTERS AND GETTERS  **********************************/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	
	public boolean isRealtor() {
		return realtor;
	}

	public void setRealtor(boolean realtor) {
		this.realtor = realtor;
	}
	
	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
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
	
	/********************************** EMPTY CONSTRUCTOR  **********************************/
	
	public User() {
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}
	
}
