package com.quotation.service;

import java.util.List;
import java.util.Map;

import com.quotation.model.ExclusionReason;


public interface ICriteria<X,T>{
	
	public Map<String,Double> checkCriteriaAndReturnQuote(X x,List<T> t);

}
