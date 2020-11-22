package edu.sjsu.cmpe275.lab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.lab2.entity.Opponent;
import edu.sjsu.cmpe275.lab2.entity.Player;
import edu.sjsu.cmpe275.lab2.service.OpponentService;
import edu.sjsu.cmpe275.lab2.service.PlayerService;

/**
 * Controller for all the Rest Api request endpoints. This routes the requests to the respective services
 * @author priyankasharma
 *
 */
@RestController
public class OpponentController {
	
	@Autowired
	private OpponentService opponentService;
	
	@Autowired
	private PlayerService playerService;
	
	/**
	 * PUT API end point for Player
	 * @param id1
	 * @param id2
	 * @return
	 */
	@PutMapping("/opponents/{id1}/{id2}")
	public ResponseEntity addOpponent(@PathVariable long id1, @PathVariable long id2) {
		Player player1 = playerService.getPlayerById(id1);
		if (player1 == null) 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		
		Player player2 = playerService.getPlayerById(id2);
		if (player2 == null) 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	
		Opponent details = opponentService.addOpponent(id1, id2);
		if (details == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	/**
	 * DELETE API end point for Player
	 * @param id1
	 * @param id2
	 * @return
	 */
	@DeleteMapping("/opponents/{id1}/{id2}")
	public ResponseEntity deletePlayer(@PathVariable long id1, @PathVariable long id2) {
		
		Player player1 = playerService.getPlayerById(id1);
		if (player1 == null) 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		
		Player player2 = playerService.getPlayerById(id2);
		if (player2 == null) 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	

		Opponent details = opponentService.deleteOpponent(id1, id2);
		if (details == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
		String msg = "Opponent deleted successfully";
		return ResponseEntity.status(HttpStatus.OK).body(msg);
	}

}
