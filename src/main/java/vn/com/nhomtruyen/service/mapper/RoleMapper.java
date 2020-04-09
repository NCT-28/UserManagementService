package vn.com.nhomtruyen.service.mapper;

import org.mapstruct.Mapper;

import vn.com.nhomtruyen.domain.Role;
import vn.com.nhomtruyen.service.dto.RoleDTO;
/**
 * 
 * @author ToanNC7
 *
 */
@Mapper(componentModel = "spring", uses = {})
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {
	
	
	default Role fromId(Long id) {
		if (id == null) {
			return null;
		}
		Role role = new Role();
		role.setId(id);
		return role;
	}
}
