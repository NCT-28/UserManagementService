package vn.com.nhomtruyen.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.com.nhomtruyen.domain.Domain;
/**
 * 
 * @author ToanNC7
 *
 */
@Repository
public interface DomainRepository extends JpaRepository<Domain,Long> {

	@Query(" SELECT dm FROM Group dm WHERE dm.id = :id ")
	Page<Domain> findAllGroupByDomainId(Pageable pageable, @Param("id") Long Id);
}
