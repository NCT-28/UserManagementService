package vn.com.nhomtruyen.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.com.nhomtruyen.domain.User;

/**
 * 
 * @author ToanNC7
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query(" SELECT u from User u WHERE u.id = :id ")
	Optional<User> findOneUserById(@Param("id") Long id);
	
	@Query(" SELECT u from User u WHERE u.login = :login ")
	User findOneUserByName(@Param("login") String login);
	
	@Query(" Select us FROM User us WHERE us.userInfo.id = :id")
	Optional<User> findByUserInfoByUserId(@Param("id") Long Id);
	
	@Query(" Select us FROM User us WHERE us.login LIKE %:login% "
			+ " and us.userInfo.fullName LIKE %:fullName% ")
	Page<User> findAllUser(Pageable pageable ,@Param("login") String userName, @Param("fullName") String fullName);
	
	@Query(value = "Select u.* " + 
			" FROM (((um_user u INNER JOIN um_group_user gu ON u.id = gu.user_id)" 
			+ " INNER JOIN um_group gr ON gr.id = gu.group_id ) " 
			+ " INNER JOIN um_user_info uf ON u.id = uf.user_id ) " 
			+ " WHERE gr.id= :id "
			+ "	and u.login LIKE %:login% "
			+ "	and uf.full_name Like %:fullname% "
			+ " ORDER BY :sortBy",nativeQuery = true)
	Page<User> getListUserByGroupId(Pageable pageable, @Param("id") Long id, @Param("login") String login, @Param("fullname") String fullName, @Param("sortBy") String sortBy);
	
	@Query(value = " SELECT us FROM User us WHERE us.login = :login")
    Optional<User> findOneWithAuthoritiesByLogin(@Param("login") String login);

}
