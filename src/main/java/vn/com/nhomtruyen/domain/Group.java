package vn.com.nhomtruyen.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
/**
 * 
 * @author ToanNC7
 *
 */
@Entity
@Table(name = "um_group")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Group extends AbstractAuditingEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "domain_id", nullable = false)
	private Domain domain;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	
	@ManyToMany
	@JoinTable(name = "um_group_user", joinColumns = {
			@JoinColumn(name = "group_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "user_id", referencedColumnName = "id") })
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<User> user;
	
	
	@ManyToMany
	@JoinTable(name = "um_group_role", joinColumns = {
			@JoinColumn(name = "group_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "id") })
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<Role> roles;


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

	public void setUser(List<User> user) {
		this.user = user;
	}


	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


	@Override
	public String toString() {
		return "Group [id=" + id + ", domain=" + domain + ", name=" + name + ", description=" + description + ", user="
				+ user + ", roles=" + roles + "]";
	} 
	
}
