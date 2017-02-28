package com.quotation.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.quotation.entity.Insurer;
import com.quotation.entity.Occupation;
import com.quotation.entity.PostCode;
import com.quotation.service.InsurerService;



/**This rest controller contains apis to insert and get the Insurers
 * to associate the Customer quotation query api
 * @author Madhumita
 *
 */
@RestController
@RequestMapping("/api/insurer")
public class InsurerApiController {

	public static final Logger logger = LoggerFactory.getLogger(InsurerApiController.class);

	@Autowired
	InsurerService insurerService; //Service which will do all data retrieval/manipulation work

	// -------------------Retrieve All Insurers---------------------------------------------
	
	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Insurer> addInsurer(@RequestBody Insurer insurer) {
		
		Set<PostCode> entryPostCodes = insurer.getExcludedPostCodes();
		Set<Occupation> entryOccupations = insurer.getExcludedOccupations();
		if(entryPostCodes!=null){
		for(PostCode p: entryPostCodes){
			List<PostCode> postCodeFromDB = insurerService.findPostCodeByCode(p.getPostCode());
			if(postCodeFromDB!=null && postCodeFromDB.size()==1 && postCodeFromDB.get(0)!=null){
				p.setId(postCodeFromDB.get(0).getId());
				insurer.getExcludedPostCodes().add(p);
			}
		}
		}
		
		if(entryOccupations!=null){
		for(Occupation o: entryOccupations){
			List<Occupation> occupationFromDB = insurerService.findOccupationByName(o.getOccupation());
			if(occupationFromDB!=null && occupationFromDB.size()==1 && occupationFromDB.get(0)!=null){
				o.setId(occupationFromDB.get(0).getId());
				insurer.getExcludedOccupations().add(o);
			}
		}
		}
		insurerService.saveInsurer(insurer);
		logger.debug("Added:: " + insurer);
		return new ResponseEntity<Insurer>(insurer, HttpStatus.CREATED);
	}
	
	/*
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<PostCode> addPostCode(@RequestBody PostCode postCode) {
		insurerService.savePostCode(postCode);
		logger.debug("Added:: " + postCode);
		return new ResponseEntity<PostCode>(postCode, HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Occupation> addOccupation(@RequestBody Occupation occupation) {
		insurerService.saveOccupation(occupation);
		logger.debug("Added:: " + occupation);
		return new ResponseEntity<Occupation>(occupation, HttpStatus.CREATED);
	}
*/


	@RequestMapping(value="/get",method = RequestMethod.GET)
	public ResponseEntity<List<Insurer>> getAllInsurers() {
		List<Insurer> insurers = insurerService.findAllInsurers();
		if (insurers.isEmpty()) {
			logger.debug("Insurers does not exists");
			return new ResponseEntity<List<Insurer>>(insurers,HttpStatus.NO_CONTENT);
		}
		logger.debug("Found " + insurers.size() + " Insurers");
		logger.debug(Arrays.toString(insurers.toArray()));
		return new ResponseEntity<List<Insurer>>(insurers, HttpStatus.OK);
	}
	
	// ------------------- Delete All Users-----------------------------

	@RequestMapping(value = "/deleteAllInsurers/", method = RequestMethod.DELETE)
	public ResponseEntity<Insurer> deleteAllUsers() {
		logger.info("Deleting All Users");

		insurerService.deleteAllInsurers();
		return new ResponseEntity<Insurer>(HttpStatus.NO_CONTENT);
	}

}