package com.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.bean.AccountBean;
import com.bean.AccountName;
import com.bean.AccountTypeBean;
import com.bean.BalanceBean;
import com.bean.CategoryBean;
import com.bean.ExpenseBean;
import com.bean.PayeeBean;
import com.bean.RoleBean;
import com.bean.SubCategoryBean;
import com.bean.UserBean;

@Repository
public class UserDao {
	@Autowired
	JdbcTemplate stmt;

	public List<RoleBean> getRole() {
		String sql = "select * from role ";
		return stmt.query(sql, (resultSet, rowNum) -> {
			RoleBean role = new RoleBean();
			role.setRoleId(resultSet.getInt("id"));
			role.setRole(resultSet.getString("rolename"));
			return role;
		});

	}

	public void saveUser(UserBean user) {
		// exeu -> state -->
		// exeq --> read

		stmt.update("insert into users(name,email,phone,password,lastname,roleId) values (?,?,?,?,?,2)",
				user.getFirstName(), user.getEmail(), user.getPhone(), user.getPassword(), user.getLastName());

	}

	public UserBean finduser(UserBean user) {
		System.out.print("finduser");
		String email = user.getEmail();
		String password = user.getPassword();
		String sql = "select * from users join role on users.roleId=role.id where email='" + email + "' and password ='"
				+ password + "'";
		return stmt.query(sql, new ResultSetExtractor<UserBean>() {
			public UserBean extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					UserBean contact = new UserBean();
					RoleBean role = new RoleBean();
					contact.setUserId(rs.getInt("id"));
					contact.setFirstName(rs.getString("name"));
					contact.setEmail(rs.getString("email"));
					contact.setLastName(rs.getString("lastname") != null ? rs.getString("lastname") : "");
					contact.setPhone(rs.getLong("phone"));
					contact.setPassword(rs.getString("password"));
					role.setRoleId(rs.getInt("roleId"));
					role.setRole(rs.getString("rolename"));
					contact.setRole(role);

					return contact;
				}
				return null;

			}
		});

	}

	public List<UserBean> getAllUsers() {
		String query = "select * from users where roleId=2";
		return stmt.query(query, (resultSet, rowNum) -> {
			UserBean user = new UserBean();
			user.setEmail(resultSet.getString("email"));
			user.setFirstName(resultSet.getString("name"));
			user.setLastName(resultSet.getString("lastname"));
			user.setPhone(resultSet.getLong("phone"));
			user.setUserId(resultSet.getInt("id"));
			return user;
		});
	}

	public void deleteuser(int id) {
		stmt.update("delete from users where id = ?", id);
	}

	public UserBean finduserbyid(int id) {

		String sql = "select * from users where id=\"" + id + "\"";
		return stmt.query(sql, new ResultSetExtractor<UserBean>() {
			public UserBean extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					UserBean contact = new UserBean();

					contact.setUserId(rs.getInt("id"));
					contact.setFirstName(rs.getString("name"));
					contact.setEmail(rs.getString("email"));
					contact.setLastName(rs.getString("lastname") != null ? rs.getString("lastname") : "");
					contact.setPhone(rs.getLong("phone"));
					contact.setPassword(rs.getString("password"));

					return contact;
				}
				return null;

			}
		});
	}

	public void edituser(UserBean user) {
		String query = "update users set firstName='" + user.getFirstName() + "',email='" + user.getEmail()
				+ "',password='" + user.getPassword() + "' where id=" + user.getUserId();
		stmt.update(query);
		// TODO Auto-generated method stub

	}

	public UserBean searchEmail(String email) {
		String query = "select * from users where email='" + email + "'";
		return stmt.query(query, new ResultSetExtractor<UserBean>() {
			public UserBean extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					UserBean contact = new UserBean();

					contact.setUserId(rs.getInt("id"));
					contact.setFirstName(rs.getString("name"));
					contact.setEmail(rs.getString("email"));
					contact.setLastName(rs.getString("lastname") != null ? rs.getString("lastname") : "");
					contact.setPhone(rs.getLong("phone"));
					contact.setPassword(rs.getString("password"));

					return contact;
				}
				return null;

			}
		});

	}

	public int updatePasswordInDb(String newPassword, String otpemail) {
		String query = "update  users set password='" + newPassword + "' where email='" + otpemail + "'";
		int res = stmt.update(query);
		if (res == 1) {
			String sql = "select * from users where email='" + otpemail + "'";
			UserBean user = stmt.query(sql, new ResultSetExtractor<UserBean>() {
				public UserBean extractData(ResultSet rs) throws SQLException, DataAccessException {
					if (rs.next()) {
						UserBean contact = new UserBean();
						RoleBean role = new RoleBean();
						contact.setUserId(rs.getInt("id"));
						contact.setFirstName(rs.getString("name"));
						contact.setEmail(rs.getString("email"));
						contact.setLastName(rs.getString("lastname") != null ? rs.getString("lastname") : "");
						contact.setPhone(rs.getLong("phone"));
						contact.setPassword(rs.getString("password"));
						role.setRoleId(rs.getInt("roleId"));
						// role.setRole(rs.getString("rolename"));
						contact.setRole(role);

						return contact;
					}
					return null;

				}

			});

			return user.getRole().getRoleId();
		}
		// TODO Auto-generated method stub
		return 0;
	}

	public List<AccountTypeBean> list() {

		String query = "select * from accountType";
		return stmt.query(query, (resultSet, rowNum) -> {
			AccountTypeBean acType = new AccountTypeBean();
			acType.setAccountTypeId(resultSet.getInt("accountTypeId"));
			acType.setTypeName(resultSet.getString("typeName"));
			return acType;
		});

		// TODO Auto-generated method stub

	}

	public void categoryInsert(CategoryBean cat) {
		stmt.update("insert into category(catName) values(?)", cat.getCatName());
	}

	public void setAccount(AccountBean account) {
		Date date = new Date(System.currentTimeMillis());
		account.setCreatedAt(date);
		stmt.update("insert into account(accountName,balance,accountType,userId,createdAt) values(?,?,?,?,?)",
				account.getAccountName(), account.getBalance(), account.getAccountType(), account.getUserId(),
				account.getCreatedAt());

	}

	public List<AccountBean> getUserAccountDetails(int userID) {
		String query = "select * from account join accountType on account.accountType=accountType.accountTypeId where account.userId="
				+ userID;
		return stmt.query(query, (resultSet, rowNum) -> {
			AccountBean accountbean = new AccountBean();
			AccountTypeBean acType = new AccountTypeBean();
			accountbean.setAccountId(resultSet.getInt("accountId"));
			accountbean.setAccountName(resultSet.getString("accountName"));
			accountbean.setBalance(resultSet.getString("balance"));
			accountbean.setAccountType(resultSet.getInt("accountType"));
			accountbean.setAccountTypename(resultSet.getString("typeName"));
			acType.setAccountTypeId(resultSet.getInt("accountTypeId"));
			acType.setTypeName(resultSet.getString("typeName"));
			accountbean.setAccounttype(acType);
			accountbean.setCreatedAt(resultSet.getDate("createdAt"));
			return accountbean;
		});

	}

	public List<BalanceBean> getallaccountbyID(int userID) {
		String query = "select accountId,accountName,balance from account where userId=" + userID;
		return stmt.query(query, (resultSet, rowNum) -> {
			BalanceBean balancedetails = new BalanceBean();
			balancedetails.setAccountId(resultSet.getInt("accountId"));
			balancedetails.setAccountName(resultSet.getString("accountName"));
			balancedetails.setAmount(resultSet.getInt("balance"));

			return balancedetails;

		});

	}

	public List<PayeeBean> getallPayee(int userID) {
		System.out.println(userID);
		String query = "select * from payee where userId=" + userID;
		return stmt.query(query, (resultSet, rownum) -> {
			PayeeBean payee = new PayeeBean();
			payee.setPayeeId(resultSet.getInt("payeeId"));
			payee.setPayeename(resultSet.getString("payeeName"));

			return payee;
		});
	}

	public void setexpense(ExpenseBean expbean) {
		System.out.println(expbean);
		System.out.println("set>>>>>");
		int payeeId = getpayeeId(expbean.getPayeeName(), expbean.getUserId());
		System.out.println("set expense---------payeeID " + payeeId);
		if (checkgetallCategoryByPayeeName(expbean.getCategorydatalist(), payeeId).isEmpty()) {
			updateCategoryByPayeeName(expbean.getCategorydatalist(), payeeId);
		}
		int catID = getCategoryId(expbean.getCategorydatalist(), payeeId);
		System.out.println("set expense---------catID " + catID);
		if (checkgetallsubCategoryByCategoryName(expbean.getSubcategorydatalist(), catID).isEmpty()) {
			updatesubCategoryBycategory(expbean.getSubcategorydatalist(), catID, expbean.getUserId());
			int subCatId = getSubcategoryId(expbean.getSubcategorydatalist(), catID);
			System.out.println("set expense---------subCatId " + subCatId);
			// int subCatId=0;
			String query = "insert into expense (amount,paymentMethod,description,userId,dateExp,timeExp,exppic,catId,subCatId,payeeId) values (?,?,?,?,?,?,?,?,?,?)";
			stmt.update(query, expbean.getAmmount(), expbean.getUseraccountID(), expbean.getDescription(),
					expbean.getUserId(), expbean.getDateexp(), expbean.getTimeexp(), expbean.getImage(), catID,
					subCatId, payeeId);
		}

	}

	public List<String> checkgetallsubCategoryByCategoryName(String subCat, int catId) {

		String query = "select * from Subcategory where subCatName='" + subCat + "' and catId=" + catId;
		return stmt.query(query, (resultSet, rowNum) -> {
			return resultSet.getString("subCatName");
		});
	}

	private int getSubcategoryId(String subcategorydatalist, int catID) {
		String querysubcategory = "select id from Subcategory where subCatName='" + subcategorydatalist + "' and catId="
				+ catID;
		return stmt.queryForObject(querysubcategory, Integer.class);

	}

	public BalanceBean getamountbyuserIDandaccountID(int userID, int useraccountID) {
		String query = "select * from account where userId=" + userID + " and accountId=" + useraccountID;
		return stmt.query(query, new ResultSetExtractor<BalanceBean>() {
			public BalanceBean extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					BalanceBean balance = new BalanceBean();
					balance.setAmount(rs.getInt("balance"));

					return balance;
				}
				return null;

			}
		});

	}

	public void updatebalance(ExpenseBean expbean) {
		String query = "update account set balance=balance" + -expbean.getAmmount() + " where accountId="
				+ expbean.getUseraccountID() + " and userId=" + expbean.getUserId();
		stmt.update(query);

	}

	public void updateandaddbalance(ExpenseBean expbean) {
		String query = "update account set balance=balance +" + expbean.getAmmount() + " where accountId="
				+ expbean.getUseraccountID() + " and userId=" + expbean.getUserId();
		stmt.update(query);

	}

	public void setpayee(String payeeName, int userId) {
		String getquery = "select * from payee where userId=" + userId;
		List<String> result = stmt.query(getquery, (resultSet, roeNum) -> {
			String data = "";
			data = resultSet.getString("payeeName");
			return data;
		});
		if (result.isEmpty()) {
			String query = "insert into payee(payeeName,userId) values(?,?)";
			stmt.update(query, payeeName, userId);
		}

	}

	public List<ExpenseBean> getAllExpenseDetails(int userID) {
		String query = "select  * from expense as e join payee as p on e.payeeId=p.payeeId join category as c on e.catId=c.catId join Subcategory as sc on e.subCatId=sc.id join account as a on a.accountId=e.paymentMethod where e.userId="
				+ userID;

		return stmt.query(query, (resultSet, rowNum) -> {
			ExpenseBean expensebean = new ExpenseBean();
			AccountName account = new AccountName();
			expensebean.setExpenseId(resultSet.getInt("expenseId"));
			expensebean.setPayeeName(resultSet.getString("payeeName"));
			expensebean.setAmmount(resultSet.getInt("amount"));
			expensebean.setTimeexp(resultSet.getString("timeExp"));
			expensebean.setDateexp(resultSet.getDate("dateExp"));
			expensebean.setDescription(resultSet.getString("description"));
			account.setId(resultSet.getInt("accountId"));
			account.setName(resultSet.getString("accountName"));
			expensebean.setAccountTypebean(account);
			expensebean.setCategorydatalist(resultSet.getString("catName"));
			expensebean.setSubcategorydatalist(resultSet.getString("subCatName"));
			expensebean.setImage(resultSet.getString("exppic"));
//			if (getallCategoryByPayeeName(expensebean.getPayeeName()).isEmpty()) {
//				System.out.println("getallCategoryByPayeeName is empty--->>>");
//				//int payeeId=getpayeeId( expensebean.getPayeeName(),userID);
//				//System.out.println("PsyeeId="+payeeId+"payeename"+expensebean.getPayeeName()+"userid="+userID );
//			//	updateCategoryByPayeeName(expensebean.getCategorydatalist(), expensebean.getPayeeName());
//			}
//			if (getallsubCategoryByCategoryName(expensebean.getSubcategorydatalist(),userID).isEmpty()) {
//				updatesubCategoryBycategory(expensebean.getSubcategorydatalist(), expensebean.getCategorydatalist(),userID);
//			}
			return expensebean;
		});

	}

	public List<CategoryBean> getallCategory() {
		String query = "select * from category ";
		return stmt.query(query, (resultSet, rowNum) -> {
			CategoryBean category = new CategoryBean();
			category.setCatId(resultSet.getInt("catId"));
			category.setCatName(resultSet.getString("catName"));

			return category;
		});

	}

	public List<CategoryBean> checkgetallCategoryByPayeeName(String catName, int id) {
		String query = "select * from category where catName='" + catName + "' and payeeId=" + id;
		return stmt.query(query, (resultSet, rowNum) -> {
			CategoryBean category = new CategoryBean();
			category.setCatId(resultSet.getInt("catId"));
			category.setCatName(resultSet.getString("catName"));

			return category;
		});
	}

	public List<CategoryBean> getallCategoryByPayeeName(String payeeName, int userID) {
		String queryforID = "select payeeId from payee where payeeName='" + payeeName + "' and userId=" + userID;
		int payeeId = stmt.queryForObject(queryforID, Integer.class);
		String query = "select * from category where payeeId='" + payeeId + "'";
		return stmt.query(query, (resultSet, rowNum) -> {
			CategoryBean category = new CategoryBean();
			category.setCatId(resultSet.getInt("catId"));
			category.setCatName(resultSet.getString("catName"));

			return category;
		});
	}

	public List<String> getallsubCategoryByCategoryName(String catName, int userId) {
		String queryforid = "select catId from category where catName='" + catName + "'";
		int catId = stmt.queryForObject(queryforid, Integer.class);
		String query = "select * from Subcategory where catId='" + catId + "' and userId=" + userId;
		return stmt.query(query, (resultSet, rowNum) -> {
			return resultSet.getString("subcatName");
		});
	}

	public void updateCategoryByPayeeName(String categoryName, int payeeId) {
		String query = "insert into category(catName,payeeId) values(?,?)";

		stmt.update(query, categoryName, payeeId);
	}

	public void updatesubCategoryBycategory(String subcategoryName, int catId, int userID) {
		String query = "insert into subcategory(subcatName,catId,userId) values(?,?,?)";
		stmt.update(query, subcategoryName, catId, userID);
	}

	public List<Map<Object, Object>> getALlusersubCategory(int userID) {
		String query = "select sum(amount) as total, Subcategory.subCatName as subcategory from expense as e join Subcategory on e.subCatId=Subcategory.id where e.userId="
				+ userID + " group by Subcategory.subCatName; ";
		return stmt.query(query, (resultSet, rowNum) -> {
			Map<Object, Object> map = new HashMap<>();
			map.put("label",
					resultSet.getString("subcategory") != null ? resultSet.getString("subcategory") : "miscellanous");
			map.put("y", resultSet.getInt("total"));
			return map;
		});
	}

	public void insertRole(RoleBean role1) {
		String query = "insert into role(rolename) values(?)";
		stmt.update(query, role1.getRole());
		// TODO Auto-generated method stub

	}

	private List<ExpenseBean> getallExpense(String query, int userID) {
		return stmt.query(query, (resultSet, rowNum) -> {
			ExpenseBean expensebean = new ExpenseBean();
			AccountName account = new AccountName();
			expensebean.setExpenseId(resultSet.getInt("expenseId"));
			expensebean.setPayeeName(resultSet.getString("payeeName"));
			expensebean.setAmmount(resultSet.getInt("amount"));
			expensebean.setTimeexp(resultSet.getString("timeExp"));
			expensebean.setDateexp(resultSet.getDate("dateExp"));
			expensebean.setDescription(resultSet.getString("description"));
			account.setId(resultSet.getInt("accountId"));
			account.setName(resultSet.getString("accountName"));
			expensebean.setAccountTypebean(account);
			expensebean.setCategorydatalist(resultSet.getString("catName"));
			expensebean.setSubcategorydatalist(resultSet.getString("subCatName"));
			expensebean.setImage(resultSet.getString("exppic"));
//			if (getallCategoryByPayeeName(expensebean.getPayeeName()).isEmpty()) {
//				updateCategoryByPayeeName(expensebean.getCategorydatalist(), expensebean.getPayeeName());
//			}
//			if (getallsubCategoryByCategoryName(expensebean.getCategorydatalist(),userID).isEmpty()) {
//				updatesubCategoryBycategory(expensebean.getSubcategorydatalist(), expensebean.getCategorydatalist(),userID);
//			}
			return expensebean;
		});
	}

	public List<ExpenseBean> getMonthlyExpense(String monthName, int userId) {
		String query = "select * from expense as e join payee as p on e.payeeId=p.payeeId join category as c on e.catId=c.catId join Subcategory as sc on e.subCatId=sc.id join account as a on a.accountId=e.paymentMethod where  month(dateExp)='"
				+ monthName + "' and e.userId=" + userId;
		return getallExpense(query, userId);

	}

	public List<ExpenseBean> getallExpenseBetweenDates(String startdate, String enddate, int userID) {
		String query = "select * from expense as e join payee as p on e.payeeId=p.payeeId join category as c on e.catId=c.catId join Subcategory as sc on e.subCatId=sc.id join account as a on a.accountId=e.paymentMethod where dateExp between '"
				+ startdate + "' and '" + enddate + "' and e.userId=" + userID;
		return getallExpense(query, userID);
	}

	public List<ExpenseBean> getallExpenseByYear(String year, int userID) {
		String query = "select * from expense as e join payee as p on e.payeeId=p.payeeId join category as c on e.catId=c.catId join Subcategory as sc on e.subCatId=sc.id join account as a on a.accountId=e.paymentMethod where year(dateExp)='"
				+ year + "'and e.userId=" + userID;
		return getallExpense(query, userID);
		// TODO Auto-generated method stub

	}

	public void insertAccountType(AccountTypeBean accountType) {
		String query = "insert into accountType(typeName) values(?)";
		stmt.update(query, accountType.getTypeName());
		// TODO Auto-generated method stub

	}

	private int getpayeeId(String payeeName, int userID) {
		Integer payeeId = 0;
		System.out.println("getpayeeId method...........");
		String queryfindpayeeid = "select payeeId from payee where payeeName='" + payeeName + "' and userId=" + userID;

		try {
			payeeId = stmt.queryForObject(queryfindpayeeid, Integer.class);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println(payeeId);
			if (payeeId == 0) {
				String query = "insert into payee (payeeName,userId) values(?,?)";
				stmt.update(query, payeeName, userID);
				String queryfindpayeeid2 = "select payeeId from payee where payeeName='" + payeeName + "' and userId="
						+ userID;
				payeeId = stmt.queryForObject(queryfindpayeeid2, Integer.class);
				System.out.println("test3---" + payeeId);
				return payeeId;
			}
			return payeeId;
		}

	}

	private int getCategoryId(String catName, int payeeId) {
		Integer categoryId = 0;
		String queryfindCategory = "select catId from category where catName='" + catName + "' and payeeId=" + payeeId;
		categoryId = stmt.queryForObject(queryfindCategory, Integer.class);
		return categoryId;

	}

	///////////////////////// Delete/////////////////////
	//////////////////// account delete///////////////////
	public void deleteAccount(int id) {
		stmt.update("delete from account where accountId = ?", id);
	}

