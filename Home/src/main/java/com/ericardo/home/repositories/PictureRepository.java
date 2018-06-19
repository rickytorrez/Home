package com.ericardo.home.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ericardo.home.models.Picture;

@Repository
public interface PictureRepository extends CrudRepository<Picture, Long> {

}