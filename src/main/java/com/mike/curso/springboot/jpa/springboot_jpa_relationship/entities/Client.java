package com.mike.curso.springboot.jpa.springboot_jpa_relationship.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastName;

// @JoinColumn(name="client_id")
    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
    @JoinTable(
    name="tbl_clientes_to_direcciones",
    joinColumns= @JoinColumn(name="id_cliente"),
    inverseJoinColumns= @JoinColumn(name="id_direcciones"),
    uniqueConstraints= @UniqueConstraint(columnNames= {"id_direcciones"}))
    private Set<Address>  addresses;


    @OneToMany(cascade= CascadeType.ALL, orphanRemoval= true, mappedBy="client")
    private Set<Invoice> invoices;


    @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true, mappedBy="client")
    private ClientDetails clientDetails;

    public Client() {
        addresses = new HashSet<>();
        invoices = new HashSet<>();
    }

    public Client(String name, String lastName) {
        this();
        this.name = name;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Client addInvoice(Invoice invoice){
        invoices.add(invoice);
        invoice.setClient(this);

        return this;
    
    }
    
    public ClientDetails getClientDetails() {
        return clientDetails;
    }
    

    public void setClientDetails(ClientDetails clientDetails) {
        this.clientDetails = clientDetails;
        clientDetails.setClient(this);
    }
    
    public void removeClientDetails(ClientDetails clientDetails) {
        clientDetails.setClient(null);
        this.clientDetails = null;
        
    }



   
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Client{");
        sb.append("id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", lastName=").append(lastName);
        sb.append(", invoices=").append(invoices);
        sb.append(", addresses=").append(addresses);
        sb.append(", clientDetails=").append(clientDetails);
        sb.append('}');
        return sb.toString();
    }






}
