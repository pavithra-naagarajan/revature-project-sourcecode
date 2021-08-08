package com.revature.pms.service;

import java.util.List;

import com.revature.pms.dao.EmployeeDAO;
import com.revature.pms.dao.EmployeeDAOImpl;
import com.revature.pms.model.Customer;
import com.revature.pms.model.Employee;

public class EmployeeServiceImpl implements EmployeeService {

	EmployeeDAO employeeDAO =new EmployeeDAOImpl();
	@Override
	public boolean addEmployee(Employee employee) {
		return employeeDAO.addEmployee(employee);
		
	}

	@Override
	public boolean updateEmployee(Employee employee) {
		return employeeDAO.updateEmployee(employee);
	}

	@Override
	public boolean deleteEmployee(int employeeId) {
		return employeeDAO.deleteEmployee(employeeId);
	}

	@Override
	public Employee getEmployeeById(int employeeId) {
		return employeeDAO.getEmployeeById(employeeId);
	}

	@Override
	public List<Employee> getEmployeeByName(String employeeName) {
		return employeeDAO.getEmployeeByName(employeeName);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeDAO.getAllEmployees();
	}

	@Override
	public boolean isEmployeeExists(int employeeId) {
		return employeeDAO.isEmployeeExists(employeeId);
	}

	@Override
	public int withdrawalOfEmployee(int employeeId,String employeePassword,int amount)  {
		return employeeDAO.withdrawalOfEmployee(employeeId,employeePassword,amount);
	}

	@Override
	public boolean transferAmountForEmployee(int senderId, int receiverId, int amount) {
		return employeeDAO.transferAmountForEmployee(senderId, receiverId, amount);
		

	}

	@Override
	public int depositOfEmployee(int employeeId,String employeePassword,int amount) {
		return employeeDAO.depositOfEmployee(employeeId, employeePassword, amount);
	}

	@Override
	public void employeeApproval(Customer customer) {
		

	}

	@Override
	public int checkBalanceOfEmployee(int employeeId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
