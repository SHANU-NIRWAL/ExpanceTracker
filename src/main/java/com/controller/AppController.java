package com.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bean.AccountBean;
import com.bean.UserBean;
import com.dao.UserDao;
import com.util.RandomNumber;

//import com.bean.UserBean;

@Controller
public class AppController {

	@Autowired
	UserDao userDao;
	private String Otpnum;
	private String Otpemail;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		System.out.println("calling login....");
		// model.addAttribute("user", new UserBean());
		return "Login";
	}
	@RequestMapping(value = "/logout")
	public String logout(Model model,HttpSession session) {
		System.out.println("calling login....");
		session.invalidate();
		// model.addAttribute("user", new UserBean());
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
			// userDao.insertUser(user);

			userDao.saveUser(user);
			System.out.println("save user-------------------");

			model.addAttribute("myUser", user);
			return "redirect:/login";//
		}
	}

	@RequestMapping(value = "/searchUser", method = RequestMethod.POST)
	public String loginpage(@ModelAttribute("user") UserBean user, BindingResult result, Model model,HttpSession session  ) {
		System.out.println("login");
		// System.out.println(email +password);
		UserBean rslt = userDao.finduser(user);
		// System.out.println(rslt.getRole().getRole());
		if (rslt == null) {
			return "Login";
		} else if (rslt.getRole().getRole().equals("admin")) {
			session.setAttribute("adminId", rslt.getUserId());
			List<UserBean> ltuser = userDao.getAllUsers();
			ltuser.stream().forEach(x -> {
				System.out.println(x);
			});
			model.addAttribute("userlist", ltuser);
			return "Dashboard";
		} else if (rslt.getRole().getRole().equals("user")) {
			session.setAttribute("userId", rslt.getUserId());
			return "Account";
		}
		return "Login";
	}

	@GetMapping("/users")
	public String listUsers(Model model) {
		model.addAttribute("userlist", userDao.getAllUsers());

		return "Dashboard";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deletebyId(@PathVariable("id") int id, Model model) {
		userDao.deleteuser(id);
		// model.addAttribute("user", new UserBean());
		return "redirect:/users";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editUser(@PathVariable("id") int id, Model model) {
		UserBean result = userDao.finduserbyid(id);
		model.addAttribute("user", result);
		return "Edit";
	}

	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	public String editUser(@ModelAttribute("user") UserBean user, Model model) {
		userDao.edituser(user);
		System.out.println("save edit-------------------");
		// model.addAttribute("user", new UserBean());
		return "Login";
	}

	@RequestMapping(value = "/forgetpswd", method = RequestMethod.GET)
	public String forgetPassword(Model model) {
		System.out.println("calling forgot password page....");
		// model.addAttribute("user", new UserBean());
		return "ForgotPassword";
	}

	@RequestMapping(value = "/sendOtp", method = RequestMethod.GET)
	public String OtpSend(@RequestParam("email") String email, Model model) {
		System.out.println(email);
		System.out.println("calling otp page....");
		//model.addAttribute("email", email);
		UserBean user = userDao.searchEmail(email);
		if (user != null) {
			Otpemail=email;
			Otpnum = RandomNumber.randomNumberGenerator();
			System.out.println(Otpnum);
	//		SendMail.sendingOtpByMail(Otpnum, email);
			return "Otp";
			// TODO Auto-generated method stub
		}
		return "ForgotPassword";
		

	}

	@RequestMapping(value = "/newPass", method = RequestMethod.GET)
	public String newPassword(@RequestParam("otp")String otp1,Model model) {
		System.out.println("calling otp page....");
		//System.out.println(email);
		if(otp1.equals(Otpnum)) {
			//model.addAttribute("email", email);
			return "NewPassword";	
		}
		
		
		return "Otp";
	}
	@RequestMapping(value = "/updatePass", method = RequestMethod.GET)
	public String updatePassword(@RequestParam("newPassword")String newPassword) {
		int res=userDao.updatePasswordInDb(newPassword,Otpemail);
		System.out.println(res);
		if(res==1)
			return "redirect:/login";
		else if(res==2)
			return "Account";
		
		return "NewPassword";
	}
	
	
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String accountPage(Model model,HttpSession session) {
		System.out.println("calling Account page....");
		//model.addAttribute("user", new UserBean());
		int userID=Integer.parseInt(session.getAttribute("userId")!=null?(session.getAttribute("userId").toString()):"0".toString());
		model.addAttribute("AccountType", userDao.list());
		model.addAttribute("useraccountdetails", userDao.getUserAccountDetails(userID));
		//userDao.getUserAccountDetails().stream().forEach(x->{System.out.println(x.toString());});
		
		return "Account";
	}

	@RequestMapping(value = "/accountinsert", method = RequestMethod.POST)
	public String accountInsert(@ModelAttribute("account")AccountBean account,Model model,HttpSession session) {
		System.out.println("inserting in account.....");
		int userID=Integer.parseInt(session.getAttribute("userId")!=null?(session.getAttribute("userId").toString()):"45".toString());
		model.addAttribute("AccountType", userDao.list());
		model.addAttribute("useraccountdetails", userDao.getUserAccountDetails(userID));
		//userDao.getUserAccountDetails().stream().forEach(x->{System.out.println(x.toString());});
		account.setUserId(userID);
		System.out.println	(account);	
		userDao.setAccount(account);
		return "redirect:/account";
	}
}
