package com.quotation.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**Entity to map the Occupation in DB
 * @author MADHUMITA
 *
 */
@Entity
public class Occupation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="OCCUPATION",unique=true)
	private String occupation;
	
	@ManyToMany(mappedBy = "excludedOccupations")
	@JsonIgnore
	private Set<Insurer> insurers = new HashSet<Insurer>();	
	
	public Occupation() {}
	public Occupation(String occupation) {
		this.occupation = occupation;
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

	public String getOccupation() {
		return occupation;
	}
	
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	@Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Occupation)) {
            return false;
        }

        Occupation occupation = (Occupation) o;

        return occupation.occupation.equals(occupation);
                
    }

}
