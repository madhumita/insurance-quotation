package com.quotation.repository;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quotation.entity.Occupation;
import com.quotation.entity.PostCode;

/**Data JPA Repository for Occupation
 * @author MADHUMITA
 *
 */
@Repository
public interface OccupationRepository extends JpaRepository<Occupation, Long> {
	
	//(value = "entities", cacheManager = "cacheManage")
	//@Cacheable
	public List<Occupation> findByOccupation(String occupation);


}
