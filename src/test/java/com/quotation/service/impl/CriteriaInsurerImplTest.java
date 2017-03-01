package com.quotation.service.impl;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.quotation.TestDataUtil;

import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

import com.quotation.configuration.ApplicationConfiguration;
import com.quotation.entity.Insurer;
import com.quotation.model.CustomerQuery;
import com.quotation.repository.InsurerRepository;

/**This class contains unit tests for criteria check for Insurer
 * @author Madhumita
 *
 */
@RunWith(SpringRunner.class)
//@SpringBootTest
public class CriteriaInsurerImplTest {
	
	@InjectMocks
	CriteriaInsurerImpl criteriaInsurerImpl;
	
	@Mock
	private ApplicationConfiguration config;
	

	@Mock
	private InsurerServiceImpl insurerServiceImpl;
	
	@Mock
	private InsurerRepository insurerRepository;
	
	private static final Logger LOG = LoggerFactory
            .getLogger(CriteriaInsurerImplTest.class);
	
	 @Before
	 public void setup() throws Exception {
		 
		 MockitoAnnotations.initMocks(this);
		 List<String> mustCriteria = Arrays.asList("PostCode","Occupation");
		 List<String> shouldCriteria = Arrays.asList("less than minimumTurnOver");
		 when(config.getMustCriteria()).thenReturn(mustCriteria);
		 when(config.getShouldCriteria()).thenReturn(shouldCriteria);
		 
	 }
	
	 //Test to check where all insurers are eligible for panel
	 @Test
	 public void checkCriteriaAndReturnQuotewhereAllInsurersAreEligible() throws Exception {
		 
		 CustomerQuery query = TestDataUtil.getRequestQuery();
		 List<Insurer> excludedInsurers = new ArrayList<Insurer>();
		 when(insurerRepository.findByExcludedOccupationsAndExcludedPostCodes(anyString(),anyString())).thenReturn(excludedInsurers);
		 when(insurerRepository.findByMinimumTurnOverGreaterThan(Matchers.anyLong())).thenReturn(excludedInsurers);
			
		 List<Insurer> insurers = TestDataUtil.getInsurers();
		
		 Map<String,Double> map = criteriaInsurerImpl.checkCriteriaAndReturnQuote(query, insurers);
		 
		 assertNotNull(map);
		 assertEquals(2,map.size());
	  
	    }
	 
	 //Test to check where no insurers are eligible for Panel
	 @Test
	 public void checkCriteriaAndReturnQuotewhereNoInsurersAreEligible() throws Exception {
		 
		 CustomerQuery query = TestDataUtil.getRequestQuery();
		 List<Insurer> excludedInsurers = TestDataUtil.getInsurers();
		 when(insurerRepository.findByExcludedOccupationsAndExcludedPostCodes(anyString(),anyString())).thenReturn(excludedInsurers);
		 when(insurerRepository.findByMinimumTurnOverGreaterThan(Matchers.anyLong())).thenReturn(excludedInsurers);
			
		 List<Insurer> insurers = TestDataUtil.getInsurers();
		
		 Map<String,Double> map = criteriaInsurerImpl.checkCriteriaAndReturnQuote(query, insurers);
		 
		 assertNotNull(map);
		 assertEquals(0,map.size());
	  
	    }
	 
	 
	 //Test to check where insurer is excluded from panel due to minimumTurnOver criteria
	 @Test
	 public void checkCriteriaAndReturnQuotewhereCustomerHaslessMinimumTurnOver() throws Exception {
		 
		 CustomerQuery query = new CustomerQuery();
		 query.setName("MADHUMITA");
		 query.setOccupation("PAINTER");
		 query.setPostCode("2100");
		 query.setTurnOver(106000);
		 
		 List<Insurer> insurers = TestDataUtil.getInsurers();
		 List<Insurer> excludedInsurers = TestDataUtil.getExInsurersWithGreaterTurnOver();
		 when(insurerRepository.findByMinimumTurnOverGreaterThan(Matchers.anyLong())).thenReturn(excludedInsurers);
			
		 		
		 Map<String,Double> map = criteriaInsurerImpl.checkCriteriaAndReturnQuote(query, insurers);
		 
		 assertNotNull(map);
		 assertEquals(1,map.size());
	       
	  
	    }
	 
	 //Test to check where insurer is excluded from panel due to postCode and Occupation criteria
	 @Test
	 public void checkCriteriaAndReturnQuotewhereCustomerHasExcludedPostcodeandOccupation() throws Exception {
		 
		 CustomerQuery query = new CustomerQuery();
		 query.setName("MADHUMITA");
		 query.setOccupation("PAINTER");
		 query.setPostCode("2100");
		 query.setTurnOver(156000);
		 
		 List<Insurer> excludedInsurers = TestDataUtil.getInsurersWithExcludedPostCodeAndOccupation();
		 List<Insurer> excludedInsurersforTurnOver = TestDataUtil.getExInsurersWithGreaterTurnOver();
		 when(insurerRepository.findByExcludedOccupationsAndExcludedPostCodes(anyString(),anyString())).thenReturn(excludedInsurers);
		 
		 List<Insurer> insurers = TestDataUtil.getInsurers();
		
		 Map<String,Double> map = criteriaInsurerImpl.checkCriteriaAndReturnQuote(query, insurers);
		 
		 assertNotNull(map);
		 assertEquals(1,map.size());
	       
	  
	    }
	 
	 
	 
	
	

}
