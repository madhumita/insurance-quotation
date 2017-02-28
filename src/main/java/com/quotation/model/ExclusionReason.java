package com.quotation.model;

import java.util.List;
import java.util.Set;

import com.quotation.entity.Occupation;
import com.quotation.entity.PostCode;

/**
 * This POJO has been created for future planned api to retrieve excluded
 * insurers with reason of exclusion.
 * 
 * @author MADHUMITA
 *
 */
public class ExclusionReason {

	private Set<PostCode> postCode;

	private Set<Occupation> occupation;

	private String turnOver;

	private boolean isExcluded = false;

	public Set<PostCode> getPostCode() {
		return postCode;
	}

	public void setPostCode(Set<PostCode> postCode) {
		this.postCode = postCode;
	}

	public Set<Occupation> getOccupation() {
		return occupation;
	}

	public void setOccupation(Set<Occupation> occupation) {
		this.occupation = occupation;
	}

	public String getTurnOver() {
		return turnOver;
	}

	public void setTurnOver(String turnOver) {
		this.turnOver = turnOver;
	}

	public boolean isExcluded() {
		return isExcluded;
	}

	public void setExcluded(boolean isExcluded) {
		this.isExcluded = isExcluded;
	}

}
