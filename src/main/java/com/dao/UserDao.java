package com.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.bean.AccountBean;
import com.bean.AccountName;
import com.bean.AccountTypeBean;
import com.bean.BalanceBean;
import com.bean.CategoryBean;
import com.bean.ExpenseBean;
import com.bean.PayeeBean;
import com.bean.RoleBean;
import com.bean.UserBean;

@Repository
public class UserDao {
	@Autowired
	JdbcTemplate stmt;

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
		String query = "insert into expense (payee,amount,paymentMethod,description,userId,dateExp,timeExp,exppic,category,subcategory) values (?,?,?,?,?,?,?,?,?,?)";
		stmt.update(query, expbean.getPayeeName(), expbean.getAmmount(), expbean.getUseraccountID(),
				expbean.getDescription(), expbean.getUserId(), expbean.getDateexp(), expbean.getTimeexp(),
				expbean.getImage(), expbean.getCategorydatalist(), expbean.getSubcategorydatalist());

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

	public void setpayee(String payeeName, int userId) {
		String query = "insert into payee(payeeName,userId) values(?,?)";
		stmt.update(query, payeeName, userId);

	}

	public List<ExpenseBean> getAllExpenseDetails(int userID) {
		String query = "select * from expense join account on expense.paymentMethod=account.accountId where expense.userId="
				+ userID;
		
		return stmt.query(query, (resultSet, rowNum) -> {
			ExpenseBean expensebean = new ExpenseBean();
			AccountName account = new AccountName();
			expensebean.setExpenseId(resultSet.getInt("expenseId"));
			expensebean.setPayeeName(resultSet.getString("payee"));
			expensebean.setAmmount(resultSet.getInt("amount"));
			expensebean.setTimeexp(resultSet.getString("timeExp"));
			expensebean.setDateexp(resultSet.getDate("dateExp"));
			expensebean.setDescription(resultSet.getString("description"));
			account.setId(resultSet.getInt("accountId"));
			account.setName(resultSet.getString("accountName"));
			expensebean.setAccountTypebean(account);
			expensebean.setCategorydatalist(resultSet.getString("category"));
			expensebean.setSubcategorydatalist(resultSet.getString("subcategory"));
			expensebean.setImage(resultSet.getString("exppic"));
			if(getallCategoryByPayeeName(expensebean.getPayeeName()).isEmpty())
			{
			  updateCategoryByPayeeName(expensebean.getCategorydatalist(),expensebean.getPayeeName());
			}
			if(getallsubCategoryByCategoryName(expensebean.getCategorydatalist()).isEmpty())
			{
			  updatesubCategoryBycategory(expensebean.getSubcategorydatalist(),expensebean.getCategorydatalist());
			}
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

	public List<CategoryBean> getallCategoryByPayeeName(String payeeName) {
		String query = "select * from category where payeename='" + payeeName + "'";
		return stmt.query(query, (resultSet, rowNum) -> {
			CategoryBean category = new CategoryBean();
			category.setCatId(resultSet.getInt("catId"));
			category.setCatName(resultSet.getString("catName"));

			return category;
		});
	}

	public List<String> getallsubCategoryByCategoryName(String categoryName) {
		String query = "select * from Subcategory where catName=\'" + categoryName + "\'";
		return stmt.query(query, (resultSet, rowNum) -> {
			return resultSet.getString("subcatName");
		});
	}
	 public void updateCategoryByPayeeName(String categoryName,String payeeName)
	  {
	    String query="insert into category(catName,payeeName) values(?,?)";
	   
	stmt.update(query,categoryName,payeeName);
	  }
	  
	 
	  
	  public void updatesubCategoryBycategory(String subcategoryName,String categoryName)
	  {
	    String query="insert into subcategory(subcatName,catName) values(?,?)";
	    stmt.update(query,subcategoryName,categoryName);
	  }
	  
	  
}
