package com.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.entity.User;
import com.entity.Role;
import com.service.IUserService;

@Named
@SessionScoped
public class LoginController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private IUserService userService;
	private User user;
	
	
	@PostConstruct
	public void init()
	{
		this.user = new User();
	}
	
	
	public String login() {
		String redirect = null;
		try {
			User user = this.userService.login(this.user);

			if (user != null) {
				if (user.getRole().equals(Role.ROLE_ADMIN)) {
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);
					redirect = "index?faces-redirect=true";
				} else if (user.getRole().equals(Role.ROLE_USER)) {
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);
					redirect = "index?faces-redirect=true";
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Credenciales incorrectas", "Credenciales incorrectas"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return redirect;
	}
	


	public void checkSesion() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			User user = (User) context.getExternalContext().getSessionMap().get("user");

			if (user == null) {
				context.getExternalContext().redirect("../index.xhtml");
			}

		} catch (Exception e) {

		}
	}
	
	public void closeSesion() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
	
	
	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


}
