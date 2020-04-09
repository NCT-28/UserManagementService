package vn.com.nhomtruyen.service.dto;

import java.io.Serializable;

import vn.com.nhomtruyen.domain.UserInfo;

/**
 * 
 * @author ToanNC7
 *
 */
public class UserInfoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String fullName;

	private String firstName;

	private String lastName;

	private String initials;

	private String comment;
	
	private int mobile;
	
	
	public UserInfoDTO() {
		super();
	}


	public UserInfoDTO(UserInfo user) {
		this.id=user.getId();
		this.fullName=user.getFullName();
		this.firstName=user.getFirstName();
		this.lastName=user.getLastName();
		this.initials=user.getInitials();
		this.comment=user.getComment();
		this.mobile=user.getMobile();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getInitials() {
		return initials;
	}


	public void setInitials(String initials) {
		this.initials = initials;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public int getMobile() {
		return mobile;
	}


	public void setMobile(int mobile) {
		this.mobile = mobile;
	}
	

}
