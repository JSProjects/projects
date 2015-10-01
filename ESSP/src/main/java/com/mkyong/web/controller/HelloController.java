package com.mkyong.web.controller;

import java.security.Principal;
import java.util.List;

import org.impl.JdbcUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

import com.model.KeyRequest;
import com.model.User;
import com.model.UserDAO;

@Controller
public class HelloController {
	@Autowired
    private UserDAO userDAO;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String printMessage(ModelMap model, Principal principal) {

		//String username = principal.getName();
		if(null!=principal && null!= principal.getName()){
			model.addAttribute("user", principal.getName());
			return "home";
		}
		else{
			return "login";
		}

	}

	
	@RequestMapping(value = "/ng", method = RequestMethod.GET)
	public String fetchInstructions(ModelMap model, Principal principal) {

		//String username = principal.getName();
		if(null!=principal && null!= principal.getName()){
			model.addAttribute("user", principal.getName());
			return "instructions";
		}
		else{
			return "login";
		}

	}
	@RequestMapping(value ={"/", "/login"}, method = RequestMethod.GET)
	public String login(ModelMap model) {

		return "login";

	}

	@RequestMapping(value = "/failLogin", method = RequestMethod.GET)
	public String failedLogin(ModelMap model) {

		model.addAttribute("error", "true");
		return "login";

	}

	@RequestMapping(value = "/logoff", method = RequestMethod.GET)
	public String logoff(ModelMap model) {

		return "login";

	}
	
	@RequestMapping(value = "/HMOL", method = RequestMethod.GET)
	public String Key(ModelMap model,Principal principal) {
		if(null!=principal && null!= principal.getName()){
		 User user=new User();
		 String mail=principal.getName();
		 System.out.println("Principal Name :" +mail);
		 user.setEmail(mail);
		 List<User> userlist= userDAO.fetch(user);
		 model.addAttribute("userlist",userlist);
		}
		return "hmolkey";

	}
	
	@RequestMapping(value = "/generate", method = RequestMethod.GET)
	public String generateKey(@ModelAttribute("keyrequest") KeyRequest request,ModelMap model,Principal principal) {
		if(null!=principal && null!= principal.getName()){
			
			 User user=new User();
			 String mail=principal.getName();
			 System.out.println("Principal Name :" +mail);
			 user.setEmail(mail);
			 user.setHmType(request.getHmType());
			 if(userDAO.countUser(user)<=0){ 
				 userDAO.insert(user);	
			 }else{
				 model.addAttribute("error", "true");
			 }
			List<User> userlist= userDAO.fetch(user);
			model.addAttribute("userlist",userlist);
		}
		return "hmolkey";
	}

}