package com.quotation.model;

import javax.validation.constraints.NotNull;

/**this bean models request from Customer api for quotation
 * Bean validation has been used to ensure that all fields are provided
 * @author MADHUMITA
 *
 */
public class CustomerQuery {
	
	@NotNull
	private String name;
	
	@NotNull
	private String postCode;
	
	@NotNull
	private String occupation;
	
	@NotNull
	private long turnOver;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public long getTurnOver() {
		return turnOver;
	}

	public void setTurnOver(long turnOver) {
		this.turnOver = turnOver;
	}

}
