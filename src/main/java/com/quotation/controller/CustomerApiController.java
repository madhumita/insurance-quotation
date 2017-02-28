package com.quotation.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
import com.quotation.model.CustomerQuery;
import com.quotation.model.ExclusionReason;
import com.quotation.service.InsurerService;

/**Rest api controller that contains the api for customer to query quotation
 * @author Madhumita
 *
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerApiController {
	
	public static final Logger logger = LoggerFactory.getLogger(CustomerApiController.class);

	@Autowired
	InsurerService insurerService; //Service which will do all data retrieval/manipulation work

	@RequestMapping(value="/quotation",method = RequestMethod.POST)
	public ResponseEntity<Map<String,Double>> getAllInsurers(@RequestBody CustomerQuery query) {
		Map<String,Double> insurers = insurerService.checkCriteriaAndReturnQuote(query);
		if (insurers.isEmpty()) {
			logger.debug("No Insurer is eligible for Panel.");
			return new ResponseEntity<Map<String,Double>>(insurers,HttpStatus.NO_CONTENT);
		}
		logger.debug("Found " + insurers.size() + " Insurers");
		
		return new ResponseEntity<Map<String,Double>>(insurers, HttpStatus.OK);
	}
}
