package vn.com.nhomtruyen.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import vn.com.nhomtruyen.config.Constants;

/**
 * 
 * @author ToanNC7
 *	
 */
@Entity
@Table(name = "um_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User extends AbstractAuditingEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	private Long id;

	@NotNull
	@Pattern(regexp = Constants.LOGIN_REGEX)
	@Size(min = 1, max = 50)
	@Column(length = 50, unique = true, nullable = false)
	private String login;

	@NotNull
	@Size(min = 0, max = 60)
	@JsonIgnore
	@Column(name = "password_hash", length = 60, nullable = false)
	private String password;

	@Email
	@Size(min = 5, max = 254)
	@Column(length = 254, unique = true)
	private String email;

	@Size(max = 256)
	@Column(name = "image_url", length = 256)
	private String imageUrl;

	@NotNull
	@Column(nullable = false)
	private boolean activated = false;

	@NotNull
	@Column(name = "locked", nullable = false)
	private boolean locked = false;

	@NotNull
	@Column(name = "can_change", nullable = false)
	private boolean canChange = false;

	@NotNull
	@Column(name = "must_change", nullable = false)
	private boolean mustChange = false;

	@Size(max = 20)
	@Column(name = "activation_key", length = 20)
	@JsonIgnore
	private String activationKey;

	@Size(max = 20)
	@Column(name = "reset_key", length = 20)
	@JsonIgnore
	private String resetKey;

	@Column(name = "reset_date")
    @JsonIgnore
	private Instant resetDate = null;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "um_user_role", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "id") })
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@BatchSize(size = 20)
	private List<Role> role;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@BatchSize(size = 1)
	private UserInfo userInfo;

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
	
	

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public List<Role> getRole() {
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}

	

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + ", email=" + email + ", imageUrl="
				+ imageUrl + ", activated=" + activated + ", locked=" + locked + ", canChange=" + canChange
				+ ", mustChange=" + mustChange + ", activationKey=" + activationKey + ", resetKey=" + resetKey
				+ ", resetDate=" + resetDate + ", role=" + role + ", userInfo=" + userInfo + "]";
	}







}
