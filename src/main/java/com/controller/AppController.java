package com.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bean.UserBean;
import com.dao.UserDao;

//import com.bean.UserBean;

@Controller
public class AppController {
	
	@Autowired
	UserDao userDao;
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		System.out.println("calling login....");
		//model.addAttribute("user", new UserBean());
		return "Login";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		System.out.println("calling signup....");
		model.addAttribute("user", new UserBean());
		return "Signup";
	}
	@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
	public String saveUser(@Valid @ModelAttribute("user") UserBean user, BindingResult result, Model model) {

		// local
		if (result.hasErrors()) {
			model.addAttribute("user", user);
			return "Signup";
		} else {
			//userDao.insertUser(user);
			
			userDao.saveUser(user);
			System.out.println("save user-------------------");

			model.addAttribute("myUser", user);
			return "Home";//
		}
	}
	
	
	@RequestMapping(value ="/searchUser", method = RequestMethod.POST)
	public String loginpage( @ModelAttribute("user") UserBean user, BindingResult result, Model model) {
		System.out.println("login");
		//System.out.println(email +password);
		UserBean result2=userDao.finduser(user);
		if(result2!=null)
		{
			return "Home";
		}
		return "Login";
	}
}
