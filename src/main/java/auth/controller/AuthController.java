package auth.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import auth.service.UserService;
import br.com.rino.util.JSFUtil;

@Controller("authController")
@Scope(WebApplicationContext.SCOPE_REQUEST)
@Transactional
public class AuthController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserService userService;
	private auth.model.User user;
	private String username;

	@PostConstruct
	public void init() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		username = userName;
	}

	public void logout() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletResponse response =  (HttpServletResponse)context.getResponse();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
		new SecurityContextLogoutHandler().logout(request, response, null);
		new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY).logout(request, response, null);
		JSFUtil.redirect("/rino-gallery/");
	}
	
	
	// ***************************
	// ***** GETTERS - SETTERS *****
	// ***************************

	public auth.model.User getUser() {
		return user;
	}

	public void setUser(auth.model.User user) {
		this.user = user;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	
}