package edu.sjsu.cmpe275.lab2.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Entity object for Sponsor. This is mapped to the Sponsor Table in the database
 * @author sumeetdeshpande
 *
 */
@Entity
@Table(name = "SPONSOR")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Sponsor {
	
	@Id
	@GeneratedValue
	private long id;
	
	@NotNull
	private String name;
	
	private String description;
	
	@Embedded
	private Address address;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE}, mappedBy = "sponsor", fetch = FetchType.LAZY)
	private List<Player> players;
	
	/**
	 * Used for setting player sponsor id to null in player table before removing sponsor
	 */
	@PreRemove
	private void preRemove() {
		players.forEach(player->player.setSponsor(null));
	}

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