/////////////////////////////////delete expense///////////////
	public void deleteExpence(int id) {
		stmt.update("delete from expense where expenseId = ?", id);
		// TODO Auto-generated method stub

	}

	//////////////////////////////// delete payee///////////////////////////////////
	public void deletePayee(int id) {
		System.out.println("in deletePayee dao");
		stmt.update("delete from payee where payeeId =?", id);
		// TODO Auto-generated method stub

	}

////////////////////////////////delete category///////////////////////////////////
	public void deleteCategory(int id) {
		System.out.println("in deletePayee dao");
		stmt.update("delete from category where catId =?", id);
// TODO Auto-generated method stub

	}
	/////////////////////////delete subcat////////////////

	public void deleteSubCategory(int id) {
		stmt.update("delete from Subcategory where id =?", id);
		
	}


	/////////////////// Edit///////////////////////////////

	public AccountBean findAccountbyid(int id) {

		String sql = "select * from account where accountId=" + id;
		return stmt.query(sql, new ResultSetExtractor<AccountBean>() {
			public AccountBean extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					AccountBean act = new AccountBean();
					AccountTypeBean acType = new AccountTypeBean();
					act.setAccountId(rs.getInt("accountId"));
					act.setAccountName(rs.getString("accountName"));
					act.setBalance(rs.getString("balance"));
					act.setAccountType(rs.getInt("accountType"));
					// act.setAccountTypename(rs.getString("typeName"));
					// acType.setAccountTypeId(rs.getInt("accountTypeId"));
					// acType.setTypeName(rs.getString("typeName"));
					// act.setAccounttype(acType);
					act.setCreatedAt(rs.getDate("createdAt"));
					return act;
				}
				return null;

			}
		});
	}

	public ExpenseBean getExpenceDetails(int id, int userID) {
		String sql = "select * from expense where expenseId=" + id;
		// TODO Auto-generated method stub
		return null;
	}

	////////////////////////////////// update/////////////////
	public void editAccount(AccountBean account, int id) {
		Date date = new Date(System.currentTimeMillis());
		account.setCreatedAt(date);
		String query = "update account set accountName='" + account.getAccountName() + "',balance='"
				+ account.getBalance() + "',accountType='" + account.getAccountType() + "',createdAt='"
				+ account.getCreatedAt() + "' where accountId=" + account.getAccountId() + " and userId=" + id;
		stmt.update(query);
		// TODO Auto-generated method stub

	}
	//////////////////////////////////// Model//////
	////////// Payeeee?////////////

	public List<PayeeBean> payeeData(int userID) {
		String query = "select * from payee where userId=" + userID;
		return stmt.query(query, (resultSet, rowNum) -> {
			PayeeBean pBean = new PayeeBean();
			pBean.setPayeeId(resultSet.getInt("payeeId"));
			pBean.setPayeename(resultSet.getString("payeeName"));
			pBean.setUserId(resultSet.getInt("userId"));

			return pBean;
		});

	}

