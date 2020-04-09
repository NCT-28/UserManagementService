package vn.com.nhomtruyen.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import io.github.jhipster.web.util.HeaderUtil;
import vn.com.nhomtruyen.domain.Function;
import vn.com.nhomtruyen.service.FunctionService;
import vn.com.nhomtruyen.service.dto.FunctionDTO;
import vn.com.nhomtruyen.web.rest.errors.BadRequestAlertException;

/**
 * RestController Function.
 * 
 * @author ToanNC7
 *
 * @version 1.0 - 04/01/2020
 */

@RestController
@RequestMapping("/api/")
public class FunctionResource {
	
	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	
	private final Logger log = LoggerFactory.getLogger(FunctionResource.class);
	private static final String ENTITY_NAME = "functionsFunction";

	private final FunctionService functionService;


	/**
	 * Constructor
	 * @param functionService
	 */
	public FunctionResource(FunctionService functionService) {
		super();
		this.functionService = functionService;
	}

	/**
	 * {@code POST  /functions} : Create a new functions.
	 *
	 * @param userDTO the FunctionDTO to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new FunctionDTO, or with status {@code 400 (Bad Request)} if
	 *         the quote has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/functions")
	public ResponseEntity<FunctionDTO> createFunction(@Valid @RequestBody FunctionDTO functionDTO) throws URISyntaxException {
		log.debug("REST request to create users : {}");
		if(functionDTO.getId()!=null) {
			throw new BadRequestAlertException("A new user cannot already have an ID", ENTITY_NAME, "idexists");
		}
		FunctionDTO function = functionService.saveAndUpdate(functionDTO);
		return ResponseEntity
				.created(new URI("/api/users" + function.getId())).headers(HeaderUtil
						.createEntityCreationAlert(applicationName, true, ENTITY_NAME, function.getId().toString()))
				.body(function);
	}
	/**
	 * {@code PUT  /functions} : Updates an existing functions.
	 *
	 * @param FunctionDTO the FunctionDTO to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the updated FunctionDTO, or with status {@code 400 (Bad Request)} if the
	 *         userDTO is not valid, or with status
	 *         {@code 500 (Internal Server Error)} if the userDTO couldn't be
	 *         updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/functions")
	public ResponseEntity<FunctionDTO> updateFunction(@Valid @RequestBody FunctionDTO functionDTO) throws URISyntaxException {
		log.debug("REST request to create users : {}");
		if(functionDTO.getId()!=null) {
			throw new BadRequestAlertException("A new user cannot already have an ID", ENTITY_NAME, "idexists");
		}
		FunctionDTO function = functionService.saveAndUpdate(functionDTO);
		return ResponseEntity
				.created(new URI("/api/users" + function.getId())).headers(HeaderUtil
						.createEntityCreationAlert(applicationName, true, ENTITY_NAME, function.getId().toString()))
				.body(function);
	}
	
	@GetMapping("/functions")
	public ResponseEntity<List<Function>> getAllFunctions() {
		log.debug("REST request to get all function : {}");
		List<Function> enties = functionService.getAllFunctions();
		return new ResponseEntity<>(enties, new HttpHeaders(), HttpStatus.OK);
	}	
	
	/**
	 * Delete function by function id.
	 * @param id
	 * @return void
	 * 
	 */

	@DeleteMapping(value = "/functions/{id}")
	public ResponseEntity<Void> deleteRoleByID(@PathVariable Long id) {
		log.debug("REST request to delete Function : {}", id);
		functionService.delete(id);
		return ResponseEntity.noContent()
				.headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
				.build();
	}
	
}
