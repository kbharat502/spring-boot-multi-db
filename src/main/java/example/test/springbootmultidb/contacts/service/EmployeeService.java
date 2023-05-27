package example.test.springbootmultidb.contacts.service;

import example.test.springbootmultidb.contacts.exception.EmployeeCreationException;
import example.test.springbootmultidb.contacts.exception.EmployeeNotFoundException;
import example.test.springbootmultidb.contacts.model.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

	EmployeeDTO getEmployeeById(long id);
	
	List<EmployeeDTO> getEmployeesByDepartmentName(String deptName);

	List<EmployeeDTO> getEmployeesByPhoneNumber(Long number);

	EmployeeDTO addEmployee(EmployeeDTO employee) throws EmployeeCreationException;

	void deleteEmployeeById(Long id) throws EmployeeNotFoundException;
}
