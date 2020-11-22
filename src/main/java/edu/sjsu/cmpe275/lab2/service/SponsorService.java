package edu.sjsu.cmpe275.lab2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.lab2.entity.Player;
import edu.sjsu.cmpe275.lab2.entity.Sponsor;
import edu.sjsu.cmpe275.lab2.repository.SponsorRepository;

@Service
public class SponsorService {

	@Autowired
	private SponsorRepository repository;
	
	/**
	 * Creates sponsor and adds it to the database
	 * @param sponsor
	 * @return
	 */
	public Sponsor createSponsor(Sponsor sponsor) {
		return repository.save(sponsor);
	}
	
	/**
	 * Gets the sponsor by sponsor id
	 * @param id
	 * @return
	 */
	public Sponsor getSponsorById(long id) {
		return repository.findById(id).orElse(null);
	}
	
	/**
	 * Updates the sponsor by id
	 * @param id
	 * @param sponsor
	 * @return
	 */
	public Sponsor updateSponsorById(long id, Sponsor newSponsor) {
		
		Sponsor existingSponsor = repository.findById(id).orElse(null);
		/**
		 * Return null if sponsor not found
		 */
		if(existingSponsor==null) {
			return null;
		} else {
			existingSponsor.setName(newSponsor.getName());
			existingSponsor.setDescription(newSponsor.getDescription());
			existingSponsor.setAddress(newSponsor.getAddress());
			return repository.save(existingSponsor);
		}
		
	}
	
	/**
	 * Deletes the sponsor by sponsor id
	 * @param id
	 * @return
	 */
	public Sponsor deleteSponsorById(long id) {
		Sponsor deletedSponsor = repository.findById(id).orElse(null);
		/**
		 * Return null if sponsor not found
		 */
		if(deletedSponsor==null) {
			return null;
		} else {
			repository.delete(deletedSponsor);
			return deletedSponsor;
		}
	}
	
}