package edu.sjsu.cmpe275.lab2.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Address Class which is to be embedded in the Player/Sponsor object
 * @author priyankasharma
 *
 */
@Entity
@Table(name = "OPPONENT")
public class Opponent {
	@Id
	@GeneratedValue
	private long id;
	
	@NotNull
	private long playerid;

	@NotNull
	private long opponentid;

	public Opponent() {
	    	
	}
	    
	public Opponent(long playerid, long opponentid) {
		this.playerid = playerid;
		this.opponentid = opponentid;
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	

	public long getPlayerid() {
		return playerid;
	}

	public void setPlayerid(long playerid) {
		this.playerid = playerid;
	}

	public long getOpponentid() {
		return opponentid;
	}

	public void setOpponentid(long opponentid) {
		this.opponentid = opponentid;
	}
	
	@Override
	public String toString() {
		return "playerid: " + String.valueOf(playerid) + ", opponentid: "  + String.valueOf(opponentid);
	}

}
