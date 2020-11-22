package edu.sjsu.cmpe275.lab2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.lab2.entity.Opponent;
import edu.sjsu.cmpe275.lab2.repository.OpponentRepository;


/**
 * Player Service which makes the call to the Repository for performing CRUD operations
 * @author priyankasharma
 *
 */
@Service
public class OpponentService {
	
	@Autowired
	private OpponentRepository opponentRepository;
	
	/**
	 * Adds the opponent relationship
	 * @param id1
	 * @param id2
	 * @return
	 */
	public Opponent addOpponent(long id1, long id2) {
		Opponent details1 = new Opponent();                         
		details1.setPlayerid(id1); 
		details1.setOpponentid(id2);
		
		Opponent details2 = new Opponent();                         
		details2.setPlayerid(id2); 
		details2.setOpponentid(id1);
		try {
			Object result = opponentRepository.findByPlayeridAndOpponentid(id1, id2);
	
			if (result == null) {
				System.out.println("addOpponent: "+ details1.toString());

				opponentRepository.save(details2);
				return opponentRepository.save(details1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	/**
	 * Deletes the opponent relationship
	 * @param id1
	 * @param id2
	 * @return
	 */
	public Opponent deleteOpponent(long id1, long id2) {
		Opponent details = new Opponent();                         
		details.setPlayerid(id1); 
		details.setOpponentid(id2);
				
		try {
			Opponent result = opponentRepository.findByPlayeridAndOpponentid(id1, id2);
			Opponent result_reverse = opponentRepository.findByPlayeridAndOpponentid(id2, id1);
	
			if (result == null && result_reverse == null) {
				return null;
			} else {
				System.out.println("Deleting " + result);
				if (result != null)
					opponentRepository.delete(result);
				
				if (result_reverse != null)
					opponentRepository.delete(result_reverse);
				
				return details;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * Deletes the opponents by playerid
	 * @param id1
	 * @return
	 */
	public void deleteOpponents(long playerid) {
		opponentRepository.deleteByPlayeridOrOpponentid(playerid, playerid);
		return;
	}
	
	
}
