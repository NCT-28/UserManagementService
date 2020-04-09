package vn.com.nhomtruyen.service.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.security.RandomUtil;
import vn.com.nhomtruyen.domain.Function;
import vn.com.nhomtruyen.domain.Group;
import vn.com.nhomtruyen.domain.Role;
import vn.com.nhomtruyen.domain.User;
import vn.com.nhomtruyen.domain.UserInfo;
import vn.com.nhomtruyen.repository.FunctionRepository;
import vn.com.nhomtruyen.repository.GroupRepository;
import vn.com.nhomtruyen.repository.RoleRepository;
import vn.com.nhomtruyen.repository.UserInfoRepository;
import vn.com.nhomtruyen.repository.UserRepository;
import vn.com.nhomtruyen.security.SecurityUtils;
import vn.com.nhomtruyen.service.UserService;
import vn.com.nhomtruyen.service.dto.UserDTO;
import vn.com.nhomtruyen.service.mapper.UserMapper;
import vn.com.nhomtruyen.service.mess.FunctionMess;
import vn.com.nhomtruyen.service.mess.GroupMess;
import vn.com.nhomtruyen.service.mess.RoleMess;
import vn.com.nhomtruyen.service.mess.UserDetailMess;
import vn.com.nhomtruyen.service.mess.UserMess;

/**
 * 
 * @author ToanNC7
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserInfoRepository userInfoRepository;
	private final UserMapper userMapper;
	private final RoleRepository roleRepository;
	private final GroupRepository groupRepository;
	private final FunctionRepository functionRepository;

	private final PasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository, UserInfoRepository userInfoRepository, UserMapper userMapper,
			RoleRepository roleRepository, GroupRepository groupRepository, PasswordEncoder passwordEncoder,
			FunctionRepository functionRepository) {
		super();
		this.userRepository = userRepository;
		this.userInfoRepository = userInfoRepository;
		this.userMapper = userMapper;
		this.roleRepository = roleRepository;
		this.groupRepository = groupRepository;
		this.passwordEncoder = passwordEncoder;
		this.functionRepository = functionRepository;
	}

	@Override
	public UserMess getAllUser(Integer pageNo, Integer pageSize, String login, String fullName, String sortType,
			String sortBy) {

		Pageable pageable = null;

		if (sortType.equals("ASC")) {
			pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Direction.ASC, sortBy));
		} else if (sortType.equals("DESC")) {
			pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Direction.DESC, sortBy));
		}

		Page<User> enties = userRepository.findAllUser(pageable, login, fullName);

		UserMess userMess = new UserMess();
		userMess.setTotalUsers(enties.getTotalElements());
		userMess.setListUsers(enties.getContent());
		userMess.setMessage("get all user success!");
		return userMess;
	}

	@Override
	public RoleMess getAllRoleOfUserId(Integer pageNo, Integer pageSize, Long id, String name, String description,
			String sortBy) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		Page<Role> enties = roleRepository.findAllRoleByUserId(pageable, id, name, description, "r." + sortBy);

		RoleMess roleMess = new RoleMess();
		roleMess.setMessage("get all role of user success");
		roleMess.setTotalRoles(enties.getTotalElements());
		roleMess.setListRoles(enties.getContent());
		return roleMess;
	}

	@Override
	public GroupMess getALlGroupOfUserId(Integer pageNo, Integer pageSize, Long id, String name, String sortBy) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		Page<Group> enties = groupRepository.findAddGroupByUserId(pageable, id, name, "gr." + sortBy);

		GroupMess groupMess = new GroupMess();

		groupMess.setMessage("Get all group of user id " + id + " success!");
		groupMess.setListGroups(enties.getContent());
		groupMess.setTotalGroups(enties.getTotalElements());

		return groupMess;
	}

	@Override
	public UserDetailMess getAllFunctionOfUserId() {
		Optional<User> user = SecurityUtils.getCurrentUserLogin()
				.flatMap(userRepository::findOneWithAuthoritiesByLogin);

		List<Group> listGroups = groupRepository.findAddGroupByUserId(user.get().getId());
		List<Role> listRole = new ArrayList<Role>();

		for (Group group : listGroups) {
			List<Role> listRoleGr = roleRepository.findAllRoleByGroupId(group.getId());
			for (Role role : listRoleGr) {
				if (!listRole.contains(role)) {
					listRole.add(role);
				}
			}

		}

		List<Role> listRoleRo = roleRepository.findAllRoleByUserId(user.get().getLogin());
		for (Role role : listRoleRo) {
			if (!listRole.contains(role)) {
				listRole.add(role);
			}
		}

		List<Function> listFunctions = new ArrayList<Function>();
		for (Role role : listRole) {
			List<Function> list = functionRepository.findAllFunctionByRoleId(role.getId());
			for (Function function : list) {
				if (!listFunctions.contains(function)) {
					listFunctions.add(function);
				}
			}
		}

		Set<String> authorities = listRole.stream().map(Role::getName).collect(Collectors.toSet());

		UserDetailMess userDetailMess = new UserDetailMess();
		userDetailMess.setUser(user);
		userDetailMess.setFunctions(listFunctions);
		userDetailMess.setAuthorities(authorities);
		return userDetailMess;
	}

	@Override
	public Optional<User> getDetailsByUserID(Long id) {
		return userRepository.findOneUserById(id);
	}

	@Override
	public UserDTO saveAndUpdate(UserDTO userDTO) {
		// Tạo mới 1 user và set các thông tin cho user get từ phía frontend gửi về.
		User user = new User();
		Long id= (userDTO.getId()==null) ? null: userDTO.getId();
		user.setId(id);
		user.setLogin(userDTO.getLogin());
		String password= (userDTO.getPassword()==null) ? "admin" : userDTO.getPassword();
			
		String encryptedPassword = passwordEncoder.encode(password);
		user.setPassword(encryptedPassword);
		user.setEmail(userDTO.getEmail());
		user.setImageUrl(userDTO.getImageUrl());
		user.setActivated(userDTO.isActivated());
		user.setLocked(userDTO.isLocked());
		user.setCanChange(userDTO.isCanChange());
		user.setMustChange(userDTO.isMustChange());
		user.setActivationKey(RandomUtil.generateResetKey());
		user.setResetKey(RandomUtil.generateResetKey());
		user.setResetDate(Instant.now());
		user.setRole(userDTO.getRole());

		// save and get ra 1 user mới khi đã lưu thành công trong db
		// Lưu đồng thời user và role của user vào db.
		User newUser = userRepository.save(user);

		// tạo và set thông tin cho user info với khóa ngoại từ user với tạo và save
		// user info vào db
		UserInfo userInfo = new UserInfo();
		userInfo = userDTO.getUserInfo();
		userInfo.setUser(newUser);
		// Get ra một userInfo khi đã lưu thành công vào db,
		UserInfo userInfo2 = userInfoRepository.save(userInfo);
		// set userInfo vào user để show ra cho ng dùng biết đã thêm user, userInfo và
		// các role của User đó.
		user.setUserInfo(userInfo2);

		return userMapper.toDto(user);
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

}
