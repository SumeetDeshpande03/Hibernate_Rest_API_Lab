package edu.sjsu.cmpe275.lab2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.lab2.entity.Player;
import edu.sjsu.cmpe275.lab2.repository.PlayerRepository;

/**
 * Player Service which makes the call to the Repository for performing CRUD operations
 * @author sumeetdeshpande
 *
 */
@Service
public class PlayerService {
	
	@Autowired
	private PlayerRepository repository;
	
	/**
	 * Creates player and adds it to the database
	 * @param player
	 * @return
	 */
	public Player createPlayer(Player player) {
		return repository.save(player);
	}
	
	/**
	 * Gets the player by player id
	 * @param id
	 * @return
	 */
	public Player getPlayerById(long id) {
		return repository.findById(id).orElse(null);
	}
	
	/**
	 * Updates the player corresponding to the player id
	 * @param id
	 * @param newPlayer
	 * @return
	 */
	public Player updatePlayerById(long id, Player newPlayer) {
		Player existingPlayer = repository.findById(id).orElse(null);
		/**
		 * Return null if player not found
		 */
		if(existingPlayer==null) {
			return null;
		} else {
			existingPlayer.setFirstName(newPlayer.getFirstName());
			existingPlayer.setLastName(newPlayer.getLastName());
			existingPlayer.setEmail(newPlayer.getEmail());
			if(newPlayer.getDescription()!=null) {
				existingPlayer.setDescription(newPlayer.getDescription());
			}
			if(newPlayer.getAddress()!=null) {
				existingPlayer.setAddress(newPlayer.getAddress());
			}
			return repository.save(existingPlayer);
		}
	}
	
	/**
	 * Deletes the player corresponding to the player id
	 * @param id
	 * @return
	 */
	public Player deletePlayerById(long id) {
		Player deletedPlayer = repository.findById(id).orElse(null);
		/**
		 * Return null if player not found
		 */
		if(deletedPlayer==null) {
			return null;
		} else {
			repository.delete(deletedPlayer);
			return deletedPlayer;
		}
	}
	
}
