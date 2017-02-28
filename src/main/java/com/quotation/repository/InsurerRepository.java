package com.quotation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quotation.entity.Insurer;
import com.quotation.entity.Occupation;
import com.quotation.entity.PostCode;

/**This repository contains findBy methods for insurer
 * @author MADHUMITA
 *
 */
@Repository
public interface InsurerRepository extends JpaRepository<Insurer, Long> {

	public List<Insurer> findByName(String name);
	
	public List<Insurer> findByExcludedPostCodes(@Param("postCode") String postCode);
	
	public List<Insurer> findByExcludedOccupations(@Param("occupation") String occupations);
	
	@Query(value="(select i.insurer_id,i.insurer_name,i.min_turnover from insurer i,post_code p,insurer_postcode ip where i.insurer_id= ip.insurer_id and p.postcode_id= ip.postcode_id and p.postcode=:postCode) INTERSECT (select i.insurer_id,i.insurer_name,i.min_turnover from insurer i, occupation o,insurer_occupation op where i.insurer_id= op.insurer_id and o.id= op.occupation_id and o.occupation=:occupation)",nativeQuery = true)
	public List<Insurer> findByExcludedOccupationsAndExcludedPostCodes(@Param("postCode") String postCode, @Param("occupation") String occupation);
	
	public List<Insurer> findByMinimumTurnOverLessThan(@Param("minimumTurnOver") long minimumTurnOver);
	
	public List<Insurer> findByMinimumTurnOverGreaterThan(@Param("minimumTurnOver") long minimumTurnOver);
	
}

