package vn.com.nhomtruyen.service;

import java.util.List;

import vn.com.nhomtruyen.domain.Function;
import vn.com.nhomtruyen.service.dto.FunctionDTO;
import vn.com.nhomtruyen.service.mess.FunctionMess;
import vn.com.nhomtruyen.service.mess.RoleDetailMess;

public interface FunctionService {
	
	FunctionDTO saveAndUpdate(FunctionDTO functionDTO);
	
	List<Function> getAllFunctions();
	FunctionMess getListFunctionByRoleId(Integer pageNo, Integer pageSize, Long id);
	void delete(Long id);
}