//////////////////////caegory list//////////////////
	public List<CategoryBean> categoryData(int userID) {
		String query = "select catId,catName,payeeName from category join payee on category.payeeId=payee.payeeId  where userId="
				+ userID;
		return stmt.query(query, (rs, rowname) -> {
			CategoryBean cBean = new CategoryBean();
			cBean.setCatId(rs.getInt("catId"));
			cBean.setCatName(rs.getString("catName"));
			cBean.setPayeeName(rs.getString("payeeName"));

			return cBean;
		});
	}

	////////////////////////////// cat by payee/////////////////
	public List<CategoryBean> subCatByCatId(String payeeName, int userID) {
		String queryforID = "select payeeId from payee where payeeName='" + payeeName + "' ";
		int pid = stmt.queryForObject(queryforID, Integer.class);
		System.out.println("i foundpayee name here in dao " + pid);
		String query = "  select catId,catName,payeeName from category join payee on category.payeeId=payee.payeeId  where userId="
				+ userID + " and payee.payeeId=" + pid;
		return stmt.query(query, (rs, rowname) -> {
			CategoryBean cBean = new CategoryBean();
			cBean.setCatId(rs.getInt("catId"));
			cBean.setCatName(rs.getString("catName"));
			cBean.setPayeeName(rs.getString("payeeName"));

			return cBean;
		});

	}

	//////////////////// sub category list///////////////////
	public List<SubCategoryBean> subCategoryData(int userID) {
		String query = " select id,subCatName,catName,payeeName from Subcategory as sub join category as cat on sub.catId=cat.catId join  payee as p on cat.payeeId=p.payeeId where sub.userId="
				+ userID;
		return stmt.query(query, (rs, rn) -> {
			SubCategoryBean subCat = new SubCategoryBean();
			
			subCat.setCatName(rs.getString("catName"));
			subCat.setSubCatName(rs.getString("subCatName"));
			subCat.setPayeeName(rs.getString("payeeName"));
			subCat.setId(rs.getInt("id"));
			return subCat;

		});
	}

	public ExpenseBean updateExpense(int id) {
		String query = " select  * from expense as e join payee as p on e.payeeId=p.payeeId join category as c on e.catId=c.catId join Subcategory as sc on e.subCatId=sc.id join account as a on a.accountId=e.paymentMethod where e.expenseId="
				+ id;
		return stmt.query(query, new ResultSetExtractor<ExpenseBean>() {
			public ExpenseBean extractData(ResultSet resultSet) throws SQLException, DataAccessException {
				if (resultSet.next()) {

					ExpenseBean expensebean = new ExpenseBean();
					AccountName account = new AccountName();
					expensebean.setExpenseId(resultSet.getInt("expenseId"));
					expensebean.setPayeeName(resultSet.getString("payeeName"));
					expensebean.setAmmount(resultSet.getInt("amount"));
					expensebean.setTimeexp(resultSet.getString("timeExp"));
					expensebean.setDateexp(resultSet.getDate("dateExp"));
					expensebean.setDescription(resultSet.getString("description"));
					account.setId(resultSet.getInt("accountId"));
					account.setName(resultSet.getString("accountName"));
					expensebean.setAccountTypebean(account);
					expensebean.setCategorydatalist(resultSet.getString("catName"));
					expensebean.setSubcategorydatalist(resultSet.getString("subCatName"));

					return expensebean;
				}
				return null;

			}
		});
	}

	private int getpayeeID(int userId, String payeeName) {
		String query = "select payeeId from payee where payeeName='" + payeeName + "' and userId=" + userId;
		return stmt.queryForObject(query, Integer.class);
	}

	public void updateExpensebyId(ExpenseBean expensebean, int userID, int oldammount, int useraccountID,
			String oldpayeeName, String oldcategoryName) {
		System.out.println("update expense------------------" + expensebean);
		String findpayeeid = "select payeeId from payee where payeeName='" + expensebean.getPayeeName()
				+ "' and userId=" + userID;
		int payeeId = 0;

		//payeeId = stmt.queryForObject(findpayeeid, Integer.class);

		//updateCategorybyPayeeId(expensebean.getCategorydatalist(), userID, oldpayeeName, payeeId);

		String findcategoryId = "Select catId from category where catName='" + expensebean.getCategorydatalist()
				+ "' and payeeId=" + payeeId;
		//int catId = stmt.queryForObject(findcategoryId, Integer.class);

		//updateSubCategorybyID(expensebean.getSubcategorydatalist(), payeeId, oldcategoryName, catId, userID);
//
//		String findsubcategoryId = "select id from Subcategory where subCatName='"
//				+ expensebean.getSubcategorydatalist() + "' and catId=" + catId;
		//int subcatId = stmt.queryForObject(findsubcategoryId, Integer.class);

		// String findaccountId="select accountId from account where
		// accountName='"+expensebean.getAccountName().getName()+"' and userId="+userID;
		String updateexpense = " update  expense set amount=" + expensebean.getAmmount() + " ,dateExp='"
				+ expensebean.getDateexp() + "',timeExp='" + expensebean.getTimeexp() + "',paymentMethod="
				+ useraccountID + ",description='"
				+ expensebean.getDescription() + "' where expenseId=" + expensebean.getExpenseId();
		stmt.update(updateexpense);

	}

	public void updateCategorybyPayeeId(String catName, int payeeId, String oldpayeeName, int newPayeeId) {

		int oldplayeeID = getpayeeID(payeeId, oldpayeeName);

		String query = "update category set payeeId=" + newPayeeId + " where catName='" + catName + "' and payeeId="
				+ oldplayeeID;
		stmt.update(query);

	}

	public void updateSubCategorybyID(String subcatName, int payeeId, String oldcatName, int newcatID, int userId) {
		int oldCatId = getcatId(payeeId, oldcatName);
		String query = "update Subcategory set catId=" + newcatID + " where subCatName='" + subcatName + "' and userId="
				+ userId + "and catId=" + oldCatId;
		stmt.update(query);
	}

	private int getcatId(int payeeId, String oldsubcatName) {
		String query = "select catId from category where catName='" + oldsubcatName + "' and payeeId=" + payeeId;
		return stmt.queryForObject(query, Integer.class);
	}

	////////////////////////// add mode Value//////////////
	/////////////////// payeee/////////////////////////////

	public void addPayee(String payeename, int userID) {
		String query = "insert into payee (payeeName,userId) values(?,?) ";
		stmt.update(query, payeename, userID);

	}
	public int payeeByName(String payeename) {
		String queryforID = "select payeeId from payee where payeeName='" + payeename + "' ";
		int pid = stmt.queryForObject(queryforID, Integer.class);
		return pid;
	}

