package vn.com.nhomtruyen.service.mess;

import java.io.Serializable;
import java.util.List;

import vn.com.nhomtruyen.domain.Group;
/**
 * 
 * @author ToanNC7
 *
 */
public class GroupMess implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;
	
	private Long totalGroups;
	
	private List<Group> listGroups;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getTotalGroups() {
		return totalGroups;
	}

	public void setTotalGroups(Long totalGroups) {
		this.totalGroups = totalGroups;
	}

	public List<Group> getListGroups() {
		return listGroups;
	}

	public void setListGroups(List<Group> listGroups) {
		this.listGroups = listGroups;
	}


	
	
	
}
