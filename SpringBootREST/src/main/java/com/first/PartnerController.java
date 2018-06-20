package com.first;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PartnerController {

	@Autowired
	PartnerRepository partnerRepository;
	
	@GetMapping("/partner")
	public List<Partner> getAllNotes() {
	    return partnerRepository.findAll();
	}
	@PostMapping("/partner")
	public Partner createPartner(@Valid @RequestBody Partner partner) {
	    return partnerRepository.save(partner);
	}
	
	@PutMapping("/partner/{id}")
	public ResponseEntity<Partner> updatePartner(@PathVariable(value = "id") Long partnerId, 
	                                       @Valid @RequestBody Partner partnerDetails) {
		Partner partner = partnerRepository.findOne(partnerId);
	    if(partner == null) {
	        return ResponseEntity.notFound().build();
	    }
	   partner.setPartnerName(partnerDetails.getPartnerName());

	    Partner updatedPartner = partnerRepository.save(partner);
	    return ResponseEntity.ok(updatedPartner);
	}
	@DeleteMapping("/partner/{id}")
	public ResponseEntity<Partner> deletePartner(@PathVariable(value = "id") Long partnerId) {
		Partner partner = partnerRepository.findOne(partnerId);
	    if(partner == null) {
	        return ResponseEntity.notFound().build();
	    }

	    partnerRepository.delete(partner);
	    return ResponseEntity.ok().build();
	}
	
}