/////////////////// Cat/////////////////////////////

	public void addCat(String payeename, String catName) {

		String queryforID = "select payeeId from payee where payeeName='" + payeename + "' ";
		int pid = stmt.queryForObject(queryforID, Integer.class);
		System.out.println("in cat dao" + pid);
		String query = "insert into category (payeeId,catName) values(?,?) ";
		stmt.update(query, pid, catName);

	}

	public void addSubCat(String subCatName, String catName, String payeeName, int uid) {

		String queryforID = "select payeeId from payee where payeeName='" + payeeName + "' and userId= " + uid;
		int pid = stmt.queryForObject(queryforID, Integer.class);
		String queryforcatId = "select catId from category where catName='" + catName + "' and payeeId= " + pid;
		int cid = stmt.queryForObject(queryforcatId, Integer.class);
		String query = "insert into Subcategory(subCatName,userId,catId) values(?,?,?)";
		stmt.update(query, subCatName, uid, cid);
		// TODO Auto-generated method stub

	}

///////////////////signup validation///////////
	public boolean checkDuplicateEmail(String email) {

		List<UserBean> users = stmt.query("select * from users,role where email like ? and role.Id = users.roleId",
				new UserRowMapper(), email);
		System.out.println(users.size());
		if (users.size() == 0)
			return false;
		else
			return true;
	}

	class UserRowMapper implements RowMapper<UserBean> {

		public UserBean mapRow(ResultSet rs, int rowNum) throws SQLException {

			UserBean user = new UserBean();
			user.setUserId(rs.getInt("id"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setFirstName(rs.getString("name"));
//			user.setRoleName(rs.getString("roleName"));

			RoleBean role = new RoleBean();
			role.setRoleId(rs.getInt("id"));
			role.setRole(rs.getString("rolename"));
			user.setRole(role);

			return user;
		}

	}

////////////////////////payeeBean///////////////

	public PayeeBean getPayeeById(int pb, int userID) {
		String query = "select * from payee where userId=" + userID + " and payeeId=" + pb;
		return stmt.query(query, new ResultSetExtractor<PayeeBean>() {
			public PayeeBean extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					PayeeBean pb = new PayeeBean();
					pb.setPayeeId(rs.getInt("payeeId"));
					pb.setPayeename(rs.getString("payeeName"));
					return pb;
				}

				return null;
			}

		});

	}

	/////////////////// category edit////////////////
	public CategoryBean getCategory(int cb, int userID) {
		String query = " select catName,payeeName,c.payeeId,c.catId  from category as c join payee as p on c.payeeId=p.payeeId where userId="
				+ userID + "  and catId=" + cb;
		return stmt.query(query, new ResultSetExtractor<CategoryBean>() {
			public CategoryBean extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					CategoryBean cb = new CategoryBean();
					cb.setCatId(rs.getInt("catId"));
					cb.setCatName(rs.getString("catName"));
					cb.setPayeeId(rs.getInt("payeeId"));
					cb.setPayeeName(rs.getString("payeeName"));
					return cb;
				}

				return null;
			}
		});
	}
