package vn.com.nhomtruyen.service.mapper;

import org.mapstruct.Mapper;

import vn.com.nhomtruyen.domain.Group;
import vn.com.nhomtruyen.service.dto.GroupDTO;
/**
 * 
 * @author ToanNC7
 *
 */
@Mapper(componentModel = "spring", uses = {})
public interface GroupMapper extends EntityMapper<GroupDTO, Group> {
	default Group fromId(Long id) {
		if (id == null) {
			return null;
		}
		Group group = new Group();
		group.setId(id);
		return group;
	}
}
