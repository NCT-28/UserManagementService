package vn.com.nhomtruyen.service.mess;

import java.io.Serializable;

import vn.com.nhomtruyen.domain.Group;
/**
 * 
 * @author ToanNC7
 *
 */
public class GroupDetailsMess implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Group group;

	UserMess userMess;

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public UserMess getUserMess() {
		return userMess;
	}

	public void setUserMess(UserMess userMess) {
		this.userMess = userMess;
	}

}
