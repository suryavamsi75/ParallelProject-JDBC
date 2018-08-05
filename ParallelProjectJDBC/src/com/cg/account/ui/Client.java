package com.cg.account.ui;

import java.util.Date;
import java.util.Scanner;

import com.cg.account.bean.Account;
import com.cg.account.exception.AccountException;
import com.cg.account.service.AccountService;
import com.cg.account.service.IAccountService;

public class Client {
	Scanner sc = new Scanner(System.in);
	IAccountService serv = new AccountService();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Client ui = new Client();
		String opt = "";
		while (true) {
			System.out.println("1. Create Account\n2. Account balance\n3. Deposit amount\n"
							+ "4. Withdraw amount\n5. Transfer\n6. TransactionDetails\n7. Exit");
			System.out.println("Enter choice:");
			opt = sc.nextLine();
			switch (opt) {
			case "1":
				ui.accountCreation();
				break;
			case "2":
				ui.showBalance();
				break;
			case "3":
				ui.depositAmount();
				break;
			case "4":
				ui.withdrawAmount();
				break;
			case "5":
				ui.fundTransfer();
				break;
			case "6":
				ui.printTransaction();
				break;
			case "7":
				sc.close();
				System.exit(0);
				break;
			default:
				System.out.println("Enter Correct option");
				break;
			}
		}
	}

	private void accountCreation() {
		Account a= new Account();
		Client ui = new Client();

		System.out.println("Enter Name:");
		a.setName(ui.sc.nextLine());
		System.out.println("Enter Mobile Number:");
		a.setPhone(ui.sc.nextLine());
		System.out.println("Enter Email ID:");
		a.setEmail(ui.sc.nextLine());
		System.out.println("Enter current balance:");
		a.setBalance(Double.parseDouble(ui.sc.nextLine()));
		a.setDate(""+ new Date());

		try {
			String id = serv.createAccount(a);
			System.out.println(id + " has been added successfully");
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			System.out.println();
			System.err.println(e.getMessage());
			System.out.println();
		}

	}

	private void showBalance() {
		System.out.println("Enter mobile number:");
		String mobile = sc.nextLine();

		try {
			double bal = serv.AccountBalance(mobile);
			System.out.println("Current balance:" + bal);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			System.out.println();
			System.err.println(e.getMessage());
			System.out.println();
		}
	}

	private void depositAmount() {
		// TODO Auto-generated method stub
		System.out.println("Enter mobile number");
		String mobile = sc.nextLine();
		try {
			if (serv.validateMobile(mobile)) {
				//Account a = serv.getAccountDetails(mobile);
				System.out.println("Enter amount to be deposited:");
				double amt = Double.parseDouble(sc.nextLine());
				double finalAmt = serv.deposit(mobile, amt);
				System.out.println("Account with mobile id:" + mobile
						+ " has been deposited with " + amt);
				System.out.println("Current Balance in the account:" + finalAmt);
			}

		} catch (AccountException e) {
			// TODO Auto-generated catch block
			System.out.println();
			System.err.println(e.getMessage());
			System.out.println();
		}

	}

	private void withdrawAmount() {
		// TODO Auto-generated method stub
		System.out.println("Enter mobile number");
		String mobile = sc.nextLine();
		try {
			if (serv.validateMobile(mobile)) {
				//Account a = serv.getAccountDetails(mobile);
				System.out.println("Enter amount to be withdrawn:");
				double amt = Double.parseDouble(sc.nextLine());
				double finalAmt = serv.withdraw(mobile, amt);
				System.out.println("Account with mobile id:" + mobile
						+ " has been withdrawn with " + amt);
				System.out
						.println("Current Balance in the account:" + finalAmt);
			}

		} catch (AccountException e) {
			// TODO Auto-generated catch block
			System.out.println();
			System.err.println(e.getMessage());
			System.out.println();
		}

	}

	private void fundTransfer() {
		// TODO Auto-generated method stub
		System.out.println("Enter your mobile no:");
		String m1 = sc.nextLine();
		System.out.println("Enter receivers mobile number:");
		String m2 = sc.nextLine();
		System.out.println("Enter Transfer Amount:");
		double amount = Double.parseDouble(sc.nextLine());
		try {
			boolean res = serv.fundTransfer(m1, m2, amount);
			if (res)
				System.out.println("Fund Transferred");

		} catch (AccountException e) {
			// TODO Auto-generated catch block
			System.out.println();
			System.err.println(e.getMessage());
			System.out.println();
		}

	}

	private void printTransaction() {
		// TODO Auto-generated method stub
		System.out.println("Enter Mobile number");
		String mobile = sc.nextLine();
		try {
			System.out.println(serv.TransactionDetails(mobile));
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			System.out.println();
			System.err.println(e.getMessage());
			System.out.println();
		}

	}
}
