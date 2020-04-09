package vn.com.nhomtruyen.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 
 * @author ToanNC7
 *
 */
@Entity()
@Table(name = "um_user_info")
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

	@Size(max = 256)
	@Column(name = "full_name")
	private String fullName;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "initials")
	private String initials;

	@Column(name = "comment")
	private String comment;

	@Column(name = "mobile")
	private int mobile;

	
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


	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", fullName=" + fullName + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", initials=" + initials + ", comment=" + comment + ", mobile=" + mobile + "]";
	}

}
