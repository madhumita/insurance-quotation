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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**Entity to map PostCode in DB
 * @author MADHUMITA
 *
 */
@Entity
public class PostCode implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "POSTCODE_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "POSTCODE", unique = true)
	private String postCode;

	@ManyToMany(mappedBy = "excludedPostCodes", cascade = { CascadeType.ALL })
	@JsonIgnore
	private Set<Insurer> insurers = new HashSet<Insurer>();

	public PostCode() {
	}

	public PostCode(String postCode) {
		this.postCode = postCode;
	}

	public Set<Insurer> getInsurers() {
		return insurers;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

}
