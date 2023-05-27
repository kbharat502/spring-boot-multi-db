package example.test.springbootmultidb.contacts.service.web.mappers;

import example.test.springbootmultidb.contacts.db.entities.Employee;
import example.test.springbootmultidb.contacts.model.EmployeeDTO;

import java.util.List;

public interface EmployeeMapper {

	Employee modelToEntity(EmployeeDTO model);

	EmployeeDTO entityToModel(Employee entity);

	List<EmployeeDTO> entitiesToModels(List<Employee> entity);

	EmployeeDTO entityToModelIgnoreContacts(Employee entity);

	EmployeeDTO entityToModelIgnoreDepartment(Employee entity);

	EmployeeDTO entityToModelIgnoreAllChildren(Employee entity);
}
