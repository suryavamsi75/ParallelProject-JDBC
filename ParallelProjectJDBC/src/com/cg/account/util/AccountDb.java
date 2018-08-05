package com.cg.account.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.cg.account.exception.AccountException;

public class AccountDb {
	static String url="jdbc:oracle:thin:@localhost:1521:XE";
	static String connUrl="oracle.jdbc.driver.OracleDriver";
	
	public static Connection getConnection() throws AccountException{
		
		try {
			Class.forName(connUrl);
			return DriverManager.getConnection(url,"system","orcl11g");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new AccountException(e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new AccountException(e.getMessage());
		}		
	}
}
