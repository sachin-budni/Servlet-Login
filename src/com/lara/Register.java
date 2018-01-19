package com.lara;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out=response.getWriter();
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String mobileNumber=request.getParameter("mobileNo");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		response.setContentType("text/html");
		String sql="SELECT * FROM LOGIN";
		String sql1="INSERT INTO LOGIN VALUES('"+firstName+"','"+lastName+"',"+mobileNumber+",'"+email+"','"+password+"')";
		boolean status=false;
		
		try(Connection con=Util.getConnection();
			Statement stmt=con.createStatement())
		{
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				if(rs.getString("email").equals(email))
				{
					status=true;
				}
			}
			if(status)
			{
				out.println("Accout Already Created");
				RequestDispatcher rd=request.getRequestDispatcher("Register.html");
				rd.include(request, response);
			}
			else
			{
				stmt.execute(sql1);
				out.println("Account Succussfully Created");
				RequestDispatcher rd=request.getRequestDispatcher("Login.html");
				rd.include(request, response);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
