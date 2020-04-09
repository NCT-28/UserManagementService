package vn.com.nhomtruyen.service;

import java.util.Optional;

import vn.com.nhomtruyen.domain.Group;
import vn.com.nhomtruyen.service.dto.GroupDTO;
import vn.com.nhomtruyen.service.mess.GroupMess;
import vn.com.nhomtruyen.service.mess.RoleMess;
import vn.com.nhomtruyen.service.mess.UserMess;

public interface GroupService {

	GroupDTO saveAndUpdata(GroupDTO groupDTO);

	GroupMess getAllGroup(Integer pageNo, Integer pageSize, String name, String domainName, String description, String sortType, String sortBy);

	Optional<Group> getOneGroupByID(Long Id);
	
	UserMess getAllUserOfGroupId(Integer pageNo, Integer pageSize,Long id, String login, String fullName, String sortBy);
	
	RoleMess getAllRoleOfGroupId(Integer pageNo, Integer pageSize,Long id, String name, String description, String sortBy);
	
	int getTotalGroup();

	void deleteGroupById(Long id);

}