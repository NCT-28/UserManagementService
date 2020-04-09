package vn.com.nhomtruyen.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import vn.com.nhomtruyen.domain.Function;
import vn.com.nhomtruyen.domain.Role;
import vn.com.nhomtruyen.service.dto.RoleDTO;
import vn.com.nhomtruyen.service.mess.RoleMess;

public interface RoleService {


	Optional<Role> getOneRoleByID(Long id);

	List<Role> getAllRole(Integer pageNo, Integer pageSize, String sort);

	RoleMess getAllRole(Integer pageNo, Integer pageSize, String name, String description, String sortType, String sortBy);

	RoleDTO save(RoleDTO roleDTO);

	void deleteRoleById(Long id);
}