/////////////////////////////////subCatedit////////////////////
	public SubCategoryBean getSubCategory(int sb, int userID) {
		String query="  select id,subCatName,Subcategory.catId,category.catName,payee.payeeName from Subcategory join category on Subcategory.catid=.category.catId join payee on category.payeeId=payee.payeeId where Subcategory.userId="+userID+" and id="+sb;
		return stmt.query(query, new ResultSetExtractor<SubCategoryBean>() {
			public SubCategoryBean extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					SubCategoryBean sb=new SubCategoryBean();
					sb.setId(rs.getInt("id"));
					sb.setSubCatName(rs.getString("subCatName"));
					sb.setCatName(rs.getString("catName"));
					sb.setPayeeName(rs.getString("payeeName"));
					return sb;
				}
				return null;
				}
		});
	
	}

	////////////////////////////// update//////////////////
	/////////////////////////////// payee//////////////
	public void updatePayee(PayeeBean pbean, int userID) {
		System.out.println("inpayeeupd dao" + pbean.getPayeename() + " " + pbean.getPayeeId() + "  " + userID);
		String query = "update payee set payeeName='" + pbean.getPayeename() + "' where payeeId=" + pbean.getPayeeId()
				+ " and userId=" + userID;
		stmt.update(query);

	}

	public void updateCategoryBypayeeIdandCatId(CategoryBean cbean) {
		System.out.println("updatecategoryby Id-------------------------------------------------------");
		String query="update category set catName='"+cbean.getCatName()+"' where catId="+cbean.getCatId()+" and payeeId="+cbean.getPayeeId();
		System.out.println("catId"+cbean.getCatId()+"catNAme"+cbean.getCatName()+"payeeName"+cbean.getPayeeId());
		stmt.update(query);
	}

	///////////////////////////////////////////subcat/////////////////////
	public void updateSubCategory(SubCategoryBean sbean) {
		String query="update Subcategory set subCatName='"+sbean.getSubCatName()+"' where id="+sbean.getId();
		stmt.update(query);
	}

	
	
}
