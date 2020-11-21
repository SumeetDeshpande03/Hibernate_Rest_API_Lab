package edu.sjsu.cmpe275.lab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.lab2.entity.Address;
import edu.sjsu.cmpe275.lab2.entity.Player;
import edu.sjsu.cmpe275.lab2.service.PlayerService;

/**
 * Controller for all the Rest Api request endpoints. This routes the requests to the respective services
 * @author sumeetdeshpande
 *
 */
@RestController
public class PlayerController {
	
	@Autowired
	private PlayerService service;
	
	/**
	 * POST API end point for Player
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param description
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @return
	 */
	@PostMapping("/player")
	public ResponseEntity createPlayer(@Param("firstName") String firstName, @Param("lastName") String lastName, 
							@Param("email") String email, @Param("description") String description,
							@Param("street")String street, @Param("city")String city,
							@Param("state")String state, @Param("zip")String zip,
							@Param("sponsorId")Long sponsorId) {
		
		Address address = new Address(street, city, state, zip);
		Player player = new Player(firstName, lastName, email, description, address);
		try {
			/**
			 * Return response with status 200
			 */
			Player createdPlayer = null;
			if(sponsorId!=null) {
				createdPlayer = service.createPlayer(player, sponsorId);
				
			} else {
				createdPlayer = service.createPlayer(player, null);
			}
			if(createdPlayer==null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
			return ResponseEntity.ok(createdPlayer);
		} catch(Exception e) {
			/**
			 * Return status 400 if input is invalid
			 */
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
	}
	
	/**
	 * GET API end point for Player
	 * @param id
	 * @return
	 */
	@GetMapping("/player/{id}")
	public ResponseEntity getPlayer(@PathVariable long id) {
		
		Player player = service.getPlayerById(id);
		if(player!=null) {
			/**
			 * Return response with status 200
			 */
			return ResponseEntity.ok(player);
		} else {
			/**
			 * Return status 404 if player not found
			 */
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
	}
	
	/**
	 * PUT API end point for Player
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param description
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @return
	 */
	@PutMapping("/player/{id}")
	public ResponseEntity updatePlayer(@PathVariable long id, @Param("firstName") String firstName, @Param("lastName") String lastName, 
							@Param("email") String email, @Param("description") String description,
							@Param("street")String street, @Param("city")String city,
							@Param("state")String state, @Param("zip")String zip,
							@Param("sponsorId")String sponsorId) {
		
		Address address = new Address(street, city, state, zip);
		Player newPlayer = new Player(firstName, lastName, email, description, address);
		try {
			Player updatedPlayer = service.updatePlayerById(id, newPlayer);
			if(updatedPlayer==null) {
				/**
				 * Return status 404 if player not found
				 */
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			} else {
				/**
				 * Return response with status 200
				 */
				return ResponseEntity.ok(updatedPlayer);
			}
		} catch(Exception e) {
			/**
			 * Return status 400 if input is invalid
			 */
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
		
	}
	
	/**
	 * DELETE API end point for Player
	 * @param id
	 * @return
	 */
	@DeleteMapping("/player/{id}")
	public ResponseEntity deletePlayer(@PathVariable long id) {
		
		Player deletedPlayer = service.deletePlayerById(id);
		if(deletedPlayer==null) {
			/**
			 * Return status 404 if player not found
			 */
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			/**
			 * Return response with status 200
			 */
			return ResponseEntity.ok(deletedPlayer);
		}
		
	}

}
