package vn.com.nhomtruyen.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.com.nhomtruyen.domain.UserInfo;
/**
 * 
 * @author ToanNC7
 *
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

	@Query("SELECT u FROM UserInfo u WHERE u.user.id =:id")
	Optional<UserInfo> findByUserInfoByUserId(@Param("id") Long id);
}
