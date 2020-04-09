package vn.com.nhomtruyen.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.com.nhomtruyen.domain.Function;

/**
 * 
 * @author ToanNC7
 *
 */
@Repository
public interface FunctionRepository extends JpaRepository<Function, Long> {

	@Query(value = " SELECT f.* FROM " + " (( um_function f INNER JOIN um_role_function rf ON f.id = rf.function_id) "
			+ "	INNER JOIN um_role r ON r.id = rf.role_id) " + " WHERE r.id = :id ", nativeQuery = true)
	Page<Function> findAllFunctionByRoleId(Pageable pageable, @Param("id") Long id);
	
	@Query(value = " SELECT f.* FROM " + " (( um_function f INNER JOIN um_role_function rf ON f.id = rf.function_id) "
			+ "	INNER JOIN um_role r ON r.id = rf.role_id) " + " WHERE r.id = :id ", nativeQuery = true)
	List<Function> findAllFunctionByRoleId(@Param("id") Long id);

}
