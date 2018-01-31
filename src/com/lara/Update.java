package com.lara;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

public class Update extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public Update() 
    {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		ServletContext context=getServletContext();
		String email=(String)context.getAttribute("email");
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
					out.println("<form action=\"Updates\">");
					out.println("<center>");
					out.println("<h1>Profile</h1>");
					out.println("<table>");
					out.println("<tr>");
					out.println("<th>First Name :<th/><td><input type=\"text\" value=\""+rs.getString("firstname")+"\" name=\"firstName\"/></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<th>Last Name :<th/><td><input type\"text\" value=\""+rs.getString("lastname")+"\" name=\"lastName\"/></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<th>Mobile Number :<th/><td><input type=\"text\" value=\""+rs.getString("mobilenumber")+"\" name=\"mobileNo\"/></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<th>Email :<th/><td><input type=\"hidden\" name=\"email\" value=\""+mail+"\"/></td>");
					out.println("</tr>");
//					out.println("<tr>");
//					out.println("<th>PassWord :<th/><td>"+rs.getString("password")+"</td>");
//					out.println("</tr>");
					out.println("</table>");
					out.println("<input type=\"submit\" value=\"submit\">");
					out.println("</center>");
					out.println("</form>");
				}
			}			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}
