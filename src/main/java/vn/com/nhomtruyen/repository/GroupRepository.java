package vn.com.nhomtruyen.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.com.nhomtruyen.domain.Group;

/**
 * 
 * @author ToanNC7
 *
 */
@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
	@Query(" SELECT gr FROM Group gr WHERE gr.id = :id")
	Optional<Group> findOneGroupById(@Param("id") Long Id);

	@Query(" SELECT gr FROM Group gr WHERE gr.name LIKE %:name% " + " and gr.domain.name LIKE %:domainName% "
			+ " and gr.description LIKE %:description% ")
	Page<Group> findAllGroup(Pageable pageable, @Param("name") String name, @Param("domainName") String domainName,
			@Param("description") String description);
	
	@Query(value = " SELECT gr.* "
			+" FROM ((um_group gr INNER JOIN um_group_user gu ON gr.id = gu.group_id) " 
			+" INNER JOIN um_user us ON us.id=gu.user_id) " 
			+" WHERE us.id = :id "
			+ "AND gr.name LIKE %:name%"
			+ " ORDER BY :sortBy" , nativeQuery = true)
	Page<Group> findAddGroupByUserId(Pageable pageable, @Param("id") Long id,@Param("name") String name, @Param("sortBy") String sortBy);
	
	@Query(value = " SELECT gr.* "
			+" FROM ((um_group gr INNER JOIN um_group_user gu ON gr.id = gu.group_id) " 
			+" INNER JOIN um_user us ON us.id=gu.user_id) " 
			+" WHERE us.id = :id" , nativeQuery = true)
	List<Group> findAddGroupByUserId(@Param("id") Long id);
	
}
