package com.quotation.service;


import java.util.List;
import java.util.Map;

import com.quotation.entity.Insurer;
import com.quotation.entity.Occupation;
import com.quotation.entity.PostCode;
import com.quotation.model.CustomerQuery;
import com.quotation.model.ExclusionReason;

public interface InsurerService {
	
	Insurer findById(long id);
	
	Insurer findByName(String name);
	
	void saveInsurer(Insurer insurer);
		
	void savePostCode(PostCode postCode);
	
	void saveOccupation(Occupation occupation);
	
	void deleteAllInsurers();
	
	public List<Insurer> findAllInsurers();
	
	Map<Insurer,ExclusionReason> findAllInsurersforRequestedQuote(String postCode);
	
	public List<PostCode> findPostCodeByCode(String postCode);
	
	public List<Occupation> findOccupationByName(String occupation);
	
	public Map<String,Double> checkCriteriaAndReturnQuote(CustomerQuery query);
	
	
	
}
