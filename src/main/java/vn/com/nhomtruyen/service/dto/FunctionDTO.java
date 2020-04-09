package vn.com.nhomtruyen.service.dto;

import vn.com.nhomtruyen.domain.Function;
/**
 * EntityDTO Function.
 * 
 * @author ToanNC7
 *
 * @version 1.0 - 04/01/2020
 */
public class FunctionDTO {
	private Long id;
	
	private String name;
	
	private String description;

	public FunctionDTO() {
	}
	
	public FunctionDTO(Function function) {
		this.id=function.getId();
		this.name=function.getName();
		this.description=function.getDescription();
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
	
}
