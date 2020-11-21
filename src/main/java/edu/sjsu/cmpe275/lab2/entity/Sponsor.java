package edu.sjsu.cmpe275.lab2.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entity object for Sponsor. This is mapped to the Sponsor Table in the database
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
	
	@OneToMany(targetEntity = Player.class, mappedBy = "sponsor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Player> players;
	
	//@JoinColumn(name = "sponsor_id_fk", referencedColumnName = "id")

	public Sponsor() {
		
	}
	
	public Sponsor(String name, String description, Address address) {
		this.name = name;
		this.description = description;
		this.address = address;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
}
