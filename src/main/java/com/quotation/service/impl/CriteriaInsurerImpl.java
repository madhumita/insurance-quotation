package com.quotation.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.quotation.model.CustomerQuery;
import com.quotation.model.ExclusionReason;
import com.quotation.entity.Insurer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.quotation.configuration.ApplicationConfiguration;
import com.quotation.controller.CustomerApiController;
import com.quotation.repository.InsurerRepository;
import com.quotation.service.ICriteria;

/**
 * This class implements the generic interface for objects of type CustomerQuery
 * and Insurer It overrides checkCriteriaAndReturnQuote for the specific
 * criteria logic provided in assessment
 * 
 * @author Madhumita
 *
 */
@Component
public class CriteriaInsurerImpl implements ICriteria<CustomerQuery, Insurer> {

	public static final Logger logger = LoggerFactory
			.getLogger(CriteriaInsurerImpl.class);

	@Autowired
	private InsurerRepository insurerRepository;

	@Autowired
	private ApplicationConfiguration config;

	/*
	 * This method checks the criteria for each insurer against provided
	 * Business rules to determine the final list of insurers eligible for the
	 * panel
	 * 
	 * @see
	 * com.quotation.service.ICriteria#checkCriteriaAndReturnQuote(java.lang
	 * .Object, java.util.List)
	 */
	@Override
	public Map<String, Double> checkCriteriaAndReturnQuote(CustomerQuery query,
			List<Insurer> insurers) {

		Map<String, Double> finalList = new HashMap<String, Double>();
		List<String> mustCriteria = config.getMustCriteria();
		List<String> shouldCriteria = config.getShouldCriteria();
		List<Insurer> allInsurers = insurers;
		Set<Insurer> excludedInsurers = new HashSet<Insurer>();

		List<Insurer> insurersWithExcludedPostCodes = new ArrayList<Insurer>();
		List<Insurer> insurersWithExcludedPostCodesAndOccupations = new ArrayList<Insurer>();
		List<Insurer> insurersWithExcludedOccupations = new ArrayList<Insurer>();

		// compute the excluded list for must(and) criteria
		if (mustCriteria != null && mustCriteria.size() > 0) {

			if (mustCriteria.contains(config.POSTCODE)
					&& mustCriteria
							.contains(ApplicationConfiguration.OCCUPATION)) {

				insurersWithExcludedPostCodesAndOccupations = insurerRepository
						.findByExcludedOccupationsAndExcludedPostCodes(
								query.getPostCode(), query.getOccupation());
				excludedInsurers
						.addAll(insurersWithExcludedPostCodesAndOccupations);
			} else if (mustCriteria.contains(config.OCCUPATION)) {

				insurersWithExcludedOccupations = insurerRepository
						.findByExcludedOccupations(query.getOccupation());
				excludedInsurers.addAll(insurersWithExcludedOccupations);
			} else if (mustCriteria.contains(config.POSTCODE)) {

				insurersWithExcludedPostCodes = insurerRepository
						.findByExcludedPostCodes(query.getPostCode());
				excludedInsurers.addAll(insurersWithExcludedPostCodes);
			} else {
				insurersWithExcludedPostCodesAndOccupations = null;
				insurersWithExcludedOccupations = null;
				insurersWithExcludedPostCodes = null;

			}

		}

		// compute the excluded list for Or criteria
		if (shouldCriteria != null && shouldCriteria.size() > 0) {

			if (shouldCriteria
					.contains(ApplicationConfiguration.LESS_THAN_MINIMUM_TURNOVER)) {
				logger.debug("shouldCriteria has MINIMUM_TURNOVER ");
				excludedInsurers.addAll(insurerRepository
						.findByMinimumTurnOverGreaterThan(query.getTurnOver()));
			}
		}

		if ((excludedInsurers != null && excludedInsurers.size() > 0)) {
			List excludedInsurersList = new ArrayList(excludedInsurers);
			for (Insurer i : allInsurers) {

				if (!excludedInsurersList.contains(i))
					finalList.put(i.getName(),
							ApplicationConfiguration.PRICE_QUOTE);
			}
		} else {
			for (Insurer i : allInsurers) {

				if (!excludedInsurers.contains(i))
					finalList.put(i.getName(),
							ApplicationConfiguration.PRICE_QUOTE);
			}

		}
		return finalList;

	}

}
