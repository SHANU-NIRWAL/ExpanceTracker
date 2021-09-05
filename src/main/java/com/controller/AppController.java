package com.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.bean.RoleBean;
import com.bean.SubCategoryBean;
import com.bean.UserBean;
import com.dao.UserDao;
import com.emailConfig.SendMail;
import com.google.gson.Gson;
import com.util.PdfGenerator;
import com.util.RandomNumber;

//import com.bean.UserBean;

@Controller
public class AppController {

	@Autowired
	UserDao userDao;
	private String Otpnum;
	private String Otpemail;
	private String ImageURL;
	private int userIdforAdmin;
	
	
	
	@RequestMapping(value = "/expensereposrt", method = RequestMethod.GET)
	public String expenseReport(Model model) {
		System.out.println("calling ShowDateWiseData....");
		// model.addAttribute("user", new UserBean());
		return "ShowDateWiseData";
	}

	@GetMapping("/checkemail/{email}")
	@ResponseBody
	public boolean checkEmail(@PathVariable("email") String email) {
		boolean ans = userDao.checkDuplicateEmail(email);
		return ans;//
	}

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
	public String Addcategory(Model model,HttpSession session) {
		int adminID = Integer.parseInt(
				session.getAttribute("adminId") != null ? (session.getAttribute("adminId").toString()) : "0".toString());
		if(adminID!=0)
		{
		model.addAttribute("role", userDao.getRole());
		return "AddCategory";
		}
		return "redirect:/login";
	}

	@RequestMapping(value = "/categoryInset", method = RequestMethod.POST)
	public String Insertcategory(@ModelAttribute("category") RoleBean role1, Model model) {
		userDao.insertRole(role1);
		// System.out.println(cat);
		model.addAttribute("role", userDao.getRole());
		return "redirect:/category";
	}

	@RequestMapping(value = "/subcategory", method = RequestMethod.GET)
	public String Subcategory(Model model) {
		// System.out.println("calling login....");
		// model.addAttribute("user", new UserBean());
		return "SubCategory";
	}

	@RequestMapping(value = "/accountrole", method = RequestMethod.GET)
	public String AccountRole(Model model,HttpSession session) {
		System.out.println("calling account type....");
		int adminID = Integer.parseInt(
				session.getAttribute("adminId") != null ? (session.getAttribute("adminId").toString()) : "0".toString());
		System.out.println(adminID +"admin id..............>>");
					if(adminID!=0)
			{
		List<AccountTypeBean> list = userDao.list();
		model.addAttribute("list", list);
		return "Add_Account_Type";
			}
			else {
				return "redirect:/login";
			}
	}

