package vn.com.nhomtruyen.service.mess;

import java.io.Serializable;
import java.util.List;

import vn.com.nhomtruyen.domain.Role;
/**
 * 
 * @author ToanNC7
 *
 */
public class RoleMess implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;
	
	private long totalRoles;
	
	private List<Role> listRoles;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTotalRoles() {
		return totalRoles;
	}

	public void setTotalRoles(long totalRoles) {
		this.totalRoles = totalRoles;
	}

	public List<Role> getListRoles() {
		return listRoles;
	}

	public void setListRoles(List<Role> listRoles) {
		this.listRoles = listRoles;
	}


	
	
}
