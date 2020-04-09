package vn.com.nhomtruyen.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.com.nhomtruyen.domain.Function;
import vn.com.nhomtruyen.repository.FunctionRepository;
import vn.com.nhomtruyen.service.FunctionService;
import vn.com.nhomtruyen.service.dto.FunctionDTO;
import vn.com.nhomtruyen.service.mapper.FunctionMapper;
import vn.com.nhomtruyen.service.mess.FunctionMess;

/**
 * 
 * @author ToanNC7
 *
 */
@Service
@Transactional
public class FunctionServiceImpl implements FunctionService {

	private final Logger log = LoggerFactory.getLogger(FunctionServiceImpl.class);
	private final FunctionRepository functionRepository;
	private  FunctionMapper functionMapper;

	public FunctionServiceImpl(FunctionRepository functionRepository) {
		super();
		this.functionRepository = functionRepository;
	}

	@Override
	public FunctionDTO saveAndUpdate(FunctionDTO functionDTO) {
		log.debug("Request to save FunctionDTO : {}", functionDTO);
		Function function = functionMapper.toEntity(functionDTO);
		function = functionRepository.save(function);
		return functionMapper.toDto(function);
	}

	@Override
	public List<Function> getAllFunctions() {
		log.debug("Request to get all FUnctions: {}");
		return functionRepository.findAll();
	}

	@Override
	public FunctionMess getListFunctionByRoleId(Integer pageNo, Integer pageSize, Long id) {
		log.debug("Request to get list function by role id : {}", id);

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

		Page<Function> enties = functionRepository.findAllFunctionByRoleId(pageable, id);

		List<Function> listFunctions = enties.getContent();


		FunctionMess functionMess = new FunctionMess();
		functionMess.setListFunctions(listFunctions);
		functionMess.setMessage("get all function of role \" + id + \" success");
		functionMess.setTotalFunctions(enties.getTotalElements());

		return functionMess;
	}

	@Override
	public void delete(Long id) {
		functionRepository.deleteById(id);
	}

}
