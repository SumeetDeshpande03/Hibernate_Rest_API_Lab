package edu.sjsu.cmpe275.lab2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.sjsu.cmpe275.lab2.entity.Sponsor;

/**
 * Interface Player Repository which extends the JPA Repository for performing
 * all the CRUD operations
 * 
 * @author harshitmalwiya
 *
 */
public interface SponsorRepository extends JpaRepository<Sponsor, Long> {

}
