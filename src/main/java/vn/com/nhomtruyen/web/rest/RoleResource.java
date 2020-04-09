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
import vn.com.nhomtruyen.domain.Role;
import vn.com.nhomtruyen.service.FunctionService;
import vn.com.nhomtruyen.service.RoleService;
import vn.com.nhomtruyen.service.dto.RoleDTO;
import vn.com.nhomtruyen.service.mess.FunctionMess;
import vn.com.nhomtruyen.service.mess.RoleDetailMess;
import vn.com.nhomtruyen.service.mess.RoleMess;
import vn.com.nhomtruyen.web.rest.errors.BadRequestAlertException;

/**
 * RestController Role.
 * 
 * @author ToanNC7
 *
 * @version 1.0 - 04/01/2020
 */
@RestController
@RequestMapping(value = "/api/")
public class RoleResource {

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	private final Logger log = LoggerFactory.getLogger(RoleResource.class);
	private static final String ENTITY_NAME = "rolesRole";

	private final RoleService roleService;
	private final FunctionService functionService;
	
	/**
	 * Constructor
	 * @param roleService, functionService.
	 */
	public RoleResource(RoleService roleService, FunctionService functionService) {
		super();
		this.roleService = roleService;
		this.functionService = functionService;
	}

	/**
	 * {@code POST  /roles} : Create a new roles.
	 *
	 * @param rolesDTO the roleDTO to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new roleDTO, or with status {@code 400 (Bad Request)} if the
	 *         quote has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */

	@PostMapping(value = "/roles")
	public ResponseEntity<RoleDTO> createRole(@Valid @RequestBody RoleDTO roleDTO) throws URISyntaxException {
		log.debug("REST request to create role {}");
		if(roleDTO.getId()!=null) {
			throw new BadRequestAlertException("A new role cannot already have an ID", ENTITY_NAME, "idexists");
		}

		RoleDTO role = roleService.save(roleDTO);
		return ResponseEntity
				.created(new URI("/api/roles/" + role.getId())).headers(HeaderUtil
						.createEntityCreationAlert(applicationName, true, ENTITY_NAME, role.getId().toString()))
				.body(role);
	}

	/**
	 * 
	 * @param roleDTO
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new roleDTO, or with status {@code 400 (Bad Request)} if the
	 *         quote has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping(value = "/roles")
	public ResponseEntity<RoleDTO> updateRole(@Valid @RequestBody RoleDTO roleDTO) throws URISyntaxException {
		log.debug("REST request to update role. {}");
		if(roleDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idNull");
		}
		RoleDTO role = roleService.save(roleDTO);
		return ResponseEntity
				.created(new URI("/api/roles/" + role.getId())).headers(HeaderUtil
						.createEntityCreationAlert(applicationName, true, ENTITY_NAME, role.getId().toString()))
				.body(role);
	}
	/**
	 * 
	 * @param pageNo   What is the slide? 0, 1, 2, 3 ...
	 * @param pageSize Number of elements per page default value 10,
	 * @return list <>
	 */
	@GetMapping(value = "/roles")
	public ResponseEntity<RoleMess> getAllRoles(
			@RequestParam(defaultValue = "1") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, 
			@RequestParam(defaultValue = "") String name,
			@RequestParam(defaultValue = "") String description, 
			@RequestParam(defaultValue = "ASC") String sortType,
			@RequestParam(defaultValue = "name") String sortBy) {
		log.debug("REST request to get all role {}");
		RoleMess enties = roleService.getAllRole(pageNo, pageSize, name, description, sortType, sortBy);
		return new ResponseEntity<>(enties, new HttpHeaders(), HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/roles/{id}")
	public ResponseEntity<RoleDetailMess> getRoleDetails(@PathVariable Long id,
			@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) {
		log.debug("REST request to get role details {}", id);

		Role role = roleService.getOneRoleByID(id).get();

		FunctionMess functionMess = functionService.getListFunctionByRoleId(pageNo, pageSize, id);
		RoleDetailMess enties = new RoleDetailMess();
			enties.setFunctionMess(functionMess);
			enties.setRole(role);
		return new ResponseEntity<>(enties, new HttpHeaders(), HttpStatus.OK);
	}

	/**
	 * 
	 * @param roleID
	 * @return void
	 */

	@DeleteMapping(value = "/roles/{roleID}")
	public ResponseEntity<Void> deleteRoleByID(@PathVariable Long roleID) {
		log.debug("REST request to delete Role : {}", roleID);
		roleService.deleteRoleById(roleID);
		return ResponseEntity.noContent()
				.headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, roleID.toString()))
				.build();
	}
}
