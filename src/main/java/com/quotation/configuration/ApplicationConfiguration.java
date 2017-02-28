package com.quotation.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.quotation.service.impl.CriteriaInsurerImpl;

/**This class reads values from properties files and sets Constants
 * @author Madhumita
 *
 */
@Configuration
public class ApplicationConfiguration {
	
	public static final String POSTCODE ="PostCode";
	public static final String OCCUPATION ="Occupation";
	public static final String MINIMUM_TURNOVER ="minimumTurnOver";
	public static final String LESS_THAN_MINIMUM_TURNOVER ="less than minimumTurnOver";
	public static final String GREATER_THAN_MINIMUM_TURNOVER ="greater than minimumTurnOver";
	public static final String EQUALS ="equals";
	public static final Double PRICE_QUOTE =200000.00;
    
    @Value("#{'${excluded.criteria.must}'.split(',')}")
    private List<String> mustCriteria;
    
    @Value("#{'${excluded.criteria.should}'.split(',')}")
    private List<String> shouldCriteria;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

	public List<String> getMustCriteria() {
		return mustCriteria;
	}

	public List<String> getShouldCriteria() {
		return shouldCriteria;
	}
	
	 

}