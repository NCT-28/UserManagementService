package vn.com.nhomtruyen.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.com.nhomtruyen.domain.Role;
/**
 * 
 * @author ToanNC7
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	@Query(" SELECT r FROM Role r  WHERE r.name LIKE %:name%")
	Page<Role> findByRoleName(Pageable pageable, @Param("name") String name);

	@Query(" SELECT r FROM Role r  WHERE r.description LIKE %:name%")
	Page<Role> findByRoleDescription(Pageable pageable, @Param("name") String name);

	@Query("SELECT r FROM Role r  WHERE r.name LIKE %:name% " + " and r.description LIKE %:description% ")
	Page<Role> findByRole(Pageable pageable, @Param("name") String name, @Param("description") String description);

	@Query(" SELECT r from Role r WHERE r.id = :id")
	Optional<Role> findOneRoleByID(@Param("id") Long id);

	@Query(value = "Select r.*" 
			+ " FROM ((um_role r INNER JOIN um_group_role gro ON r.id = gro.role_id)"
			+ " INNER JOIN um_group gr ON gr.id = gro.group_id)" 
			+ " WHERE gr.id= :id " + " and r.name LIKE %:name%"
			+ " and r.description LIKE %:description%" 
			+ " ORDER By :soryBy", nativeQuery = true)
	Page<Role> findAllRoleByGroupId(Pageable pageable, @Param("id") Long id, @Param("name") String name,
			@Param("description") String description, @Param("soryBy") String soryBy);
	
	@Query(value = "Select r.*" 
			+ " FROM ((um_role r INNER JOIN um_group_role gro ON r.id = gro.role_id)"
			+ " INNER JOIN um_group gr ON gr.id = gro.group_id)" 
			+ " WHERE gr.id= :id ", nativeQuery = true)
	List<Role> findAllRoleByGroupId(@Param("id") Long id);
	
	
	@Query(value = "Select r.* "
			+" FROM ((um_role r INNER JOIN um_user_role ur ON r.id = ur.role_id)"
			+" INNER JOIN um_user us ON us.id = ur.user_id)" 
			+" WHERE us.id= :id"
			+" and r.name LIKE %:name%" 
			+" and r.description LIKE %:description%"
			+" ORDER By :soryBy", nativeQuery = true)
	Page<Role> findAllRoleByUserId(Pageable pageable, @Param("id") Long id, @Param("name") String name,
			@Param("description") String description, @Param("soryBy") String soryBy);
	
	@Query(value = "Select r.* "
			+" FROM ((um_role r INNER JOIN um_user_role ur ON r.id = ur.role_id)"
			+" INNER JOIN um_user us ON us.id = ur.user_id)" 
			+" WHERE us.login= :login", nativeQuery = true)
	List<Role> findAllRoleByUserId( @Param("login") String login);
	

}
