package vn.com.nhomtruyen.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.com.nhomtruyen.domain.Function;
import vn.com.nhomtruyen.domain.Role;
import vn.com.nhomtruyen.repository.FunctionRepository;
import vn.com.nhomtruyen.repository.RoleRepository;
import vn.com.nhomtruyen.service.RoleService;
import vn.com.nhomtruyen.service.dto.RoleDTO;
import vn.com.nhomtruyen.service.mapper.RoleMapper;
import vn.com.nhomtruyen.service.mess.RoleMess;

/**
 * 
 * @author ToanNC7
 *
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	private final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);
	private final RoleRepository roleRepository;
	private RoleMapper roleMapper;

	public RoleServiceImpl(RoleRepository roleRepository, FunctionRepository functionRepository) {
		super();
		this.roleRepository = roleRepository;
	}

	/**
	 * get one role by role id
	 */
	@Override
	@Transactional
	public Optional<Role> getOneRoleByID(Long id) {
		return roleRepository.findOneRoleByID(id);
	}

	/**
	 * 
	 */
	@Override
	public List<Role> getAllRole(Integer pageNo, Integer pageSize, String sort) {
		Pageable page = PageRequest.of(pageNo - 1, pageSize, Sort.by(Direction.ASC, sort));

		Page<Role> enties = roleRepository.findAll(page);

		if (enties.hasContent()) {
			return enties.getContent();
		} else {
			return new ArrayList<Role>();
		}
	}

	/**
	 * 
	 */

	@Override
	public RoleMess getAllRole(Integer pageNo, Integer pageSize, String name, String description, String sortType,
			String sortBy) {
		Pageable pageable = null;

		if (sortType.equals("ASC")) {
			pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Direction.ASC, sortBy));
		} else if (sortType.equals("DESC")) {
			pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Direction.DESC, sortBy));
		}

		Page<Role> enties = roleRepository.findByRole(pageable, name, description);
		List<Role> listRoles = enties.getContent();

		RoleMess roleMess = new RoleMess();
		roleMess.setMessage("get all role success!");
		roleMess.setListRoles(listRoles);
		roleMess.setTotalRoles(enties.getTotalElements());

		return roleMess;
	}

	/**
	 * Save of update Role.
	 */
	@Override
	public RoleDTO save(RoleDTO roleDTO) {
		log.debug("Request to save RoleDTO : {}", roleDTO);
		Role role = roleMapper.toEntity(roleDTO);
		role = roleRepository.save(role);
		return roleMapper.toDto(role);
	}

	/**
	 * Delete role by id.
	 */
	@Override
	public void deleteRoleById(Long id) {
		roleRepository.deleteById(id);
	}

}
