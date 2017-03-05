package com.quotation.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.quotation.exception.InsurerNotFoundException;
import com.quotation.model.CustomerQuery;
import com.quotation.service.InsurerService;
import com.quotation.validator.CustomerQueryValidator;

/**
 * Rest api controller that contains the api for customer to query quotation
 * 
 * @author Madhumita
 *
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerApiController {

	public static final Logger logger = LoggerFactory
			.getLogger(CustomerApiController.class);

	// Service which will do all data retrieval/manipulation work
	@Autowired
	InsurerService insurerService;

	// Service which will do all data retrieval/manipulation work
	@Autowired
	CustomerQueryValidator customerValidator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(customerValidator);
	}

	@RequestMapping(value = "/quotation", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Double>> getAllInsurers(
			@RequestBody @Valid CustomerQuery query, BindingResult result)
			throws InsurerNotFoundException {
		Map<String, Double> emptyMap = new HashMap<String, Double>();
		Map<String, Double> insurers = new HashMap<String, Double>();
		Errors errors;

		if (result.hasErrors()) {
			for (ObjectError e : result.getAllErrors()) {
				if (!StringUtils.isEmpty(e.getCode()))
					emptyMap.put(e.getCode(), null);
			}
			return new ResponseEntity<Map<String, Double>>(emptyMap,
					HttpStatus.BAD_REQUEST);
		}

		try {

			insurers = insurerService.checkCriteriaAndReturnQuote(query);
			logger.debug("Found " + insurers.size() + " Insurers");
			return new ResponseEntity<Map<String, Double>>(insurers,
					HttpStatus.OK);

		} catch (InsurerNotFoundException e) {
			if (insurers.isEmpty()) {

				logger.debug("No Insurer is eligible for Panel.");
				emptyMap.put("No Insurer is eligible for Panel.", null);
				return new ResponseEntity<Map<String, Double>>(emptyMap,
						HttpStatus.NO_CONTENT);
			} else
				throw new InsurerNotFoundException(
						"System Error in Computing Insurer Panel and Quote.");

		}

	}
}
