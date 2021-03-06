package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.intercom.AccountClient;
import com.revature.models.Account;
import com.revature.models.Employee;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

private Logger log = Logger.getLogger(EmployeeController.class);
	
	private List<Employee> employees = new ArrayList<>();
	
	@Autowired
	AccountClient accountClient;
	
	public EmployeeController() {
		super();
		employees.add(new Employee(1, "Sally Jones", "sjonesie@gmail.com", null));
		employees.add(new Employee(2, "Paulina Jackson", "paulinaJ@gmail.com", null));
		employees.add(new Employee(3, "Ronald McCormick", "rmccormick@gmail.com", null));
	}
	
	@GetMapping
	public List<Employee> getAllEmployees(){
		log.info("getting all employees");
		return employees;
	}
	
	@GetMapping("/{employeeId}")
	public Employee getEmployeeById(@PathVariable("employeeId")Integer employeeId) {
		log.info("getting Employee by id:" + employeeId);
		Employee employee = employees.stream()
				.filter(cust -> cust.getEmployeeId()==employeeId)
				.findFirst().get();
		List<Account> employees = accountClient.getAccountsByEmployeeId(employeeId);
		employee.setAccounts(employees);
		return employee;
	}

}
