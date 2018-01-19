package com.lara;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util 
{
	static Connection con=null;
	static
	{
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} 
		catch (ClassNotFoundException e) 
		{
			System.out.println("Install th driver");
		}
	}
	public static Connection getConnection()
	{
		try 
		{
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","oracle");
		}
		catch (SQLException e) 
		{
			System.out.println("Connection properly Not Connect");
		}
		return con;
	}
}
