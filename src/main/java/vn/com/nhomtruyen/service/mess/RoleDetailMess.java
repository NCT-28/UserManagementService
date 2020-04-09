package vn.com.nhomtruyen.service.mess;

import vn.com.nhomtruyen.domain.Role;
/**
 * 
 * @author ToanNC7
 *
 */
public class RoleDetailMess {

	Role role;
	FunctionMess functionMess;
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public FunctionMess getFunctionMess() {
		return functionMess;
	}
	public void setFunctionMess(FunctionMess functionMess) {
		this.functionMess = functionMess;
	}

	
}
