package com.mike.curso.springboot.jpa.springboot_jpa_relationship.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mike.curso.springboot.jpa.springboot_jpa_relationship.entities.Invoice;

public interface InvoiceRepository  extends CrudRepository<Invoice, Long> {

}
