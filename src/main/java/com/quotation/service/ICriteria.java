package com.quotation.service;

import java.util.List;
import java.util.Map;


public interface ICriteria<X,T>{
	
	public Map<String,Double> checkCriteriaAndReturnQuote(X x,List<T> t);

}
