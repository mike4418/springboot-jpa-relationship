package com.mike.curso.springboot.jpa.springboot_jpa_relationship.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mike.curso.springboot.jpa.springboot_jpa_relationship.entities.ClientDetails;

public interface  ClientDetailsRepository extends CrudRepository<ClientDetails, Long>{

}
