package edu.sjsu.cmpe275.lab2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.lab2.entity.Player;
import edu.sjsu.cmpe275.lab2.entity.Sponsor;
import edu.sjsu.cmpe275.lab2.repository.PlayerRepository;
import edu.sjsu.cmpe275.lab2.repository.SponsorRepository;

/**
 * Player Service which makes the call to the Repository for performing CRUD operations
 * @author sumeetdeshpande
 *
 */
@Service
public class PlayerService {
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private SponsorRepository sponsorRepository;
	
	/**
	 * Creates player and adds it to the database
	 * @param player
	 * @return
	 * @throws Exception 
	 */
	public Player createPlayer(Player player, Long sponsorId) throws Exception {
		
		/**
		 * Check if sponsor id is passed
		 */
		if(sponsorId!=null) {
			Sponsor sponsor = sponsorRepository.findById(sponsorId).orElse(null);
			/**
			 * Check if sponsor id is present
			 */
			if(sponsor==null) {
				throw new Exception("Sponsor does not exist");
			}
			player.setSponsor(sponsor);
		}
		
		return playerRepository.save(player);
	}
	
	/**
	 * Gets the player by player id
	 * @param id
	 * @return
	 */
	public Player getPlayerById(long id) {
		return playerRepository.findById(id).orElse(null);
	}
	
	/**
	 * Updates the player corresponding to the player id
	 * @param id
	 * @param newPlayer
	 * @return
	 * @throws Exception 
	 */
	public Player updatePlayerById(long id, Player newPlayer, Long sponsorId) throws Exception {
		
		Player existingPlayer = playerRepository.findById(id).orElse(null);
		/**
		 * Return null if player not found
		 */
		if(existingPlayer==null) {
			return null;
		} else {
			/**
			 * Check if sponsor id is passed
			 */
			if(sponsorId!=null) {
				Sponsor sponsor = sponsorRepository.findById(sponsorId).orElse(null);
				/**
				 * Check if sponsor id is present
				 */
				if(sponsor==null) {
					throw new Exception("Sponsor does not exist");
				}
				existingPlayer.setSponsor(sponsor);
			}
			
			existingPlayer.setFirstName(newPlayer.getFirstName());
			existingPlayer.setLastName(newPlayer.getLastName());
			existingPlayer.setEmail(newPlayer.getEmail());
			existingPlayer.setDescription(newPlayer.getDescription());
			existingPlayer.setAddress(newPlayer.getAddress());

			return playerRepository.save(existingPlayer);
		}
	}
	
	/**
	 * Deletes the player corresponding to the player id
	 * @param id
	 * @return
	 */
	public Player deletePlayerById(long id) {
		
		Player deletedPlayer = playerRepository.findById(id).orElse(null);
		/**
		 * Return null if player not found
		 */
		if(deletedPlayer==null) {
			return null;
		} else {
			playerRepository.delete(deletedPlayer);
			return deletedPlayer;
		}
		
	}
	
}
