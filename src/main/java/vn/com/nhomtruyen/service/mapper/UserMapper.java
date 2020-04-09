package vn.com.nhomtruyen.service.mapper;

import org.mapstruct.Mapper;

import vn.com.nhomtruyen.domain.User;
import vn.com.nhomtruyen.service.dto.UserDTO;
/**
 * 
 * @author ToanNC7
 *
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserMapper extends EntityMapper<UserDTO, User> {

	default User fromId(Long id) {
		if (id == null) {
			return null;
		}
		User user = new User();
		user.setId(id);
		return user;
	}
}
