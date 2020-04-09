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
import io.github.jhipster.web.util.ResponseUtil;
import vn.com.nhomtruyen.service.GroupService;
import vn.com.nhomtruyen.service.dto.GroupDTO;
import vn.com.nhomtruyen.service.mess.GroupMess;
import vn.com.nhomtruyen.service.mess.RoleMess;
import vn.com.nhomtruyen.service.mess.UserMess;
import vn.com.nhomtruyen.web.rest.errors.BadRequestAlertException;
/**
 * 
 * Rest Controller Group.
 * @author ToanNC7
 * @version 1.0 - 04/01/2020
 */
@RestController
@RequestMapping(value = "/api/")
public class GroupResource {
	
	@Value("${jhipster.clientApp.name}")
	private String applicationName;
	
	private final Logger log = LoggerFactory.getLogger(GroupResource.class);
	
	private static final String ENTITY_NAME = "groupsGroup";
	private final GroupService groupService;
	
	
	/**
	 * Constructor
	 * @param groupService
	 */
	public GroupResource(GroupService groupService) {
		super();
		this.groupService = groupService;
	}

	/**
	 * {@code POST  /groups} : Create a new groups.
	 *
	 * @param groupDTO the GroupDTO to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new groupDTO, or with status {@code 400 (Bad Request)} if the
	 *         quote has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */

	@PostMapping("/groups")
	public ResponseEntity<GroupDTO> createGroup(@Valid @RequestBody GroupDTO groupDTO) throws URISyntaxException {
		log.debug("REST request to create group : {}");
		
		if(groupDTO.getId()!=null) {
			throw new BadRequestAlertException("A new group cannot already have an ID", ENTITY_NAME, "idexists");
		}
		
		GroupDTO group = groupService.saveAndUpdata(groupDTO);
		return ResponseEntity
				.created(new URI("/api/groups" + group.getId())).headers(HeaderUtil
						.createEntityCreationAlert(applicationName, true, ENTITY_NAME, group.getId().toString()))
				.body(group);
	}

	/**
	 * {@code Put  /groups} : Update a new roles.
	 * 
	 * @param groupDTO
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new groupDTO, or with status {@code 400 (Bad Request)} 
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */

	@PutMapping("/groups")
	public ResponseEntity<GroupDTO> updateGroup(@Valid @RequestBody GroupDTO groupDTO) throws URISyntaxException {
		log.debug("REST request to update group : {}");
		
		if(groupDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idNull");
		}
		
		GroupDTO group = groupService.saveAndUpdata(groupDTO);
		return ResponseEntity
				.created(new URI("/api/groups" + group.getId())).headers(HeaderUtil
						.createEntityCreationAlert(applicationName, true, ENTITY_NAME, group.getId().toString()))
				.body(group);
	}

	/**
	 * 
	 * {@code Get  /groups} get all group: filter, paging, sort.
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param groupName
	 * @param domainNameGroup
	 * @param GroupDescription
	 * @param sortType
	 * @param sortBy
	 * @return  "groups": [list group ],
	 * 			"message": "string",
	 * 			"totalGroup": 0
	 */

	@GetMapping("/groups")
	public ResponseEntity<GroupMess> getAllGroup(
			@RequestParam(defaultValue = "1") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "") String groupName,
			@RequestParam(defaultValue = "") String domainNameGroup,
			@RequestParam(defaultValue = "") String groupDescription,
			@RequestParam(defaultValue = "ASC") String sortType, 
			@RequestParam(defaultValue = "name") String sortBy) {
		GroupMess groupMess = groupService.getAllGroup(pageNo, pageSize, groupName, domainNameGroup, groupDescription,
				sortType, sortBy);

		return new ResponseEntity<>(groupMess, new HttpHeaders(), HttpStatus.OK);
	}

	/**
	 * 
	 * {@code Get  /groups} get all user of group by group id: filter, paging, sort.
	 * 
	 * @param id
	 * @param pageNo
	 * @param pageSize
	 * @param userName
	 * @param fullName
	 * @param sortBy
	 * @return {
	 * 			"message": "", 
	 * 			"totalUser": 0,
	 * 			"listUser": [List user]
	 * 			}
	 */
	@GetMapping(value = "/groups/{id}/users")
	public ResponseEntity<UserMess> getAllUserByGroupId(
			@PathVariable Long id,
			@RequestParam(defaultValue = "1") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "") String login,
			@RequestParam(defaultValue = "") String fullName,
			@RequestParam(defaultValue = "login") String sortBy) {

		log.debug("REST request to get list user by group id : {}", id);
		UserMess enties = groupService.getAllUserOfGroupId(pageNo, pageSize, id, login, fullName, sortBy);
		return new ResponseEntity<>(enties, new HttpHeaders(), HttpStatus.OK);
	}

	/**
	 * 
	 * {@code Get  /groups} get all user of group by group id: filter, paging, sort.
	 * 
	 * @param id
	 * @param pageNo
	 * @param pageSize
	 * @param userName
	 * @param fullName
	 * @param sortBy
	 * @return {
	 * 			"message": "", 
	 * 			"totalRoles": 0,
	 * 			"listRoles": [List user]
	 * 			}
	 */
	@GetMapping(value = "/groups/{id}/roles")
	public ResponseEntity<RoleMess> getAllRoleByGroupId(
			@PathVariable Long id,
			@RequestParam(defaultValue = "1") Integer pageNo, 
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "") String roleNameGroup, 
			@RequestParam(defaultValue = "") String roleDescriptionGroup,
			@RequestParam(defaultValue = "name") String sortBy) {

		log.debug("REST request to get list role by group id : {}", id);

		RoleMess enties = groupService.getAllRoleOfGroupId(pageNo, pageSize, id, roleNameGroup, roleDescriptionGroup, sortBy);
		return new ResponseEntity<>(enties, new HttpHeaders(), HttpStatus.OK);
	}

	/**
	 * 
	 * {@code Get  /groups/{id} } get one group by group id
	 * 
	 * @param id
	 * @return groupDTO
	 */
	@GetMapping(value = "/groups/{id}")
	public ResponseEntity<GroupDTO> getGroupDetails(@PathVariable Long id) {
		log.debug("REST request to get User : {}", id);
		return ResponseUtil.wrapOrNotFound(groupService.getOneGroupByID(id).map(GroupDTO::new));
	}
	/**
	 * 
	 * {@code Delete  /groups{id} } delete group by group id 
	 * 
	 * @param id
	 * @return void
	 */
	@DeleteMapping(value = "/groups/{id}")
	public ResponseEntity<Void> deleteGroupId(@PathVariable Long id) {
		log.debug("REST request to delete group : {}", id);
		groupService.deleteGroupById(id);
		return ResponseEntity.noContent()
				.headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
				.build();
	}

}
