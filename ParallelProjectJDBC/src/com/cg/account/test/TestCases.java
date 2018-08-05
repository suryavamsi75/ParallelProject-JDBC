package com.cg.account.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.cg.account.bean.Account;
import com.cg.account.exception.AccountException;
import com.cg.account.service.AccountService;
import com.cg.account.service.IAccountService;

public class TestCases {
	
	Account acc;
	IAccountService service;
	
	@Before
	public void init() {
		// TODO Auto-generated method stub
		service = new AccountService();
		acc = new Account();
	}
	
	@Test
	//Given wrong mobile number.
	public void testAccountMobile(){
		
		acc.setName("Surya");
		acc.setPhone("111111");
		acc.setEmail("Surya@gmail.com");
		acc.setBalance(500.0);
		acc.setDate(""+new Date());
		
		try {
			if(service.validateAccount(acc))
				service.createAccount(acc);
		} catch (AccountException e) {
			// TODO: handle exception
			//System.out.println("wrong mobile:"+e.getMessage());
			assertEquals("Mobile number should contain 10 digits", e.getMessage());
		}
	}
	
	@Test
	// Given wrong name i.e. contains numbers 
	public void testAccountName(){
		
		acc.setName("1232456");
		acc.setPhone("1234567890");
		acc.setEmail("Surya@gmail.com");
		acc.setBalance(500.0);
		acc.setDate(""+new Date());
		
		try {
			if(service.validateAccount(acc))
				service.createAccount(acc);
		} catch (AccountException e) {
			// TODO: handle exception
			//System.out.println("name with numbers:"+e.getMessage());
			assertEquals("Name must contain only alphabets", e.getMessage());
		}	
	}
	@Test
	// given an empty name string
	public void testAccountName1(){
		
		acc.setName("");
		acc.setPhone("1111111111");
		acc.setEmail("Surya@gmail.com");
		acc.setBalance(500.0);
		acc.setDate(""+new Date());
		
		try {
			if(service.validateAccount(acc))
				service.createAccount(acc);
		} catch (AccountException e) {
			// TODO: handle exception
			//System.out.println("empty name:"+e.getMessage());
			assertEquals("Name cannot be empty", e.getMessage());
		}	
	}
	
	@Test
	public void testAccountEmail(){
		
		acc.setName("Surya");
		acc.setPhone("1111111111");
		acc.setEmail("Suryagmail.com");
		acc.setBalance(500.0);
		acc.setDate(""+new Date());
		
		try {
			if(service.validateAccount(acc))
				service.createAccount(acc);
		} catch (AccountException e) {
			// TODO: handle exception
			//System.out.println("wrong id:"+e.getMessage());
			assertEquals("Invalid Email ID", e.getMessage());
		}	
	}
	
	@Test
	public void testAccountBalance(){
		
		acc.setName("Surya");
		acc.setPhone("1111111111");
		acc.setEmail("dee@cg.com");
		acc.setBalance(-500);
		acc.setDate(""+new Date());
		//System.out.println(acc.getBalance());
		try {
			
			if(service.validateAccount(acc))
				service.createAccount(acc);
		} catch (AccountException e) {
			// TODO: handle exception
			//System.out.println("negative balance:"+e.getMessage());
			assertEquals("Balance must be a number greater than zero", e.getMessage());
		}	
	}
	
	@Test
	public void testCreateAccount(){
		
		acc.setName("Surya");
		acc.setPhone("1111111111");
		acc.setEmail("dee@cg.com");
		acc.setBalance(500);
		acc.setDate(""+new Date());
		
		try {
			String mobile = service.createAccount(acc);
			assertNotNull(mobile);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			//System.out.println(e.getMessage());
		}
		
	}
	
	@Test
	public void testCreateAccount1(){
				
		acc.setName("Hari");
		acc.setPhone("2222222222");
		acc.setEmail("haha@cg.com");
		acc.setBalance(15000);
		acc.setDate(""+new Date());
		
		try {
			String mobile = service.createAccount(acc);
			assertNotNull(mobile);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			//System.out.println(e.getMessage());
		}
		
	}
	
	@Test
	public void testCreateAccount2(){
				
		acc.setName("Radha");
		acc.setPhone("3333333333");
		acc.setEmail("radha@cg.com");
		acc.setBalance(2000);
		acc.setDate(""+new Date());
		
		try {
			String mobile = service.createAccount(acc);
			assertNotNull(mobile);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			//System.out.println(e.getMessage());
		}
		
	}
	
	/*@Test
	public void testExistingAccount(){
		
		acc.setPhone("1111111111");
		try {
			service.createAccount(acc);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			assertEquals("", e.getMessage());
		}
		
	}*/
	
	
	@Test
	public void testShowBalanceExistingAccountWrongNumber(){
		acc.setPhone("11111111");
		try {
			service.AccountBalance(acc.getPhone());
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			assertEquals("Mobile number should contain 10 digits", e.getMessage());
		}
		
	}
	
	@Test
	public void testShowBalanceExistingName(){
		acc.setPhone("1111111111");
		try {
			acc = service.TransactionDetails(acc.getPhone());
			service.AccountBalance(acc.getPhone());
			assertEquals("Surya",acc.getName() );
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			
		}
		
	}
	
