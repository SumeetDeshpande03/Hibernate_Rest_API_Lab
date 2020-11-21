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
	
}
