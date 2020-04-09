package vn.com.nhomtruyen.service.dto;

import java.io.Serializable;
import java.util.List;

import vn.com.nhomtruyen.domain.Function;
import vn.com.nhomtruyen.domain.Role;

/**
 * 
 * @author ToanNC7
 *
 */
public class RoleDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private String description;

	private List<Function> functions;

	public RoleDTO() {
		super();
	}
	public RoleDTO(Role role) {
		this.id=role.getId();
		this.name=role.getName();
		this.description=role.getDescription();
		this.functions=role.getFunctions();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}
	
	
}
