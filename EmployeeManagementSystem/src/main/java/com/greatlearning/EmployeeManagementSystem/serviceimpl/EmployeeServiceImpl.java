package com.greatlearning.EmployeeManagementSystem.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.greatlearning.EmployeeManagementSystem.entity.Employee;
import com.greatlearning.EmployeeManagementSystem.entity.Roles;
import com.greatlearning.EmployeeManagementSystem.entity.User;
import com.greatlearning.EmployeeManagementSystem.repository.EmployeeRepository;
import com.greatlearning.EmployeeManagementSystem.repository.RoleRepository;
import com.greatlearning.EmployeeManagementSystem.repository.UserRepository;
import com.greatlearning.EmployeeManagementSystem.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	BCryptPasswordEncoder bcryptEncoder;

	@Override
	public List<Employee> findAll() {

		return employeeRepository.findAll();
	}

	@Override
	public Optional<Employee> findById(int theId) {
		boolean ifEmployeeExist = employeeRepository.existsById(theId);
		if (ifEmployeeExist) {
			return employeeRepository.findById(theId);

		} else
			throw new RuntimeException("There is no Employee with id : " + theId);

	}

	@Override
	public String save(Employee theEmployee) {

		if (theEmployee.getFirstName().equals("") || theEmployee.getLastName().equals("")
				|| theEmployee.getEmail().equals("")) {
			throw new RuntimeException("Error!!! All fields are mandatory ");

		} else {
			employeeRepository.saveAndFlush(theEmployee);
			return "Below Employee Details Added Successfully \nid : " + theEmployee.getId()
					+ "\nFirst Name : " + theEmployee.getFirstName() + "\nLast Name : " + theEmployee.getLastName()
					+ "\nEmail : " + theEmployee.getEmail();
		}
	}

	@Override
	public String updateEmployee(Employee employee) {
		boolean ifEmployeeExist = employeeRepository.existsById(employee.getId());

		if (ifEmployeeExist) {
			employeeRepository.saveAndFlush(employee);
			return "Below Employee Details Updated Successfully \nid : " + employee.getId()
					+ "\nFirst Name : " + employee.getFirstName() + "\nLast Name : " + employee.getLastName()
					+ "\nEmail : " + employee.getEmail();
		} else {
			return "There is no Employee with id : " + employee.getId();

		}

	}

	@Override
	public String deleteById(int theId) {

		boolean ifEmployeeExist = employeeRepository.existsById(theId);

		if (ifEmployeeExist) {
			employeeRepository.deleteById(theId);
			return "Deleted employee id -" + theId;
		} else
			return "There is no Employee with id : " + theId;
	}

	@Override
	public List<Employee> searchByFirstName(String firstName) {
		List<Employee> employees = employeeRepository.findByFirstNameContainsAllIgnoreCase(firstName);
		if (employees.size() > 0)
			return employees;
		else
			throw new RuntimeException("Employee Details not found!!!");
	}

	@Override
	public List<Employee> sortByFirstName(String sortBy) {

		List<Employee> employees = employeeRepository.findAll(Sort.by(Direction.fromString(sortBy), "firstName"));
		if (employees.size() > 0)

			return employees;
		else
			throw new RuntimeException("Employee Details not found!!!");
	}

	@Override
	public User saveUser(User user) {

		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public Roles saveRole(Roles role) {

		return roleRepository.save(role);
	}

}
