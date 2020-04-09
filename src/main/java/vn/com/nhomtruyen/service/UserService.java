package vn.com.nhomtruyen.service;

import java.util.Optional;

import vn.com.nhomtruyen.domain.User;
import vn.com.nhomtruyen.service.dto.UserDTO;
import vn.com.nhomtruyen.service.mess.FunctionMess;
import vn.com.nhomtruyen.service.mess.GroupMess;
import vn.com.nhomtruyen.service.mess.RoleMess;
import vn.com.nhomtruyen.service.mess.UserDetailMess;
import vn.com.nhomtruyen.service.mess.UserMess;

public interface UserService {

	UserDTO saveAndUpdate(UserDTO userDTO);

	UserMess getAllUser(Integer pageNo, Integer pageSize, String login, String fullName, String sortType,
			String sortBy);

	RoleMess getAllRoleOfUserId(Integer pageNo, Integer pageSize, Long id, String name, String description,
			String sortBy);

	GroupMess getALlGroupOfUserId(Integer pageNo, Integer pageSize, Long id, String name, String sortBy);
	
	UserDetailMess getAllFunctionOfUserId();
	

	Optional<User> getDetailsByUserID(Long id);

	void delete(Long id);
}
