package com.mkyong.web.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.impl.JdbcUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

import com.model.HmId;
import com.model.KeyDeactivation;
import com.model.OperationParameters;
import com.model.Partner;
import com.model.PartnerDAO;
import com.model.PartnerHistory;
import com.model.SerialNumber;
import com.model.User;
import com.model.UserDAO;


@Controller
public class HelloController {
	@Autowired
    private UserDAO userDAO;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String printMessage(HttpServletRequest request,ModelMap model, Principal principal,@RequestParam("uname") String uname) {
	
		System.out.println(uname);
		HttpSession session=request.getSession(true);
		session.setAttribute("uname", uname);
		//String username = principal.getName();
		if(null!=principal && null!= principal.getName()){
			model.addAttribute("user", principal.getName());
			return "home";
		}else if(null!=uname){
			model.addAttribute("user", uname);
			return "home";
		}
		else{
			return "login";
		}
	

	}

	
	@RequestMapping(value = "/key", method = RequestMethod.GET)
	public String requestKey(HttpServletRequest request,ModelMap model, Principal principal) {

		HttpSession session=request.getSession(false);
		if(null!=principal && null!= principal.getName()){
			model.addAttribute("user", principal.getName());
			ArrayList<String> serialnumbers=userDAO.getSerialNumbers();
			
			ArrayList<String> activeserialnumbers =userDAO.getActivatedSerialNumbers();
			serialnumbers.removeAll(activeserialnumbers);
			StringBuilder sb = new StringBuilder();
			
			for (String s : serialnumbers)
			{
			    sb.append(s);
			    sb.append(",");
			}
			System.out.println(sb);
			model.addAttribute("nfrkeys",sb);
			return "key";
		}else if(null!=session && null !=session.getAttribute("uname")){
			
		model.addAttribute("user", session.getAttribute("uname"));
		ArrayList<String> serialnumbers=userDAO.getSerialNumbers();
		ArrayList<String> activeserialnumbers =userDAO.getActivatedSerialNumbers();
		serialnumbers.removeAll(activeserialnumbers);
		StringBuilder sb = new StringBuilder();
		for (String s : serialnumbers)
		{
		    sb.append(s);
		    sb.append(",");
		}
		System.out.println(sb);
		model.addAttribute("nfrkeys",sb);
		return "key";
		
		}else{
			return "login";
		}
	}
	
	@RequestMapping(value = "/provision", method = RequestMethod.POST)
	public String saveSerialNumbers(@ModelAttribute("partner") Partner partner,HttpServletRequest request,Principal principal,ModelMap model) {
		
		HttpSession session=request.getSession(false);
		System.out.println(partner);
		if(null!=principal && null!= principal.getName()){
			String name=principal.getName();
			 PartnerDAO user=new PartnerDAO();
			 user.setEndUser(partner.getEndUser());
			 user.setHmType(partner.getHmType());
			
			 List<HmId> hmids=partner.getHmids();
			 user.setHmids(hmids);
			 List<SerialNumber> serialnos=partner.getSerialNumbers();
			 user.setSerialnumbers(serialnos);
			 
			 userDAO.saveDetails(user,name);
			 model.addAttribute("message", "Details saved Successfully!");
			 return "key";
		}else if(null!=session && null !=session.getAttribute("uname")){
			String name=(String) session.getAttribute("uname");
			 PartnerDAO user=new PartnerDAO();
			 user.setEndUser(partner.getEndUser());
			 user.setHmType(partner.getHmType());
			
			 List<HmId> hmids=partner.getHmids();
			
			 user.setHmids(hmids);
			 List<SerialNumber> serialnos=partner.getSerialNumbers();
			 user.setSerialnumbers(serialnos);
			 
			 userDAO.saveDetails(user,name);
			 model.addAttribute("message", "Details saved Successfully!");
			 return "key";
		}else{
			return "login";
		}

	}

/*	@RequestMapping(value = "/ng", method = RequestMethod.GET)
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

	}*/
	
	@RequestMapping(value ={"/history"}, method = RequestMethod.GET)
	public String getHistory(HttpServletRequest request,ModelMap model,Principal principal) {
		HttpSession session=request.getSession(false);
	 if(null!=session && null !=session.getAttribute("uname")){
			 model.addAttribute("user", session.getAttribute("uname"));
			 List<PartnerHistory> userlist= userDAO.getHistory((String) session.getAttribute("uname"));
			 System.out.println(userlist);
			 model.addAttribute("userlist",userlist);
			return "history";
		}else{
			return "login";
		}
		

	}
	
	
	@RequestMapping(value ={"/history"}, method = RequestMethod.POST)
	public String deactivateKey(@ModelAttribute("keydeactivation") KeyDeactivation detail,ModelMap model,HttpServletRequest request) {
		HttpSession session=request.getSession(false);
		//get the list using delimiter
		 StringTokenizer strTkn = new StringTokenizer(detail.getChck(), ",");
		   List<String> cancelKeys = new ArrayList<String>();

		   while(strTkn.hasMoreTokens()){
			   String token=strTkn.nextToken();
			   cancelKeys.add(token);
			   try{
				   String[] parts = token.split(";");
					String serialnumber = parts[0]; 
					String ek = parts[1];
			   }
			   catch(Exception e){
					session.setAttribute("error", "Please enter a valid Serial Number/Entitlement");
					return "redirect:history";
			  }
				
		   }
		   userDAO.deactivateKeys(cancelKeys);
		    		  
		return "redirect:history";

	}

/* @RequestMapping(value = "/failLogin", method = RequestMethod.GET)
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
	public String generateKey(ModelMap model,Principal principal) {
		if(null!=principal && null!= principal.getName()){
			
			 User user=new User();
			 String mail=principal.getName();
			 System.out.println("Principal Name :" +mail);
			 user.setEmail(mail);
			 if(userDAO.countUser(user)<=0){ 
				 userDAO.insert(user);	
			 }else{
				 model.addAttribute("error", "true");
			 }
			List<User> userlist= userDAO.fetch(user);
			model.addAttribute("userlist",userlist);
		}
		return "hmolkey";
	}*/

}