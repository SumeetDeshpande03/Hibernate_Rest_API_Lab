package edu.sjsu.cmpe275.lab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.lab2.entity.Address;
import edu.sjsu.cmpe275.lab2.entity.Player;
import edu.sjsu.cmpe275.lab2.entity.Sponsor;
import edu.sjsu.cmpe275.lab2.service.SponsorService;

@RestController
public class SponsorController {

	@Autowired
	private SponsorService service;
	
	@PostMapping("/sponsor")
	public ResponseEntity createSponsor(@Param("name") String name, @Param("description") String description, 
							@Param("street")String street, @Param("city")String city,
							@Param("state")String state, @Param("zip")String zip) {
		
		Address address = new Address(street, city, state, zip);
		Sponsor sponsor = new Sponsor(name, description, address);
		try {
			/**
			 * Return response with status 200
			 */
			return ResponseEntity.ok(service.createSponsor(sponsor));
		} catch(Exception e) {
			/**
			 * Return status 400 if input is invalid
			 */
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
	}
	
	/**
	 * GET API end point for Sponsor
	 * @param id
	 * @return
	 */
	@GetMapping("/sponsor/{id}")
	public ResponseEntity getPlayer(@PathVariable long id) {
		
		Sponsor sponsor = service.getSponsorById(id);
		if(sponsor!=null) {
			/**
			 * Return response with status 200
			 */
			return ResponseEntity.ok(sponsor);
		} else {
			/**
			 * Return status 404 if player not found
			 */
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
	}
	
}
