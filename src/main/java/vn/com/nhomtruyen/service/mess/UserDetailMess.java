package vn.com.nhomtruyen.service.mess;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import vn.com.nhomtruyen.domain.Function;
import vn.com.nhomtruyen.domain.User;

public class UserDetailMess {
	Optional<User> user;
	Set<String> authorities;
	List<Function> functions;
	public Optional<User> getUser() {
		return user;
	}
	public void setUser(Optional<User> user) {
		this.user = user;
	}
	public Set<String> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Set<String> authorities) {
		this.authorities = authorities;
	}
	public List<Function> getFunctions() {
		return functions;
	}
	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}
	
	
}
