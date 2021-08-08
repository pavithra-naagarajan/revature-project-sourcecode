package com.revature.pms.controller.customer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.pms.model.Customer;
import com.revature.pms.service.CustomerService;
import com.revature.pms.service.CustomerServiceImpl;

/**
 * Servlet implementation class DeleteCustomer
 */
public class DeleteCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int customerId = Integer.parseInt(request.getParameter("customerId"));
		
		boolean result=false;
		boolean flag=false;
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html");
		out.println("<html><body bgcolor=lightpink>");
		CustomerService customerService = new CustomerServiceImpl();
		result=customerService.isCustomerExists(customerId);
		
		if(result==true) {
			
			customerService.deleteCustomer(customerId);
			flag=true;
	
		}
		else
			out.println("<h3>Customer not exist for given customer Id!");
		if(flag==true)
			out.println("<h3>Customer with customerId :"+customerId+" is deleted successfully!");
		else
			out.println("<h3>Customer with customerId :"+customerId+" is not deleted successfully!");
		out.println("<br><br><br><a href=CustomerPage.html>Customer Personal Page</a>");
	
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
