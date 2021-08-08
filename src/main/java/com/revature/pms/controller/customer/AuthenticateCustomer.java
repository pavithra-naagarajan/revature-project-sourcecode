package com.revature.pms.controller.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.pms.model.Customer;
import com.revature.pms.service.CustomerService;
import com.revature.pms.service.CustomerServiceImpl;

/**
 * Servlet implementation class AuthenticateCustomer
 */
public class AuthenticateCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthenticateCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String customerName = request.getParameter("username");
		String password = request.getParameter("password");
		int flag=0;
		
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html");
		out.println("<html><body bgcolor=lightpink>");
		CustomerService customerService = new CustomerServiceImpl();
		List<Customer> customerList=customerService.getAllCustomers();
		for(Customer c:customerList) {
			
			if(c.getCustomerName().equals(customerName) ){
				if(c.getCustomerPassword().equals(password) ){
			
				flag=1;
				break;
				}
			}
			else 
				flag=0;	
		}
			
		if(flag==1) {
			out.println("<h2>You have logged in at : " + new java.util.Date());
			out.println("<br>Your name is : " + customerName);
			out.println("<br>Your password is : " + password);
			out.println("<br/><br/><a href=CustomerPage.html>Customer Page</a>");
			
		}
		else {
			out.println("<br><h2>Your login details are not matched!Try again");
			out.println("<br/><br/><a href=Login.html>Login Again</a>");
		}
		
		boolean alreadyVisited = false;
		Cookie allCookies[] = request.getCookies();

		
		
		

		if (allCookies == null) {
			
		} else {
			for (Cookie c : allCookies) {
				if (c.getName().equals(customerName)) {
					alreadyVisited = true;
					break;
				}
			}
		}

		if (alreadyVisited) {
			out.println("<h3>Your have already visited my website : " + customerName);

		} else {
			out.println("<h3>You are first time visitor " + customerName);
			Cookie c = new Cookie(customerName, customerName);
			c.setMaxAge(1000*60*60*24*30);
			response.addCookie(c);
		}

		
		out.println("</body></html>");
	}

}
