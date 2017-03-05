package com.quotation.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quotation.configuration.ApplicationConfiguration;
import com.quotation.entity.Insurer;
import com.quotation.entity.Occupation;
import com.quotation.entity.PostCode;
import com.quotation.exception.InsurerNotFoundException;
import com.quotation.model.CustomerQuery;
import com.quotation.model.ExclusionReason;
import com.quotation.repository.InsurerRepository;
import com.quotation.repository.OccupationRepository;
import com.quotation.repository.PostCodeRepository;
import com.quotation.service.ICriteria;
import com.quotation.service.InsurerService;

/**
 * This class intends to hold the logic for various operations for persisting
 * ,finding insurer and its related attributes or checking criteria for Insurer
 * 
 * @author MADHUMITA
 *
 */
@Service("InsurerService")
public class InsurerServiceImpl implements InsurerService {

	@Autowired
	private InsurerRepository insurerRepository;

	@Autowired
	private ICriteria<CustomerQuery, Insurer> iCriteriaInsurer;

	@Autowired
	private ApplicationConfiguration config;

	@Autowired
	private PostCodeRepository postCodeRepository;

	@Autowired
	private OccupationRepository occupationRepository;

	private static List<Insurer> insurers;

	public List<Insurer> findAllInsurers() {
		return insurerRepository.findAll();
	}

	public List<PostCode> findAllPostCodes() {
		return postCodeRepository.findAll();
	}

	public List<Occupation> findAllOccupations() {
		return occupationRepository.findAll();
	}

	public Insurer findById(long id) throws Exception {
		try {
			return insurerRepository.findOne(id);
		} catch (Exception e) {
			throw new InsurerNotFoundException(
					"No Insurer with provided name exist in DB!");
		}

	}

	public Insurer findByName(String name) throws Exception {
		if (insurerRepository.findByName(name) != null)
			return insurerRepository.findByName(name).get(0);
		else
			throw new InsurerNotFoundException(
					"No Insurer with provided name exist in DB!");
	}

	public List<PostCode> findPostCodeByCode(String postCode) {

		List<PostCode> postCodefromDB = postCodeRepository
				.findByPostCode(postCode);

		if (postCodefromDB != null && postCodefromDB.size() > 0)
			return postCodefromDB;
		else
			return null;
	}

	public List<Occupation> findOccupationByName(String occupation) {

		List<Occupation> occupationfromDB = occupationRepository
				.findByOccupation(occupation);

		if (occupationfromDB != null && occupationfromDB.size() > 0)
			return occupationfromDB;
		else
			return null;
	}

	public void saveInsurer(Insurer insurer) {
		insurerRepository.save(insurer);

	}

	public void saveMultipleInsurers(List<Insurer> insurers) {
		insurerRepository.save(insurers);

	}

	/*
	 * This method checks the insurers against provided Business rules logic to
	 * determine the list of insurers on the panel
	 * 
	 * @see
	 * com.quotation.service.InsurerService#checkCriteriaAndReturnQuote(com.
	 * quotation.model.CustomerQuery)
	 */
	public Map<String, Double> checkCriteriaAndReturnQuote(CustomerQuery query)
			throws InsurerNotFoundException {

		List<Insurer> allInsurers = findAllInsurers();

		Map<String, Double> finalList = iCriteriaInsurer
				.checkCriteriaAndReturnQuote(query, allInsurers);
		if (finalList == null || finalList.isEmpty())
			throw new InsurerNotFoundException(
					"There are no eligible insurers for this Quote!");
		return finalList;

	}

	public void savePostCode(PostCode postCode) {
		postCodeRepository.save(postCode);
	}

	public void saveOccupation(Occupation occupation) {
		occupationRepository.save(occupation);
	}

	/*
	 * (non-Javadoc) TODO method to return all insurers with exclusion reason if
	 * applicable(TBD)
	 * 
	 * @see
	 * com.quotation.service.InsurerService#findAllInsurersforRequestedQuote
	 * (java.lang.String)
	 */
	public Map<Insurer, ExclusionReason> findAllInsurersforRequestedQuote(
			String postCode) {
		List<Insurer> allInsurers = findAllInsurers();
		List<Insurer> allInsurersWithExcludedPostCode = insurerRepository
				.findByExcludedPostCodes(postCode);
		Map<Insurer, ExclusionReason> insurersResult = new HashMap<Insurer, ExclusionReason>();
		for (Insurer i : allInsurers) {
			ExclusionReason reason = new ExclusionReason();

			if (allInsurersWithExcludedPostCode.contains(i)) {
				reason.setPostCode(i.getExcludedPostCodes());
				reason.setExcluded(true);
			}

			insurersResult.put(i, reason);
		}

		return insurersResult;
	}

	public void deleteAllInsurers() {
		insurers.clear();
	}

}
