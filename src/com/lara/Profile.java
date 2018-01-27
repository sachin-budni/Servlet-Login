package com.lara;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Profile() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String email=request.getParameter("email");
		String sql="SELECT * FROM LOGIN";
		int k=ResultSet.TYPE_SCROLL_SENSITIVE;
		int l=ResultSet.CONCUR_UPDATABLE;
		try(Connection con=Util.getConnection();
			Statement stmt=con.createStatement(k,l))
		{
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				String mail=rs.getString("email");
				if(email.equals(mail))
				{
					out.println("<center>");
					out.println("<h1>Profile</h1>");
					out.println("<table>");
					out.println("<tr>");
					out.println("<th>First Name :<th/><td>"+rs.getString("firstname")+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<th>Last Name :<th/><td>"+rs.getString("lastname")+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<th>Mobile Number :<th/><td>"+rs.getString("mobilenumber")+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<th>Email :<th/><td>"+mail+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<th>PassWord :<th/><td>"+rs.getString("password")+"</td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</center>");
				}
			}			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

}
