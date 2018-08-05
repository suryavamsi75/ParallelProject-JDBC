package com.cg.account.dao;

public interface AccountDaoQueryMapper {
	
	public String INSERT_QUERY = "INSERT INTO ACCOUNT VALUES(?, ?, ?, ?, ?)";
	public String UPDATE_BALANCE_QUERY = "UPDATE ACCOUNT SET BALANCE=?, MODDATE = ? WHERE MOBILE=?";
	public String GET_ALL_ACCOUNT_INFO = "SELECT * FROM ACCOUNT";
	public String GET_ACC_MOBILE_NO = "SELECT MOBILE FROM ACCOUNT";
	

}
