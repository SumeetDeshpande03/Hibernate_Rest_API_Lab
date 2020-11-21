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
import edu.sjsu.cmpe275.lab2.entity.Sponsor;
import edu.sjsu.cmpe275.lab2.service.SponsorService;

/**
 * Controller for all the Rest Api request endpoints. This routes the requests
 * to the respective services
 * 
 * @author harshitmalwiya
 *
 */
@RestController
public class SponsorController {

    /**
     * PUT API end point for Sponsor
     * 
     * @param id
     * @param name
     * @param description
     * @param street
     * @param city
     * @param state
     * @param zip
     * @return
     */
    @PutMapping("/sponsor/{id}")
    public ResponseEntity updatePlayer(@PathVariable long id, @Param("name") String name,
            @Param("description") String description, @Param("street") String street, @Param("city") String city,
            @Param("state") String state, @Param("zip") String zip) {

        Address address = new Address(street, city, state, zip);
        Sponsor newSponsor = new Sponsor(name, description, address);
        try {
            Sponsor updatedSponsor = service.updatedSponsorById(id, newSponsor);
            if (updatedSponsor == null) {
                /**
                 * Return status 404 if player not found
                 */
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            } else {
                /**
                 * Return response with status 200
                 */
                return ResponseEntity.ok(updatedSponsor);
            }
        } catch (Exception e) {
            /**
             * Return status 400 if input is invalid
             */
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }
}