	@RequestMapping(value = "/accountCrudAdmin", method = RequestMethod.POST)
	public String AccountAdmin(@ModelAttribute("accountType") AccountTypeBean AccountType, Model model) {
		System.out.println("admin crud ...>>");
		userDao.insertAccountType(AccountType);
		model.addAttribute("list", userDao.list());
		return "redirect:/accountrole";
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

		UserBean rslt = userDao.finduser(user);

		if (rslt == null) {
			int stat = 1;
			// model.addAttribute("res", "true");
			model.addAttribute("stat", stat);
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
	public String listUsers(Model model,HttpSession session) {
		int adminID = Integer.parseInt(
				session.getAttribute("adminId") != null ? (session.getAttribute("adminId").toString()) : "0".toString());
		if(adminID!=0)
		{
			
		model.addAttribute("userlist", userDao.getAllUsers());

		return "Dashboard";
		}
		return "redirect:/login";
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

	@RequestMapping(value = "/forgetpswd1", method = RequestMethod.GET)
	public String forgetPassword1(@RequestParam("stat") int stat, Model model) {
		System.out.println("calling forgot password page...." + stat);
		model.addAttribute("stat", stat);
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
			SendMail.sendingOtpByMail(Otpnum, email);
			return "Otp";
			// TODO Auto-generated method stub
		} else {
			int stat = 1;
			// model.addAttribute("res", "true");
			System.out.println("here..............");
			model.addAttribute("stat", stat);
			return "redirect:/forgetpswd1";
		}

	}

	@RequestMapping(value = "/newPass", method = RequestMethod.GET)
	public String newPassword(@RequestParam("otp") String otp1, Model model) {
		System.out.println("calling otp page....");
		// System.out.println(email);
		if (otp1.equals(Otpnum)) {
			// model.addAttribute("email", email);
			return "NewPassword";
		}
		model.addAttribute("stat", 1);
		return "Otp";
	}

	@RequestMapping(value = "/updatePass", method = RequestMethod.GET)
	public String updatePassword(@RequestParam("newPassword") String newPassword) {
		int res = userDao.updatePasswordInDb(newPassword, Otpemail);
		System.out.println(res);
		if (res == 1)
			return "redirect:/login";
		else if (res == 2)
			return "redirect:/login";

		return "NewPassword";
	}

	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String accountPage(Model model, HttpSession session) {
		System.out.println("calling Account page....");
		// model.addAttribute("user", new UserBean());
		int userID = Integer.parseInt(
				session.getAttribute("userId") != null ? (session.getAttribute("userId").toString()) : "0".toString());
		System.out.println(userID+"userID from account.....");
		if(userID!=0)
		{
		model.addAttribute("AccountType", userDao.list());
		model.addAttribute("useraccountdetails", userDao.getUserAccountDetails(userID));
		userDao.getUserAccountDetails(userID).stream().forEach(x -> {
			System.out.println(x.toString());
		});

		return "Account";
		}
		else {
			return "redirect:/login";
		}
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
				session.getAttribute("userId") != null ? (session.getAttribute("userId").toString()) : "0".toString());
		if(userID!=0)
		{
		List<BalanceBean> acbean = userDao.getallaccountbyID(userID);
		List<PayeeBean> payebean = userDao.getallPayee(userID);
		List<CategoryBean> ctbean = userDao.getallCategory();
		model.addAttribute("category", ctbean);
		model.addAttribute("payeedata", payebean);
		model.addAttribute("useraccount", acbean);
		model.addAttribute("accountSize", acbean.size());
		System.out.println("new account---->" + acbean.size());
		return "Expence";
		}
		else {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String expensevvalidation(Model model, HttpSession session) {
		System.out.println("calling expense....");
		// model.addAttribute("user", new UserBean());
		int userID = Integer.parseInt(
				session.getAttribute("userId") != null ? (session.getAttribute("userId").toString()) : "45".toString());
		List<BalanceBean> acbean = userDao.getallaccountbyID(userID);
		List<PayeeBean> payebean = userDao.getallPayee(userID);
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
				session.getAttribute("userId") != null ? (session.getAttribute("userId").toString()) : "0".toString());
		if(userID!=0)
		{
		List<ExpenseBean> result = userDao.getAllExpenseDetails(userID);
		result.stream().forEach(x -> {
			System.out.println(x);
			System.out.println("expense test in loop");
		});

		model.addAttribute("expenseDetails", result);
		System.out.println("expense test");
		return "ExpenseDetails";
		}
		else {
			return "redirect:/login";
		}
	}
	
	

	@RequestMapping(value = "/viewImage", method = RequestMethod.POST)
	public String viewImages(@RequestParam("imageurl") String image2, Model model) {
		System.out.println("viewImage...>>>>" + image2);
		model.addAttribute("imageurl", image2);
		return "ViewImages";
	}

	@RequestMapping(value = "/payeecategory/{payeeName}", method = RequestMethod.GET, produces = { "application/xml",
			"text/xml" }, consumes = MediaType.ALL_VALUE)
	@ResponseBody
	public List<CategoryBean> getallCategoryBean(@PathVariable("payeeName") String payeeName, HttpSession session) {
		System.out.println(payeeName);
		int userID = Integer.parseInt(
				session.getAttribute("userId") != null ? (session.getAttribute("userId").toString()) : "0".toString());
		
		List<CategoryBean> result = userDao.getallCategoryByPayeeName(payeeName, (userID==0)?userIdforAdmin:userID);
		List<String> rslt = new ArrayList<>();

		result.stream().forEach(x -> rslt.add(x.getCatName()));

		return result;

	}

	@ResponseBody
	@RequestMapping(value = "/testcat")
	public String testcategory() {
		// System.out.println(value);
		return "hellow world";
	}

	@RequestMapping(value = "/payeesubcategory/{categoryName}/{payeeName}", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getallsubCategory(@PathVariable("categoryName") String categoryName,@PathVariable("payeeName") String payeeName, HttpSession session) {
		int userID = Integer.parseInt(
				session.getAttribute("userId") != null ? (session.getAttribute("userId").toString()) : "0".toString());
		System.out.println(categoryName+"----get all subcategory");
		List<String> result = userDao.getallsubCategoryByCategoryName(categoryName, payeeName, (userID==0)?userIdforAdmin:userID);
//	        List<String>rslt=new ArrayList<>();
//	        
//	        result.stream().forEach(x->rslt.add(x.getCatName()));

		return result;

	}

	@RequestMapping(value = "/chartView")
	public String chartview(Model model, HttpSession session) {
		int userID = Integer.parseInt(
				session.getAttribute("userId") != null ? (session.getAttribute("userId").toString()) : "0".toString());
		if(userID!=0)
		{
		List<Map<Object, Object>> result = userDao.getALlusersubCategory(userID);
		
		Gson gsonObj = new Gson();
		System.out.println(gsonObj.toJson(result));
		System.out.println(result);
		String chartreport = gsonObj.toJson(result);
		model.addAttribute("chartreport", chartreport);
		System.out.println("string data");
		System.out.println(chartreport);
		return "ChartView";
		}
		else {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/expensegenerator")
	public String generateExpense(HttpSession session) {

		return "ExpenseReport";
	}

	@RequestMapping(value = "/downloadPDF", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
	@ResponseBody
	public ResponseEntity<InputStreamResource> citiesReport(HttpSession session) {

		int userID = Integer.parseInt(
				session.getAttribute("userId") != null ? (session.getAttribute("userId").toString()) : "45".toString());
		List<ExpenseBean> result = userDao.getAllExpenseDetails(userID);

		ByteArrayInputStream bis = PdfGenerator.generateExpense(result);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=expense.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}

//	@RequestMapping(value = "/downloadPdfbymonth/{monthName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public List<ExpenseBean> gemerateMonthly(@PathVariable("monthName") String monthName,
//			HttpSession session) {
//		System.out.println("Months name" + monthName);
//		int userID = Integer.parseInt(
//				session.getAttribute("userId") != null ? (session.getAttribute("userId").toString()) : "45".toString());
//		List<ExpenseBean> result = userDao.getMonthlyExpense(monthName, userID);
//		ByteArrayInputStream bis = PdfGenerator.generateExpense(result);
//
////		HttpHeaders headers = new HttpHeaders();
////		headers.add("Content-Disposition", "inline; filename=expense.pdf");
//
//		return result;
//	}
	@RequestMapping(value = "/downloadPdfbymonth", method = RequestMethod.POST, produces = MediaType.APPLICATION_PDF_VALUE)
	@ResponseBody
	public ResponseEntity<InputStreamResource> gemerateMonthly(@RequestParam("monthName") String monthName,
			HttpSession session) {
		System.out.println("Months name" + monthName);
		int userID = Integer.parseInt(
				session.getAttribute("userId") != null ? (session.getAttribute("userId").toString()) : "45".toString());
		List<ExpenseBean> result = userDao.getMonthlyExpense(monthName, userID);
		ByteArrayInputStream bis = PdfGenerator.generateExpense(result);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=expense.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}

	@RequestMapping(value = "downloadpdfinbetweendate", method = RequestMethod.POST, produces = MediaType.APPLICATION_PDF_VALUE)
	@ResponseBody
	public ResponseEntity<InputStreamResource> getExpensebetweendates(@RequestParam("startdate") String startdate,
			@RequestParam("enddate") String enddate, HttpSession session) {
		int userID = Integer.parseInt(
				session.getAttribute("userId") != null ? (session.getAttribute("userId").toString()) : "0".toString());
		List<ExpenseBean> result = userDao.getallExpenseBetweenDates(startdate, enddate, userID);
		ByteArrayInputStream bis = PdfGenerator.generateExpense(result);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=expense.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}

	@RequestMapping(value = "downloadPdfyerly", method = RequestMethod.POST, produces = MediaType.APPLICATION_PDF_VALUE)
	@ResponseBody
	public ResponseEntity<InputStreamResource> getExpenseByYear(@RequestParam("year") String year,
			HttpSession session) {

		int userID = Integer.parseInt(
				session.getAttribute("userId") != null ? (session.getAttribute("userId").toString()) : "45".toString());

		List<ExpenseBean> result = userDao.getallExpenseByYear(year, userID);
		ByteArrayInputStream bis = PdfGenerator.generateExpense(result);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=expense.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}
////////////////////////////Delete//////////////////////////
	/////////////////////// Delete Account/////////////////////////////

	@RequestMapping(value = "/deleteAccount/{id}", method = RequestMethod.GET)
	public String deletebyAccountId(@PathVariable("id") int id, Model model) {
		userDao.deleteAccount(id);
		// model.addAttribute("user", new UserBean());
		return "redirect:/account";
	}
	////////////////////////// deletepayee//////////////////////

	@RequestMapping(value = "/deletePayee/{id}", method = RequestMethod.GET)
	public String deletePayee(@PathVariable("id") int id, Model model) {
		System.out.println("in delete payeee method");
		userDao.deletePayee(id);
		return "redirect:/manage";

	}
//////////////////////////deleteCategory//////////////////////

	@RequestMapping(value = "/deleteCat/{id}", method = RequestMethod.GET)
	public String deleteCategory(@PathVariable("id") int id, Model model) {
		System.out.println("in delete cat method");
		userDao.deleteCategory(id);
		return "redirect:/manage";

	}
	///////////////////////delete sub category///////////
	@RequestMapping(value = "/deleteSubCat/{id}", method = RequestMethod.GET)
	public String deleteSubCat(@PathVariable("id") int id, Model model) {
		System.out.println("in delete subcat method");
		userDao.deleteSubCategory(id);
		return "redirect:/manage";

	}
	//////////////////////////////////////// Delete Expence///////////
	@RequestMapping(value = "/deleteExpence/{id}", method = RequestMethod.GET)
	public String deletebyExpenceId(@PathVariable("id") int id, Model model) {
		userDao.deleteExpence(id);
		// model.addAttribute("user", new UserBean());
		return "redirect:/expenseDetails";
	}

	
	@RequestMapping(value = "/expenseDetailsADmin/deleteExpence/{id}", method = RequestMethod.GET)
	public String deletebyExpencebyIdforadmin(@PathVariable("id") int id, Model model) {
		userDao.deleteExpence(id);
		// model.addAttribute("user", new UserBean());
		return "redirect:/expenseDetailsADmin/"+userIdforAdmin;
	}
	/////////////////////////////////////// Edit//////////////////

	////////////////////// edit payeeee///////////////////////////////////
	@RequestMapping(value = "/editPayee/{id}", method = RequestMethod.GET)
	public String editPayee(@PathVariable("id") int pb, Model model, HttpSession session) {
		int userID = Integer.parseInt(
				session.getAttribute("userId") != null ? (session.getAttribute("userId").toString()) : "45".toString());
		PayeeBean pbn = userDao.getPayeeById(pb, userID);
		model.addAttribute("pbn", pbn);
		return "EditPayee";
	}
//////////////////////////edit category/////////////////////
	@RequestMapping(value = "/editCat/{id}", method = RequestMethod.GET)
	public String editCat(@PathVariable("id") int cb, Model model, HttpSession session) {
		int userID = Integer.parseInt(
				session.getAttribute("userId") != null ? (session.getAttribute("userId").toString()) : "45".toString());
		CategoryBean cbn = userDao.getCategory(cb, userID);
		List<PayeeBean> payebean = userDao.getallPayee(userID);
		model.addAttribute("payeedata", payebean);
		model.addAttribute("cbn", cbn);
		return "EditCategory";
	}
	////////////////////////edit subcategory//////////////////////
	@RequestMapping(value = "/editSubCat/{id}", method = RequestMethod.GET)
	public String editSubCat(@PathVariable("id") int sb, Model model, HttpSession session) {
		int userID = Integer.parseInt(
				session.getAttribute("userId") != null ? (session.getAttribute("userId").toString()) : "45".toString());
		SubCategoryBean sbn = userDao.getSubCategory(sb, userID);
		model.addAttribute("sbn", sbn);
		return "EditSubCat";
	}

	// \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\Account Edit\\\\\\\\\\
	@RequestMapping(value = "/editAccount/{id}", method = RequestMethod.GET)
	public String editAccount(@PathVariable("id") int id, Model model) {
		AccountBean actbn = userDao.findAccountbyid(id);
		model.addAttribute("actbn", actbn);
		model.addAttribute("AccountType", userDao.list());
		return "EditAccount";
	}
	///////////////////////// Expence Edit/////////////////

	@RequestMapping(value = "/editExpence/{id}", method = RequestMethod.GET)
	public String expenseEdit(@PathVariable("id") int id, Model model, HttpSession session) {
		// System.out.println("expense details....................................>>");
		int userID = Integer.parseInt(
				session.getAttribute("userId") != null ? (session.getAttribute("userId").toString()) : "45".toString());
//		ExpenseBean expbn=userDao.getExpenceDetails(id,userID);
//		
		List<BalanceBean> acbean = userDao.getallaccountbyID(userID);
		List<PayeeBean> payebean = userDao.getallPayee(userID);
		model.addAttribute("payeedata", payebean);
		model.addAttribute("useraccount", acbean);
//		model.addAttribute("error", "balance error");
//		model.addAttribute("expbn", expbn);
		// System.out.println("expense test");
		ExpenseBean expbean = userDao.updateExpense(id);
		model.addAttribute("expbean", expbean);
		System.out.println(expbean);

		return "EditExpence";
	}
	
	@RequestMapping(value = "/expenseDetailsADmin/editExpence/{id}", method = RequestMethod.GET)
	public String expenseEditbyadmin(@PathVariable("id") int id, Model model, HttpSession session) {
		// System.out.println("expense details....................................>>");
		
		int userID = userIdforAdmin;
//		ExpenseBean expbn=userDao.getExpenceDetails(id,userID);
//		
		List<BalanceBean> acbean = userDao.getallaccountbyID(userID);
		List<PayeeBean> payebean = userDao.getallPayee(userID);
		model.addAttribute("payeedata", payebean);
		model.addAttribute("useraccount", acbean);
//		model.addAttribute("error", "balance error");
//		model.addAttribute("expbn", expbn);
		// System.out.println("expense test");
		ExpenseBean expbean = userDao.updateExpense(id);
		model.addAttribute("expbean", expbean);
		System.out.println(expbean);

		return "AdminExpenseEdit";
	}
	
	

	////////// update////////////
	// \\\\\\\\aCCountUpdate\\\\\\
	@PostMapping("/updateuser")
	public String accountUpdate(AccountBean account, Model model, HttpSession session) {
		// System.out.println("inserting in account.....");
		int userID = Integer.parseInt(
				session.getAttribute("userId") != null ? (session.getAttribute("userId").toString()) : "45".toString());
		model.addAttribute("AccountType", userDao.list());

		userDao.editAccount(account, userID);

		// model.addAttribute("useraccountdetails",
		// userDao.getUserAccountDetails(userID));
//		userDao.getUserAccountDetails(userID).stream().forEach(x -> {
//			System.out.println(x.toString());
//		});
		// account.setUserId(userID);
		// System.out.println(account);
		// userDao.setAccount(account);
		return "redirect:/account";
	}

	////////////////////// update//////////////////////////////
	////////////////////// expense////////////////////////////////////////////
	@PostMapping(value = "/updateExpense")
	public String updateExpense(ExpenseBean expensebean, @RequestParam("oldammount") int oldammount,
			@RequestParam("useraccountID") int useraccountID, @RequestParam("oldpayeeName") String oldpayeeName,
			@RequestParam("oldcategoryName") String oldcategoryName, HttpSession session, Model model) {

		int userID = Integer.parseInt(
				session.getAttribute("userId") != null ? (session.getAttribute("userId").toString()) : "0".toString());
		System.out.println("expense bean updated value--------------" + expensebean);
		System.out.println(oldammount);
		BalanceBean balacebean = userDao.getamountbyuserIDandaccountID(userID, useraccountID);
		model.addAttribute("totalbalance", balacebean.getAmount());
		int ammountdiff = expensebean.getAmmount() - oldammount;
		if (ammountdiff > 0) {
			if (balacebean != null && balacebean.getAmount() >= ammountdiff) {
				ExpenseBean e = new ExpenseBean();
				e.setAmmount(ammountdiff);
				e.setUseraccountID(useraccountID);
				e.setUserId(userID);
				userDao.updatebalance(e);
			}

		} else {
			ExpenseBean e = new ExpenseBean();
			e.setAmmount(oldammount - expensebean.getAmmount());
			e.setUseraccountID(useraccountID);
			e.setUserId(userID);
			userDao.updateandaddbalance(e);

		}

		userDao.updateExpensebyIdfromAdmin(expensebean, userID, oldammount, useraccountID, oldpayeeName, oldcategoryName);
		return "redirect:/expenseDetails";

	}
	
	@PostMapping(value = "/expenseDetailsADmin/updateExpense")
	public String updateExpensebyAdmin(ExpenseBean expensebean, @RequestParam("oldammount") int oldammount,
			@RequestParam("useraccountID") int useraccountID, @RequestParam("oldpayeeName") String oldpayeeName,
			@RequestParam("oldcategoryName") String oldcategoryName, HttpSession session, Model model) {

		int userID = userIdforAdmin;
		System.out.println("expense bean updated value--------------" + expensebean);
		System.out.println(oldammount);
		BalanceBean balacebean = userDao.getamountbyuserIDandaccountID(userID, useraccountID);
		model.addAttribute("totalbalance", balacebean.getAmount());
		int ammountdiff = expensebean.getAmmount() - oldammount;
		if (ammountdiff > 0) {
			if (balacebean != null && balacebean.getAmount() >= ammountdiff) {
				ExpenseBean e = new ExpenseBean();
				e.setAmmount(ammountdiff);
				e.setUseraccountID(useraccountID);
				e.setUserId(userID);
				userDao.updatebalance(e);
			}

		} else {
			ExpenseBean e = new ExpenseBean();
			e.setAmmount(oldammount - expensebean.getAmmount());
			e.setUseraccountID(useraccountID);
			e.setUserId(userID);
			userDao.updateandaddbalance(e);

		}

		userDao.updateExpensebyIdfromAdmin(expensebean, userID, oldammount, useraccountID, oldpayeeName, oldcategoryName);
		return "redirect:/expenseDetailsADmin/"+userIdforAdmin;

	}


	/////////////////////////// update payee////////////////////////////
	@PostMapping(value = "/payeeUpdate")
	public String payeeUpdate(PayeeBean pbean, HttpSession session) {
		System.out.println("in payee update controller");
		int userID = Integer.parseInt(
				session.getAttribute("userId") != null ? (session.getAttribute("userId").toString()) : "45".toString());
		userDao.updatePayee(pbean, userID);
		return "redirect:/manage";
	}

	/////////////////////////// updae category//////////////
	@PostMapping(value = "/catUpdate")
	public String catUpdate(CategoryBean cbean, HttpSession session,@RequestParam("oldPayeeName")String oldpayeeName ) {
		System.out.println("cat controller");
		int userID = Integer.parseInt(
				session.getAttribute("userId") != null ? (session.getAttribute("userId").toString()) : "45".toString());
		//int newPayeeId=userDao.payeeByName(cbean.getPayeeName());
		System.out.println("in cat update controller"+cbean.getPayeeName()+"  "+cbean.getCatName()+ cbean.getPayeeId()+  oldpayeeName);
		userDao.updateCategoryBypayeeIdandCatId(cbean);
		return "redirect:/manage";	
	}
	///////////////////////////update sub cat////////////////////////////
	@PostMapping(value = "/subcatUpdate")
	public String subcatUpdate(SubCategoryBean sbean, HttpSession session) {
		userDao.updateSubCategory(sbean);
		return "redirect:/manage";	
	}

	////////// ManageInfoPage/////////////////////////////
	@RequestMapping(value = "/manage")
	public String manageInfo(Model model, HttpSession session) {
		int userID = Integer.parseInt(
				session.getAttribute("userId") != null ? (session.getAttribute("userId").toString()) : "0".toString());
		if(userID!=0)
		{
		List<PayeeBean> pBean = userDao.payeeData(userID);
		model.addAttribute("pBean", pBean);
		List<CategoryBean> cBean = userDao.categoryData(userID);
		List<SubCategoryBean> sBean = userDao.subCategoryData(userID);
		model.addAttribute("cBean", cBean);
		model.addAttribute("sBean", sBean);
		return "ManageInformation";
		}
		else {
			return "redirect:/login";
		}
	}
	/////////// GetDataModel////////////
//	@GetMapping("/payeeCrud/{payeeName}")
//	public String payeeCrud(@PathVariable("payeeName") String pName) {
//		userDao.payeeData(pName);
//		return "ManageInformation";
//	}
//	

	/////////////////////////// Dashboard Expense/////////////////
	@RequestMapping(value = "/expenseDetailsADmin/{id}")
	public String expenseDetailsAdmin(@PathVariable("id") int id, Model model, HttpSession session) {
		// System.out.println("expense details....................................>>");

		List<ExpenseBean> result = userDao.getAllExpenseDetails(id);
		result.stream().forEach(x -> {
			// System.out.println(x);
			// System.out.println("expense test in loop");
		});

		model.addAttribute("expenseDetails", result);
		model.addAttribute("role", 1);
		userIdforAdmin=id;
		// System.out.println("expense test");
		return "ExpenseDetails";
	}

	///////////////////////////////////////////////// Add Model Value////////
	///////////////////////////////////////////////// payee///////////////////////////////////////
	@RequestMapping(value = "/addPayee")
	public String addPayee(@ModelAttribute("payee") PayeeBean pbean, HttpSession session) {
		int userID = Integer.parseInt(
				session.getAttribute("userId") != null ? (session.getAttribute("userId").toString()) : "45".toString());
		userDao.addPayee(pbean.getPayeename(), userID);
		return "redirect:/manage";

	}
///////////////////////////////////////////////////category/////////////////////

	@RequestMapping(value = "/addCat")
	public String addCat(@ModelAttribute("category") CategoryBean cbean, HttpSession session) {
		System.out.println("in cat add controller" + cbean.getCatName());
		int userID = Integer.parseInt(
				session.getAttribute("userId") != null ? (session.getAttribute("userId").toString()) : "45".toString());
		userDao.addCat(cbean.getPayeeName(), cbean.getCatName(),userID);
		return "redirect:/manage";

	}

///////////////////////////////////////////////////category/////////////////////
	@RequestMapping(value = "/addSub")
	public String addSubCat(@ModelAttribute("category") SubCategoryBean sbean, HttpSession session) {
		// System.out.println("in subcat add controller"+sbean.getCatName());
		int userID = Integer.parseInt(
				session.getAttribute("userId") != null ? (session.getAttribute("userId").toString()) : "45".toString());
		userDao.addSubCat(sbean.getSubCatName(), sbean.getCatName(), sbean.getPayeeName(), userID);
		return "redirect:/manage";

	}

	@GetMapping("/check/{payeeName}")
	@ResponseBody
	public List<CategoryBean> checkEmail(@PathVariable("payeeName") String payeeName, HttpSession session) {
		System.out.println("check cat name" + payeeName);
		int userID = Integer.parseInt(
				session.getAttribute("userId") != null ? (session.getAttribute("userId").toString()) : "45".toString());
		List<CategoryBean> cb = userDao.subCatByCatId(payeeName, userID);
		return cb;//
	}
	
	///////////////////////pdf////////////////
	@RequestMapping(value = "downloadpdfinbetweendate", method = RequestMethod.POST)
    public String getExpensebetweendates(@RequestParam("startdate") String startdate,
            @RequestParam("enddate") String enddate, Model model,HttpSession session) {
		System.out.println("getExpensebetweendates>>>>>");
        int userID = Integer.parseInt(
                session.getAttribute("userId") != null ? (session.getAttribute("userId").toString()) : "0".toString());
        List<ExpenseBean> result = userDao.getallExpenseBetweenDates(startdate, enddate, (userID==0)?userIdforAdmin:userID);
      
        model.addAttribute("expenseDetails", result);
        

        return "ExpenseDetails";
    }
	@RequestMapping(value = "/expenseDetailsADmin/downloadpdfinbetweendate", method = RequestMethod.POST)
    public String getExpensebetweendatesfromadmin(@RequestParam("startdate") String startdate,
            @RequestParam("enddate") String enddate, Model model,HttpSession session) {
		System.out.println("/expenseDetailsADmin/getExpensebetweendates>>>>>");
//        int userID = Integer.parseInt(
//                session.getAttribute("userId") != null ? (session.getAttribute("userId").toString()) : "0".toString());
        System.out.println(userIdforAdmin);
        
        	model.addAttribute("role",1);
        	;
        List<ExpenseBean> result = userDao.getallExpenseBetweenDates(startdate, enddate,userIdforAdmin);
      
        model.addAttribute("expenseDetails", result);
        

        return "ExpenseDetails";
    }


}