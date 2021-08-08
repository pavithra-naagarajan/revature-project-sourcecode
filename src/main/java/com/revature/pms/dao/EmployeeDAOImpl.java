package com.revature.pms.dao;

import java.util.List;


import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.pms.model.Customer;
import com.revature.pms.model.Employee;
import com.revature.pms.util.HibernateUtil;

public class EmployeeDAOImpl implements EmployeeDAO {

private static Logger logger = Logger.getLogger("CustomerDAOImpl");
	
	
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	Session session = sessionFactory.openSession();
	@Override
	public boolean addEmployee(Employee employee) {
		Transaction transaction = session.beginTransaction();
		session.save(employee);
		transaction.commit();
		return true;
	}

	@Override
	public boolean updateEmployee(Employee employee) {
		Transaction transaction = session.beginTransaction();
		session.update(employee);
		transaction.commit();
		return true;
	}

	@Override
	public boolean deleteEmployee(int employeeId) {
		Transaction transaction = session.beginTransaction();
		Employee employee=new Employee();
		employee.setEmployeeId(employeeId);
		session.delete(employee);
		transaction.commit();
		return true;
	}

	@Override
	public Employee getEmployeeById(int employeeId) {
		Employee employee=(Employee) session.get(Employee.class,employeeId);
		return employee;
	}

	@Override
	public List<Employee> getEmployeeByName(String employeeName) {
		Query query=session.createQuery("from com.revature.pms.model.Employee where employeeName='"+employeeName+"'");
		return query.list();
	}

	@Override
	public List<Employee> getAllEmployees() {
		Query query=session.createQuery("from com.revature.pms.model.Employee");
		return query.list();
	}

	public boolean isEmployeeExists(int employeeId) {
		Employee employee=(Employee) session.get(Employee.class,employeeId);
		if(employee==null)
			return false;
		else 
			return true;
	}

	@Override
	public int withdrawalOfEmployee(int employeeId,String employeePassword,int amount)  {
		Employee employee=new Employee();
		employee.setEmployeeId(employeeId);
		logger.info("The Previous balance for :"+employeeId + " is "+employee.getEmployeeBalance());
		int balance=employee.getEmployeeBalance()-amount;
		Query query = session.createQuery("update com.revature.pms.model.Employee set employeeBalance="+balance +"where employeeId = "+employeeId+"and employeePassword='"+employeePassword+"'");
		int finalBalance = Integer.parseInt( "query.list().get(0)");
		logger.info("The new balance for :"+employeeId + " is "+finalBalance);
		return finalBalance;
	}

	@Override
	public boolean transferAmountForEmployee(int senderId, int receiverId, int amount) {
		return false;
		// TODO Auto-generated method stub

	}

	@Override
	public int depositOfEmployee(int employeeId,String employeePassword,int amount)  {
		Employee employee=new Employee();
		employee.setEmployeeId(employeeId);
		logger.info("The Previous balance for :"+employeeId + " is "+employee.getEmployeeBalance());
		int balance=employee.getEmployeeBalance()+amount;
		Query query = session.createQuery("update com.revature.pms.model.Employee set employeeBalance="+balance +"where employeeId = "+employeeId+"and employeePassword='"+employeePassword+"'");
		int finalBalance = Integer.parseInt( "query.list().get(0)");
		logger.info("The new balance for :"+employeeId + " is "+finalBalance);
		return finalBalance;
	}

	@Override
	public void employeeApproval(Customer customer) {
		

	}

	@Override
	public int checkBalanceOfEmployee(int employeeId) {
		Query query = session.createQuery("select balance from com.revature.pms.model.Customer where customerId = "+employeeId);
		int balance = Integer.parseInt( "query.list().get(0)");
		logger.info("The balance for :"+employeeId + " is "+balance);
		return balance;
	}

}
