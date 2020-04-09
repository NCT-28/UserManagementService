package vn.com.nhomtruyen.service.mess;

import java.io.Serializable;
import java.util.List;

import vn.com.nhomtruyen.domain.User;
/**
 * 
 * @author ToanNC7
 *
 */
public class UserMess implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;

	private Long totalUsers;
	
	private List<User> listUsers;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getTotalUsers() {
		return totalUsers;
	}

	public void setTotalUsers(Long totalUsers) {
		this.totalUsers = totalUsers;
	}

	public List<User> getListUsers() {
		return listUsers;
	}

	public void setListUsers(List<User> listUsers) {
		this.listUsers = listUsers;
	}

	

}
