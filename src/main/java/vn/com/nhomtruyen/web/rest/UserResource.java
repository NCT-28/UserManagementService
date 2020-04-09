package vn.com.nhomtruyen.web.rest;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.jhipster.web.util.HeaderUtil;
import vn.com.nhomtruyen.domain.User;
import vn.com.nhomtruyen.service.UserService;
import vn.com.nhomtruyen.service.dto.UserDTO;
import vn.com.nhomtruyen.service.mess.GroupMess;
import vn.com.nhomtruyen.service.mess.RoleMess;
import vn.com.nhomtruyen.service.mess.UserDetailMess;
import vn.com.nhomtruyen.service.mess.UserMess;
import vn.com.nhomtruyen.web.rest.errors.BadRequestAlertException;

/**
 * RestController User.
 * @author ToanNC7
 * @version 1.0 - 04/01/2020
 */
@RestController
@RequestMapping(value = "/api/")
public class UserResource {

	@Value("${jhipster.clientApp.name}")
	private String applicationName;
	
	private final Logger log = LoggerFactory.getLogger(UserResource.class);
	private static final String ENTITY_NAME = "usersUser";

	private final UserService userService;

	/**
	 * Constructor
	 * @param userService
	 */
	public UserResource(UserService userService) {
		super();
		this.userService = userService;
	}


	/**
	 * {@code POST  /users} : Create a new user.
	 *
	 * @param userDTO the userDTO to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new userDTO, or with status {@code 400 (Bad Request)} if
	 *         the quote has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/users")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) throws URISyntaxException {
		log.debug("REST request to create users : {}");
		if(userDTO.getId()!=null) {
			throw new BadRequestAlertException("A new user cannot already have an ID", ENTITY_NAME, "idexists");
		}
		UserDTO user = userService.saveAndUpdate(userDTO);
		return ResponseEntity
				.created(new URI("/api/users" + user.getId())).headers(HeaderUtil
						.createEntityCreationAlert(applicationName, true, ENTITY_NAME, user.getId().toString()))
				.body(user);
	}
	/**
	 * {@code PUT  /users} : Updates an existing users.
	 *
	 * @param userDTO the userDTO to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the updated userDTO, or with status {@code 400 (Bad Request)} if the
	 *         userDTO is not valid, or with status
	 *         {@code 500 (Internal Server Error)} if the userDTO couldn't be
	 *         updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/users")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO) throws URISyntaxException {
		log.debug("REST request to update users : {}");
		if(userDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idNull");
		}
		UserDTO user = userService.saveAndUpdate(userDTO);
		return ResponseEntity
				.created(new URI("/api/users" + user.getId())).headers(HeaderUtil
						.createEntityCreationAlert(applicationName, true, ENTITY_NAME, user.getId().toString()))
				.body(user);
	}
	/**
	 * 
	 * @param pageNo   What is the slide? 0, 1, 2, 3 ...
	 * @param pageSize Number of elements per page default value 10,
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of users in body.
	 */
	@GetMapping(value = "/users")
	public ResponseEntity<UserMess> getAllUser(
			@RequestParam(defaultValue = "1") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "") String login,
			@RequestParam(defaultValue = "") String fullName,
			@RequestParam(defaultValue = "ASC") String sortType,
			@RequestParam(defaultValue = "login") String sortBy) {
		
		log.debug("REST request to get list users : {}");
		
		UserMess userMess = userService.getAllUser(pageNo, pageSize, login, fullName, sortType, sortBy);
		
		return new ResponseEntity<>(userMess, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	/**
	 * 
	 * @param id
	 * @return get user info by id of user.
	 */
	
	@GetMapping(value = "/user/details/{id}")
	ResponseEntity<User> getDetialsByUser(@PathVariable Long id) {
		log.debug("REST request to get User details. : {}", id);
		User user= userService.getDetailsByUserID(id).get();	
		return new ResponseEntity<>(user, new HttpHeaders(), HttpStatus.OK);
	}
	
	/**
	 *  {@code DELETE  /user/:id/roles} : get all roles the "id" user.
	 * @param id
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} get list ???
	 * 
	 */
	@GetMapping(value = "/users/roles/{id}")
	public ResponseEntity<RoleMess> getAllRolesByUserId(
			@PathVariable Long id,
			@RequestParam(defaultValue = "1") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "") String nameRole,
			@RequestParam(defaultValue = "") String descriptionRole,
			@RequestParam(defaultValue = "name") String sortBy) {
		log.debug("REST request to get all roles User : {}", id);
		
		RoleMess roleMess= userService.getAllRoleOfUserId(pageNo, pageSize, id, nameRole, descriptionRole, sortBy);
		
		return new ResponseEntity<>(roleMess, new HttpHeaders(), HttpStatus.OK);
	}
	/**
	 * {@code DELETE  /user/:id/groups} : get all groups the "id" user.
	 * @param id
	 * @param pageNo
	 * @param pageSize
	 * @param nameGroup
	 * @param sortBy
	 * @return
	 */
	@GetMapping(value = "/users/groups/{id}")
	public ResponseEntity<GroupMess> getAllGroupsByUserId(
			@PathVariable Long id,
			@RequestParam(defaultValue = "1") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "") String nameGroup,
			@RequestParam(defaultValue = "name") String sortBy) {
		log.debug("REST request to get all group User : {}", id);
		
		GroupMess groupMess= userService.getALlGroupOfUserId(pageNo, pageSize, id, nameGroup,sortBy);
		
		return new ResponseEntity<>(groupMess, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/users/account")
	public ResponseEntity<UserDetailMess> getAccount() {
		log.debug("REST request to get all group User : {}");
		
		UserDetailMess userDetailMess= userService.getAllFunctionOfUserId();
		return new ResponseEntity<>(userDetailMess, new HttpHeaders(), HttpStatus.OK);
	}
	

	/**
	 * {@code DELETE  /user/:id} : delete the "id" user.
	 *
	 * @param id the id of the userDTO to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Void> deleteUserByID(@PathVariable Long id){
		log.debug("REST request to delete users : {}", id);
		userService.delete(id);
		return ResponseEntity.noContent()
				.headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME,id.toString()))
				.build();
	}

}
