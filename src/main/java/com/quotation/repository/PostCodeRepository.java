package com.quotation.repository;

import java.util.List;
import java.util.Map;




import java.util.Set;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quotation.entity.Insurer;
import com.quotation.entity.PostCode;

/**Data JPA Repository for PostCode
 * @author MADHUMITA
 *
 */
@Repository
public interface PostCodeRepository extends JpaRepository<PostCode, Long> {
	
	@Cacheable(value = "postCodeCache", cacheManager = "postCodeCacheManager")
	public List<PostCode> findByPostCode(@Param("postCode") String postCode);

}
