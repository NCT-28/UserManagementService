package vn.com.nhomtruyen.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import vn.com.nhomtruyen.domain.Group;
import vn.com.nhomtruyen.domain.Role;
import vn.com.nhomtruyen.domain.User;
import vn.com.nhomtruyen.repository.GroupRepository;
import vn.com.nhomtruyen.repository.RoleRepository;
import vn.com.nhomtruyen.repository.UserRepository;
import vn.com.nhomtruyen.service.GroupService;
import vn.com.nhomtruyen.service.dto.GroupDTO;
import vn.com.nhomtruyen.service.mapper.GroupMapper;
import vn.com.nhomtruyen.service.mess.GroupMess;
import vn.com.nhomtruyen.service.mess.RoleMess;
import vn.com.nhomtruyen.service.mess.UserMess;

/**
 * 
 * @author ToanNC7
 *
 */
@Service
@Transactional
public class GroupServiceImpl implements GroupService {

	private final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

	private final GroupRepository groupRepository;
	private final GroupMapper groupMapper;
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	/**
	 * Constructor
	 * 
	 * @param groupRepository
	 * @param groupMapper
	 */
	public GroupServiceImpl(GroupRepository groupRepository, GroupMapper groupMapper, UserRepository userRepository,
			RoleRepository roleRepository) {
		super();
		this.groupRepository = groupRepository;
		this.groupMapper = groupMapper;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	/**
	 * 
	 */
	@Override
	public GroupDTO saveAndUpdata(GroupDTO groupDTO) {
		log.debug("Request to save Quote : {}", groupDTO);
		Group group = groupMapper.toEntity(groupDTO);
		group = groupRepository.save(group);
		return groupMapper.toDto(group);
	}

	/**
	 * 
	 */
	@Override
	public GroupMess getAllGroup(Integer pageNo, Integer pageSize, String name, String domainName, String description,
			String sortType, String sortBy) {
		Pageable pageable = null;
		if (sortType.equals("ASC")) {
			pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Direction.ASC, sortBy));
		} else if (sortType.equals("DESC")) {
			pageable = PageRequest.of(pageNo, pageSize, Sort.by(Direction.DESC, sortBy));
		}

		Page<Group> enties = groupRepository.findAllGroup(pageable, name, domainName, description);

		GroupMess groupMess = new GroupMess();
		groupMess.setMessage("get all group success!");
		groupMess.setListGroups(enties.getContent());
		groupMess.setTotalGroups(enties.getTotalElements());

		return groupMess;
	}

	/**
	 * 
	 */
	@Override
	public Optional<Group> getOneGroupByID(Long Id) {
		return groupRepository.findOneGroupById(Id);
	}

	@Override
	public UserMess getAllUserOfGroupId(Integer pageNo, Integer pageSize, Long id, String login, String fullName,
			String sortBy) {

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		Page<User> pageUser = null;
		if (sortBy.equals("login")) {
			pageUser = userRepository.getListUserByGroupId(pageable, id, login, fullName, "u." + sortBy);
		} else {
			pageUser = userRepository.getListUserByGroupId(pageable, id, login, fullName, "uf." + sortBy);
		}

		UserMess userMess = new UserMess();
		userMess.setListUsers(pageUser.getContent());
		userMess.setMessage("get all users of group id " + id + " success!");
		userMess.setTotalUsers(pageUser.getTotalElements());

		return userMess;
	}

	@Override
	public RoleMess getAllRoleOfGroupId(Integer pageNo, Integer pageSize, Long id, String name, String description,
			String sortBy) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		Page<Role> pageRole = roleRepository.findAllRoleByGroupId(pageable, id, name, description, "r." + sortBy);
		RoleMess roleMess = new RoleMess();
		roleMess.setMessage("get all role of group  id " + id + " success!");
		roleMess.setTotalRoles(pageRole.getTotalElements());
		roleMess.setListRoles(pageRole.getContent());
		return roleMess;
	}

	/**
	 * 
	 */
	@Override
	public int getTotalGroup() {
		List<Group> groups = groupRepository.findAll();
		return groups.size();
	}

	@Override
	public void deleteGroupById(Long id) {
		groupRepository.deleteById(id);
	}

}
