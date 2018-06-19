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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Listing {

	/*************************************** ATTRIBUTES **************************************/
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Size(min=1, max=255, message="Please provide an address.")
	private String address;
	
	@Size(min=1, max=255, message="Please provide a description.")
	private String description;
	
	@Size(min=1, max=255, message="Please provide the type of property.")
	private String type;
	
	@NotNull(message="Please provide the year the property was built.")
	private Integer yearbuilt;
	
	@NotNull(message="Please provide the number of rooms in the property.")
	private Integer numberofrooms;
	
	@NotNull(message="Please provide the number of bathrooms in the property.")
	private Integer numberofbathrooms;
	
	@NotNull(message="Please provide the size of the property in square feet.")
	private Integer size;
	
	@NotNull(message="Please provide the cost of the property.")
	private double cost;
		
	@DateTimeFormat(pattern="MM:dd:yyyy HH:mm:ss")
	private Date createdAt;
	 	
	@DateTimeFormat(pattern="MM:dd:yyyy HH:mm:ss")
	private Date updatedAt;

	/************************************* RELATIONSHIPS *************************************/
	 	
	/*************************************** with user ***************************************/
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	/*************************************** with picture ************************************/

	@OneToMany(mappedBy="listing", fetch=FetchType.LAZY)
	private List<Picture> pictures;
	
	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}
	
	/********************** many to many with user for favorite images **********************/
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "user_fave_properties",
			joinColumns = @JoinColumn(name = "listing_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private List<User> faveUsers;
	
	public List<User> getFaveUsers() {
		return faveUsers;
	}

	public void setFaveUsers(List<User> faveUsers) {
		this.faveUsers = faveUsers;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getYearbuilt() {
		return yearbuilt;
	}

	public void setYearbuilt(Integer yearbuilt) {
		this.yearbuilt = yearbuilt;
	}

	public Integer getNumberofrooms() {
		return numberofrooms;
	}

	public void setNumberofrooms(Integer numberofrooms) {
		this.numberofrooms = numberofrooms;
	}

	public Integer getNumberofbathrooms() {
		return numberofbathrooms;
	}

	public void setNumberofbathrooms(Integer numberofbathrooms) {
		this.numberofbathrooms = numberofbathrooms;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
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
	 	
	public Listing() {
	 	this.createdAt = new Date();
	 	this.updatedAt = new Date();
	}
}
