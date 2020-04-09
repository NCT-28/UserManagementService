package vn.com.nhomtruyen.service.mapper;

import org.mapstruct.Mapper;

import vn.com.nhomtruyen.domain.Function;
import vn.com.nhomtruyen.service.dto.FunctionDTO;

@Mapper(componentModel = "spring", uses = {})
public interface FunctionMapper extends EntityMapper<FunctionDTO, Function> {
	default Function fromId(Long id) {
		if (id == null) {
			return null;
		}
		Function function = new Function();
		function.setId(id);
		return function;
	}
}
