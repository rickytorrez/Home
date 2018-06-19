package com.ericardo.home.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ericardo.home.models.Listing;

@Repository
public interface ListingRepository extends CrudRepository<Listing, Long> {
	public List<Listing> findByAddress(String address);
}