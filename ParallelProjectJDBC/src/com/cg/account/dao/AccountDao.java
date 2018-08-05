package com.cg.account.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cg.account.bean.Account;
import com.cg.account.exception.AccountException;
import com.cg.account.util.AccountDb;

public class AccountDao implements IAccountDao {
	
	public String createAccount(Account account) throws AccountException{
		Connection connection = AccountDb.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(AccountDaoQueryMapper.INSERT_QUERY);
			statement.setString(1, account.getName());
			statement.setString(2, account.getPhone());
			statement.setString(3, account.getEmail());
			statement.setDouble(4, account.getBalance());
			statement.setString(5,account.getDate());
			
			int res = statement.executeUpdate();
			if(res==1){
				Statement stat = connection.createStatement();
				ResultSet rs = stat.executeQuery(AccountDaoQueryMapper.GET_ACC_MOBILE_NO);
				if(rs != null)
					rs.next();
				String id = rs.getString(1);
				
				return id;
			}
			else
				throw new AccountException("Unable to insert Account details");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new AccountException(e.getMessage());
		}
	}
	
	
	
	@Override
	public double AccountBalance(String mobile) throws AccountException {
		// TODO Auto-generated method stub
		Account acc = getAccountDetails(mobile);
		return acc.getBalance();
	}

	@Override
	public double deposit(String mobile, double amt) throws AccountException {
		// TODO Auto-generated method stub
		Account acc = getAccountDetails(mobile);
		double finalBal = acc.getBalance() + amt; 
		acc.setBalance(finalBal);
		acc.setDate(""+ new java.util.Date());
		
		Connection connection = AccountDb.getConnection();
		try {
			PreparedStatement stat = connection.prepareStatement(AccountDaoQueryMapper.UPDATE_BALANCE_QUERY);
			stat.setDouble(1, acc.getBalance());
			stat.setString(2, acc.getDate());
			stat.setString(3, mobile);
			int res = stat.executeUpdate();
			 return acc.getBalance();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new AccountException(e.getMessage());
		}
		
	}

	@Override
	public double withdraw(String mobile, double amt) throws AccountException {
		// TODO Auto-generated method stub
		Account acc = getAccountDetails(mobile);
		double finalBal = acc.getBalance() - amt; 
		acc.setBalance(finalBal);
		acc.setDate(""+ new java.util.Date());
		
		Connection connection = AccountDb.getConnection();
		try {
			PreparedStatement stat = connection.prepareStatement(AccountDaoQueryMapper.UPDATE_BALANCE_QUERY);
			stat.setDouble(1, acc.getBalance());
			stat.setString(2, acc.getDate());
			stat.setString(3, mobile);
			int res = stat.executeUpdate();
			 return acc.getBalance();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new AccountException(e.getMessage());
		}
	}

	@Override
	public boolean fundTransfer(String mobile1, String mobile2, double amount) throws AccountException {
		// TODO Auto-generated method stub
		Account acc1 = getAccountDetails(mobile1);
		Account acc2 = getAccountDetails(mobile2);
		
		if(acc1 == null || acc2 == null)
			throw new AccountException("Account doesnot exist. Amount can't be transferred");
		
		if(amount > acc1.getBalance())
			throw new AccountException("Account balance is low");
		double bal = acc1.getBalance()-amount;			//withdraw from mobile1
		acc1.setBalance(bal);
		double bal1 = acc2.getBalance()+amount;
		acc2.setBalance(bal1);		//deposit in mobile2
		
		Connection connection = AccountDb.getConnection();
		try {
			PreparedStatement stat = connection.prepareStatement(AccountDaoQueryMapper.UPDATE_BALANCE_QUERY);
			stat.setDouble(1, acc1.getBalance());
			stat.setString(2, acc1.getDate());
			stat.setString(3, mobile1);
			int res1 = stat.executeUpdate();
			
			PreparedStatement stat1 = connection.prepareStatement(AccountDaoQueryMapper.UPDATE_BALANCE_QUERY);
			stat.setDouble(1, acc2.getBalance());
			stat.setString(2, acc2.getDate());
			stat.setString(3, mobile2);
			int res2 = stat1.executeUpdate();
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new AccountException(e.getMessage());
		}
		return true;
	}

	@Override
	public Account TransactionDetails(String mobile) throws AccountException {
		// TODO Auto-generated method stub
		Account account = getAccountDetails(mobile);
		
		return account;
	}

	private Account getAccountDetails(String mobile) throws AccountException {
		// TODO Auto-generated method stub
		Connection connection = AccountDb.getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(AccountDaoQueryMapper.GET_ALL_ACCOUNT_INFO);
			
			Account acc = null;
			while(resultSet.next()){
				//System.out.println("Val: "+resultSet.getString(3));
				if(resultSet.getString(2).equals(mobile)){
					acc = new Account();
					acc.setName(resultSet.getString(1));
					acc.setPhone(resultSet.getString(2));
					acc.setEmail(resultSet.getString(3));
					acc.setBalance(resultSet.getDouble(4));
					acc.setDate(resultSet.getString(5));
					
					return acc;
				}
			}
			if(resultSet != null){
				resultSet.next();
			}
			throw new AccountException("Account Does not exist");
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new AccountException(e.getMessage());
		}
	}
	

}
