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

public class Login extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out=response.getWriter();
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String sql="SELECT * FROM LOGIN";
		response.setContentType("text/html");
		boolean status=false;
		String  emails=null;
		boolean stat=false;
		
		try(Connection con=Util.getConnection();
			Statement stmt=con.createStatement())
		{
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				emails=rs.getString("email");
				if(emails.equals(email)&&rs.getString("password").equals(password))
				{
					status=true;
				}
				else if(emails.equals(email))
				{
					stat=true;
				}
			}
			if(status)
			{
				out.println("Login successfully");
				RequestDispatcher rd=request.getRequestDispatcher("Profile");
				rd.forward(request, response);
			}
			else
			{
				if(stat)
				{
					out.println("Worng Password");
					RequestDispatcher rd=request.getRequestDispatcher("Login.html");
					rd.include(request, response);
				}
				else
				{
					out.println("Worng Email and Password");
					RequestDispatcher rd=request.getRequestDispatcher("Login.html");
					rd.include(request, response);
				}
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