	@Test
	public void testShowBalanceExisting(){
		acc.setPhone("1111111111");
		try {
			//acc = service.getAccountDetails(acc.getPhone());
			//System.out.println(service.showBalance(acc.getPhone()));
			assertEquals(500.0, service.AccountBalance(acc.getPhone()),0.5);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			
		}
		
	}
	
	@Test
	public void testDepositCheckMobile(){
		acc.setPhone("1111111");
		double depositAmt = 1500.0;
		try {
			if(service.validateMobile(acc.getPhone())){
				//acc = service.getAccountDetails(acc.getPhone());
				double bal = service.deposit(acc.getPhone(), depositAmt);
				assertNotEquals(acc.getBalance(), bal);
			}
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			assertEquals("Mobile number should contain 10 digits", e.getMessage());
		}
		
	}
	
	@Test
	public void testDepositCheckBalance(){
		acc.setPhone("1111111111");
		double depositAmt = -1500.0;
		try {
			if(service.validateBalance(acc.getBalance())){
				//acc = service.getAccountDetails(acc.getPhone());
				double bal = service.deposit(acc.getPhone(), depositAmt);
				assertNotEquals(acc.getBalance(), bal);
			}
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			assertEquals("Balance must be a number greater than zero", e.getMessage());
		}
		
	}
	
	@Test
	public void testDepositCorrect(){
		acc.setPhone("1111111111");
		double depositAmt = 1500.0;
		try {
			if(service.validateBalance(acc.getBalance())){
				double bal = service.deposit(acc.getPhone(), depositAmt);
				assertNotEquals(acc.getBalance(), bal);
			}
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			
		}
	} //Account balance is Low
	
	@Test
	public void testWithdrawCheckMobile(){
		acc.setPhone("11111111");
		double withdrawAmt = 1500.0;
		try {
			if(service.validateMobile(acc.getPhone())){
				double bal = service.withdraw(acc.getPhone(), withdrawAmt);
				assertNotEquals(acc.getBalance(), bal);
			}
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			assertEquals("Mobile number should contain 10 digits", e.getMessage());
		}
		
	}
	
	@Test
	public void testWithdrawCheckBalance(){
		acc.setPhone("1111111111");
		double amt = -1500.0;
		try {
			if(service.validateBalance(acc.getBalance())){
				//acc = service.getAccountDetails(acc.getPhone());
				double bal = service.withdraw(acc.getPhone(), amt);
				assertNotEquals(acc.getBalance(), bal);
			}
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			assertEquals("Balance must be a number greater than zero", e.getMessage());
		}
		
	}
	
	
	/*@Test
	public void testWithdrawCorrect(){
		acc.setPhone("1111111111");
		double withdrawAmt = 20000.0;
		try {
			double bal = service.withdraw(acc.getPhone(), withdrawAmt);
			assertNotEquals(acc.getBalance(), bal);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			assertEquals("Account Does not exist", e.getMessage());
			
		}
	}*/
	
	@Test
	public void testFundTransferMobile1Valid(){
		Account acc1 = new Account();
		Account acc2 = new Account();
		acc1.setPhone("333333");
		acc2.setPhone("2222222222");
		double amount = 100;
		try {
			service.fundTransfer(acc1.getPhone(), acc2.getPhone(), amount);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			assertEquals("Mobile number should contain 10 digits", e.getMessage());
		}
	}
	
	@Test
	public void testFundTransferMobile2Valid(){
		Account acc1 = new Account();
		Account acc2 = new Account();
		acc1.setPhone("3333333333");
		acc2.setPhone("222222");
		double amount = 100;
		try {
			service.fundTransfer(acc1.getPhone(), acc2.getPhone(), amount);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			assertEquals("Mobile number should contain 10 digits", e.getMessage());
		}
	}
	
	@Test
	public void testFundTransferAmountValidation(){
		Account acc1 = new Account();
		Account acc2 = new Account();
		acc1.setPhone("3333333333");
		acc2.setPhone("2222222222");
		double amount = -100;
		try {
			service.fundTransfer(acc1.getPhone(), acc2.getPhone(), amount);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			assertEquals("Amount must be a number greater than zero", e.getMessage());
		}
	}
	
	/*@Test
	public void testFundTransferMoreAmount(){
		Account acc1 = new Account();
		Account acc2 = new Account();
		acc1.setPhone("3333333333");
		acc2.setPhone("2222222222");
		double amount = 1500;
		try {
			service.fundTransfer(acc1.getPhone(), acc2.getPhone(), amount);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			assertEquals("Account Does not exist", e.getMessage());
		}
	}	*/
	
	/*@Test
	public void testFundTransferNonExistingAmount(){
		Account acc1 = new Account();
		Account acc2 = new Account();
		acc1.setPhone("3333333333");
		acc2.setPhone("1111111111");
		double amount = 150;
		try {
			service.fundTransfer(acc1.getPhone(), acc2.getPhone(), amount);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			assertEquals("Account Does not exist", e.getMessage());
		}
	}*/
	
	@Test
	public void testFundTransferExistingAmount(){
		Account acc1 = new Account();
		Account acc2 = new Account();
		acc1.setPhone("3333333333");
		acc2.setPhone("2222222222");
		double amount = 150;
		try {
			service.fundTransfer(acc1.getPhone(), acc2.getPhone(), amount);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testPrintTransaction(){
		acc = new Account();
		acc.setPhone("1111111111");
		try {
			Account ac = service.TransactionDetails(acc.getPhone());
			assertNotNull(ac);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}
	
	
}
