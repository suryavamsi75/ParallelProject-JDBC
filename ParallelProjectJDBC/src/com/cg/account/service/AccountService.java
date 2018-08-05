package com.cg.account.service;
import com.cg.account.bean.Account;
import com.cg.account.dao.AccountDao;
import com.cg.account.dao.IAccountDao;
import com.cg.account.exception.AccountException;

public class AccountService implements IAccountService {
	IAccountDao accountDao = new AccountDao();

	@Override
	public String createAccount(Account account) throws AccountException {
		// TODO Auto-generated method stub		
		return accountDao.createAccount(account);
	}
	/*@Override
	public Account getAccountDetails(String mobile) throws WalletException {
		// TODO Auto-generated method stub
		return accountDao.getAccountDetails(mobile);
	}
	*/
	
	@Override
	public boolean validateAccount(Account acc) throws AccountException{
		if(validateMobile(acc.getPhone()) && validateName(acc.getName()) 
				&& validateEmail(acc.getEmail()) && validateBalance(acc.getBalance()))
			return true;
		return false;
	}

		public boolean validateMobile(String mobile) throws AccountException {
			if (!mobile.matches("\\d{10}"))
				throw new AccountException("Mobile number should contain 10 digits");
			return true;
		}
		public boolean validateName(String name) throws AccountException {
			if(name.isEmpty() || name == null)
				throw new AccountException("Name cannot be empty");
			else
				if (!name.matches("[A-Za-z]{2,}"))
					throw new AccountException("Name must contain only alphabets");
			return true;
		}
		public boolean validateEmail(String email) throws AccountException {
			if (!email.matches("[A-Za-z0-9]{3,}@{1}[a-z]{2,}\\.com"))
				throw new AccountException("Invalid Email ID");
			return true;
		}
		public boolean validateBalance(double balance) throws AccountException {
			if (balance <= 0)
				throw new AccountException("Balance must be a number greater than zero");
			return true;
		}

	@Override
	public double AccountBalance(String mobile) throws AccountException {
		// TODO Auto-generated method stub
		
		if(validateMobile(mobile)){
			Account acc = accountDao.TransactionDetails(mobile);
			if(validateAccount(acc))
				throw new AccountException("Account Does not Exist");
		}
		return accountDao.AccountBalance(mobile);
	}

	@Override
	public double deposit(String mobile, double amt) throws AccountException {
		// TODO Auto-generated method stub
		return accountDao.deposit(mobile, amt);
	}

	@Override
	public double withdraw(String mobile, double amt) throws AccountException {
		// TODO Auto-generated method stub
		Account acc = accountDao.TransactionDetails(mobile);
		if(amt<acc.getBalance())
			throw new AccountException("Account balance is Low");
		return accountDao.withdraw(mobile, amt);
	}

	@Override
	public boolean fundTransfer(String mobile1, String mobile2, double amount) throws AccountException {
		// TODO Auto-generated method stub
		if(!validateMobile(mobile1) || !validateMobile(mobile2))
			throw new AccountException("Mobile number should contain 10 digits");
		if(amount < 0)
			throw new AccountException("Amount must be a number greater than zero");
		return accountDao.fundTransfer(mobile1, mobile2, amount);
	}

	@Override
	public Account TransactionDetails(String mobile) throws AccountException {
		// TODO Auto-generated method stub
		return accountDao.TransactionDetails(mobile);
	}
	

	
	
	
	

}
