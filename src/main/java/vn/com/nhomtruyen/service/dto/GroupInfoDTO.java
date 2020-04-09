package vn.com.nhomtruyen.service.dto;

import java.io.Serializable;
import java.util.List;

import vn.com.nhomtruyen.domain.Domain;
import vn.com.nhomtruyen.domain.Group;
import vn.com.nhomtruyen.domain.Role;
import vn.com.nhomtruyen.domain.User;

/**
 * 
 * @author ToanNC7
 *
 */
public class GroupInfoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;

	private Domain domain;

	private String name;

	private String description;
	
	private List<User> users;
	private List<Role> roles;

	public GroupInfoDTO() {
	}
    
    public GroupInfoDTO(Group group) {
    	this.id=group.getId();
    	this.name=group.getName();
    	this.domain=group.getDomain();
    	this.description=group.getDescription();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

    
}
