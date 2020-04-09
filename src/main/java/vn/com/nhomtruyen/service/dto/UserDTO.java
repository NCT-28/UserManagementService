package vn.com.nhomtruyen.service.dto;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import vn.com.nhomtruyen.config.Constants;
import vn.com.nhomtruyen.domain.Domain;
import vn.com.nhomtruyen.domain.Role;
import vn.com.nhomtruyen.domain.User;
import vn.com.nhomtruyen.domain.UserInfo;

/**
 * 
 * @author ToanNC7
 *
 */
public class UserDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank
	@Pattern(regexp = Constants.LOGIN_REGEX)
	@Size(min = 1, max = 50)
	private String login;
	
	private String password;


	private String email;

	private String imageUrl;


	private boolean activated = false;


	private boolean locked = false;

	private boolean canChange = false;


	private boolean mustChange = false;


	private String activationKey;


	private String resetKey;

	private Instant resetDate = null;

	private List<Role> role;

	
	private UserInfo userInfo;


	public UserDTO() {
		// Empty constructor needed for Jackson.
	}

	public UserDTO(User user) {
		this.id = user.getId();
		this.login = user.getLogin();
		this.password=user.getPassword();
		this.email = user.getEmail();
		this.activated = user.isActivated();
		this.locked=user.isLocked();
		this.canChange=user.isCanChange();
		this.mustChange=user.isMustChange();
		this.imageUrl = user.getImageUrl();
		this.activationKey=user.getActivationKey();
		this.resetKey=user.getResetKey();
		this.resetDate=user.getResetDate();
		this.userInfo=user.getUserInfo();
		this.role = user.getRole();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public boolean isCanChange() {
		return canChange;
	}

	public void setCanChange(boolean canChange) {
		this.canChange = canChange;
	}

	public boolean isMustChange() {
		return mustChange;
	}

	public void setMustChange(boolean mustChange) {
		this.mustChange = mustChange;
	}

	public String getActivationKey() {
		return activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}

	public String getResetKey() {
		return resetKey;
	}

	public void setResetKey(String resetKey) {
		this.resetKey = resetKey;
	}

	public Instant getResetDate() {
		return resetDate;
	}

	public void setResetDate(Instant resetDate) {
		this.resetDate = resetDate;
	}

	public List<Role> getRole() {
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
}
