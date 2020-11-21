package edu.sjsu.cmpe275.lab2.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;

/**
 * Entity object for Sponsor. This is mapped to the Sponsor Table in the
 * database
 * 
 * @author sumeetdeshpande
 *
 */
@Entity
@Table(name = "SPONSOR")
public class Sponsor {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String name;

    private String description;

    @Embedded
    private Address address;

    public Sponsor() {

    }

    // Add players
    public Sponsor(String name, String description, Address address) {
        this.name = name;
        this.description = description;
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
