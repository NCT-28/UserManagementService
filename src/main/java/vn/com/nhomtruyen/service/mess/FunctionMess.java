package vn.com.nhomtruyen.service.mess;

import java.io.Serializable;
import java.util.List;

import vn.com.nhomtruyen.domain.Function;
/**
 * 
 * @author ToanNC7
 *
 */
public class FunctionMess implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;

	private long totalFunctions;

	private List<Function> listFunctions;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTotalFunctions() {
		return totalFunctions;
	}

	public void setTotalFunctions(long totalFunctions) {
		this.totalFunctions = totalFunctions;
	}

	public List<Function> getListFunctions() {
		return listFunctions;
	}

	public void setListFunctions(List<Function> listFunctions) {
		this.listFunctions = listFunctions;
	}



	

	

}
