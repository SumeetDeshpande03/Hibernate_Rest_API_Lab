package edu.sjsu.cmpe275.lab2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.lab2.entity.Opponent;

/**
 * Interface Opponent Repository which extends the JPA Repository for performing all the CRUD operations
 * @author priyankasharma
 *
 */
public interface OpponentRepository extends JpaRepository<Opponent, Long>{
	
	Opponent findByPlayeridAndOpponentid(Long playerid, Long opponentid);
	
	@Transactional
	void deleteByPlayeridOrOpponentid(Long playerid, Long opponentid);

}
