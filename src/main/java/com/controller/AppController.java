package com.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bean.AccountBean;
import com.bean.AccountTypeBean;
import com.bean.BalanceBean;
import com.bean.CategoryBean;
import com.bean.ExpenseBean;
import com.bean.PayeeBean;
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
	private String ImageURL;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		System.out.println("calling login....");
		// model.addAttribute("user", new UserBean());
		return "Login";
	}

	@RequestMapping(value = "/logout")
	public String logout(Model model, HttpSession session) {
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

	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public String Addcategory(Model model) {
		//System.out.println("calling login....");
		// model.addAttribute("user", new UserBean());
		return "AddCategory";
	}

	@RequestMapping(value = "/categoryInset", method = RequestMethod.POST)
	public String Insertcategory(@ModelAttribute("category")CategoryBean cat,Model model) {
		System.out.println(cat);
		userDao.categoryInsert(cat);
		return "AddCategory";
	}

	@RequestMapping(value = "/subcategory", method = RequestMethod.GET)
	public String Subcategory(Model model) {
		//System.out.println("calling login....");
		// model.addAttribute("user", new UserBean());
		return "SubCategory";
	}

	@RequestMapping(value = "/accountrole", method = RequestMethod.GET)
	public String AccountRole(Model model) {
		System.out.println("calling login....");
		List<AccountTypeBean> list = userDao.list();
		model.addAttribute("list", list);
		// model.addAttribute("user", new UserBean());
		System.out.println(list);
		return "Add_Account_Type";
	}

	@RequestMapping(value = "/accountCrudAdmin", method = RequestMethod.GET)
	public String AccountAdmin(Model model) {
		System.out.println("calling login....");
		// model.addAttribute("user", new UserBean());
		return "Add_Account_Type";
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
	public String loginpage(@ModelAttribute("user") UserBean user, BindingResult result, Model model,
			HttpSession session) {
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
			return "redirect:/account";
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
		// model.addAttribute("email", email);
		UserBean user = userDao.searchEmail(email);
		if (user != null) {
			Otpemail = email;
			Otpnum = RandomNumber.randomNumberGenerator();
			System.out.println(Otpnum);
			// SendMail.sendingOtpByMail(Otpnum, email);
			return "Otp";
			// TODO Auto-generated method stub
		}
		return "ForgotPassword";

	}

	@RequestMapping(value = "/newPass", method = RequestMethod.GET)
	public String newPassword(@RequestParam("otp") String otp1, Model model) {
		System.out.println("calling otp page....");
		// System.out.println(email);
		if (otp1.equals(Otpnum)) {
			// model.addAttribute("email", email);
			return "NewPassword";
		}

		return "Otp";
	}

	@RequestMapping(value = "/updatePass", method = RequestMethod.GET)
	public String updatePassword(@RequestParam("newPassword") String newPassword) {
		int res = userDao.updatePasswordInDb(newPassword, Otpemail);
		System.out.println(res);
		if (res == 1)
			return "redirect:/login";
		else if (res == 2)
			return "Account";

		return "NewPassword";
	}

	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String accountPage(Model model, HttpSession session) {
		System.out.println("calling Account page....");
		// model.addAttribute("user", new UserBean());
		int userID = Integer.parseInt(
				session.getAttribute("userId") != null ? (session.getAttribute("userId").toString()) : "0".toString());
		model.addAttribute("AccountType", userDao.list());
		model.addAttribute("useraccountdetails", userDao.getUserAccountDetails(userID));
		userDao.getUserAccountDetails(userID).stream().forEach(x -> {
			System.out.println(x.toString());
		});

		return "Account";
	}

	@RequestMapping(value = "/accountinsert", method = RequestMethod.POST)
	public String accountInsert(@ModelAttribute("account") AccountBean account, Model model, HttpSession session) {
		System.out.println("inserting in account.....");
		int userID = Integer.parseInt(
				session.getAttribute("userId") != null ? (session.getAttribute("userId").toString()) : "45".toString());
		model.addAttribute("AccountType", userDao.list());
		model.addAttribute("useraccountdetails", userDao.getUserAccountDetails(userID));
		userDao.getUserAccountDetails(userID).stream().forEach(x -> {
			System.out.println(x.toString());
		});
		account.setUserId(userID);
		System.out.println(account);
		userDao.setAccount(account);
		return "redirect:/account";
	}

	@RequestMapping(value = "/expense", method = RequestMethod.GET)
	public String expenseForm(Model model, HttpSession session) {
		System.out.println("calling expense....");
		// model.addAttribute("user", new UserBean());
		int userID = Integer.parseInt(
				session.getAttribute("userId") != null ? (session.getAttribute("userId").toString()) : "45".toString());
		List<BalanceBean> acbean = userDao.getallaccountbyID(userID);
		List<PayeeBean> payebean = userDao.getallPayee();
		List<CategoryBean> ctbean=userDao.getallCategory();
		model.addAttribute("category", ctbean);
		model.addAttribute("payeedata", payebean);
		model.addAttribute("useraccount", acbean);
		return "Expence";
	}

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String expensevvalidation(Model model, HttpSession session) {
		System.out.println("calling expense....");
		// model.addAttribute("user", new UserBean());
		int userID = Integer.parseInt(
				session.getAttribute("userId") != null ? (session.getAttribute("userId").toString()) : "45".toString());
		List<BalanceBean> acbean = userDao.getallaccountbyID(userID);
		List<PayeeBean> payebean = userDao.getallPayee();
		model.addAttribute("payeedata", payebean);
		model.addAttribute("useraccount", acbean);
		model.addAttribute("error", "balance error");
		return "Expence";
	}

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public String testexpenseForm(@ModelAttribute("expense") ExpenseBean expbean,
			@RequestParam("expimage") CommonsMultipartFile file, Model model, HttpSession session) {
		System.out.println("calling testexpense....");
		System.out.println(expbean);
		// model.addAttribute("user", new UserBean());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getContentType());
		byte[] imageDate = file.getBytes();
		String path = session.getServletContext().getRealPath("/") + "WEB-INF" + File.separator + "images"
				+ File.separator + file.getOriginalFilename();
		System.out.println(path);
		expbean.setImage(path);

		try {
			FileOutputStream fos = new FileOutputStream(path);
			fos.write(imageDate);
			fos.close();

		} catch (IOException e) {
			System.out.println(e);
		}
		int userID = Integer.parseInt(
				session.getAttribute("userId") != null ? (session.getAttribute("userId").toString()) : "45".toString());
		expbean.setUserId(userID);
		// userDao.setexpense(expbean);
		System.out.println(expbean);
		BalanceBean balacebean = userDao.getamountbyuserIDandaccountID(userID, expbean.getUseraccountID());
		if (balacebean != null && balacebean.getAmount() >= expbean.getAmmount()) {
			userDao.updatebalance(expbean);
			userDao.setexpense(expbean);
			userDao.setpayee(expbean.getPayeeName(), expbean.getUserId());
			return "redirect:/expenseDetails";
		} else {
			System.out.println("error..........................>>");
			return "redirect:/error";

		}

	}

	@RequestMapping(value = "/expenseDetails")
	public String expenseDetails(Model model, HttpSession session) {
		System.out.println("expense details....................................>>");
		int userID = Integer.parseInt(
				session.getAttribute("userId") != null ? (session.getAttribute("userId").toString()) : "45".toString());
		List<ExpenseBean> result = userDao.getAllExpenseDetails(userID);
		result.stream().forEach(x -> {
			System.out.println(x);
			System.out.println("expense test in loop");
		});

		model.addAttribute("expenseDetails", result);
		System.out.println("expense test");
		return "ExpenseDetails";
	}

	@RequestMapping(value = "/viewImage", method = RequestMethod.POST)
	public String viewImages(@RequestParam("imageurl") String image2, Model model) {
		System.out.println("viewImage...>>>>" + image2);
		model.addAttribute("imageurl", image2);
		return "ViewImages";
	}
	
	
	
	
	@RequestMapping(value="/payeecategory/{payeeName}" , method = RequestMethod.GET, produces = { "application/xml", "text/xml" }, consumes = MediaType.ALL_VALUE)
	@ResponseBody
	public List<CategoryBean> getallCategoryBean(@PathVariable("payeeName") String payeeName)
	{
	 	List<CategoryBean> result=userDao.getallCategoryByPayeeName(payeeName);
	 	List<String>rslt=new ArrayList<>();
	 	
	 	result.stream().forEach(x->rslt.add(x.getCatName()));
	 	
	 	return result;
	 	
	}
	
	@ResponseBody
	@RequestMapping(value="/testcat")
	public String testcategory( )
	{
		//System.out.println(value);
		return "hellow world";
	}

}