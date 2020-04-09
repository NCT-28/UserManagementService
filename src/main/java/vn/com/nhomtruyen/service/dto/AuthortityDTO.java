package vn.com.nhomtruyen.service.dto;

import java.io.Serializable;
import java.util.List;

import com.sun.istack.NotNull;

import vn.com.nhomtruyen.domain.Function;
import vn.com.nhomtruyen.domain.Role;

public class AuthortityDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	private String name;
	
	private List<Function> function;

	public AuthortityDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthortityDTO(Role role) {

		this.name = role.getName();
		this.function= role.getFunctions();
	}
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Function> getFunction() {
		return function;
	}

	public void setFunction(List<Function> function) {
		this.function = function;
	}

}
