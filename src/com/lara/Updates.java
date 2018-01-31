package com.lara;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Updates extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Updates() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String mobileNo=request.getParameter("mobileNo");
		String email=request.getParameter("email");
		
		String sql="update login set firstname='"+firstName+"', lastname='"+lastName+"', mobilenumber="+mobileNo+" where email='"+email+"'";
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		try(Connection con=Util.getConnection();
			Statement stmt=con.createStatement())
		{
			stmt.execute(sql);
			RequestDispatcher rd=request.getRequestDispatcher("Profile");
			rd.forward(request, response);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
