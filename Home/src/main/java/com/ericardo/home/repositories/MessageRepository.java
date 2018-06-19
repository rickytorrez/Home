package com.ericardo.home.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ericardo.home.models.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

}