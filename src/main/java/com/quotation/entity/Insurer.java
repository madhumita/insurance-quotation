package com.quotation.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Entity to map the Insurer in DB
 * 
 * @author MADHUMITA
 *
 */
@Entity
@Table(name = "Insurer")
public class Insurer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "INSURER_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "INSURER_NAME")
	private String name;

	@Column(name = "INSURER_WEBSITE")
	private String website;

	@ManyToMany(targetEntity = PostCode.class, cascade = { CascadeType.ALL })
	@JoinTable(name = "INSURER_POSTCODE", joinColumns = { @JoinColumn(name = "INSURER_ID") }, inverseJoinColumns = { @JoinColumn(name = "POSTCODE_ID") })
	private Set<PostCode> excludedPostCodes = new HashSet<PostCode>();

	@ManyToMany(targetEntity = Occupation.class, cascade = { CascadeType.ALL })
	@JoinTable(name = "INSURER_OCCUPATION", joinColumns = { @JoinColumn(name = "INSURER_ID") }, inverseJoinColumns = { @JoinColumn(name = "OCCUPATION_ID") })
	private Set<Occupation> excludedOccupations = new HashSet<Occupation>();

	@Column(name = "MIN_TURNOVER")
	private long minimumTurnOver;

	public Insurer() {
	}

	public Insurer(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Set<PostCode> getExcludedPostCodes() {
		return excludedPostCodes;
	}

	public Set<Occupation> getExcludedOccupations() {
		return excludedOccupations;
	}

	public long getMinimumTurnOver() {
		return minimumTurnOver;
	}

	public void setMinimumTurnOver(long minimumTurnOver) {
		this.minimumTurnOver = minimumTurnOver;
	}

	@Override
	public boolean equals(Object o) {

		if (o == this)
			return true;
		if (!(o instanceof Insurer)) {
			return false;
		}

		Insurer insurer = (Insurer) o;

		return insurer.name.equals(name) && insurer.website == website
				&& insurer.minimumTurnOver == minimumTurnOver;

	}

}
