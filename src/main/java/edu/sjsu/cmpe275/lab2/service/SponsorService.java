package edu.sjsu.cmpe275.lab2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.lab2.entity.Sponsor;
import edu.sjsu.cmpe275.lab2.repository.SponsorRepository;

@Service
public class SponsorService {

    @Autowired
    private SponsorRepository repository;

    /**
     * Updates the Sponsor corresponding to the Sponsor id
     * 
     * @param id
     * @param newSponsor
     * @return
     */
    public Sponsor updateSponsorById(long id, Sponsor newSponsor) {
        Sponsor existingSponsor = repository.findById(id).orElse(null);
        /**
         * Return null if Sponsor not found
         */
        if (existingSponsor == null) {
            return null;
        } else {
            existingSponsor.setName(newSponsor.getName());
            existingSponsor.setDescription(newSponsor.getDescription());
            existingSponsor.setAddress(newSponsor.getAddress());
            // Add Players
            return repository.save(existingSponsor);
        }
    }
}
