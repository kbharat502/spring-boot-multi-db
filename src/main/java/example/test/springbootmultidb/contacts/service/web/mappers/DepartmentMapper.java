package example.test.springbootmultidb.contacts.service.web.mappers;

import example.test.springbootmultidb.contacts.db.entities.Department;
import example.test.springbootmultidb.contacts.model.DepartmentDTO;

public interface DepartmentMapper {

	Department modelToEntity(DepartmentDTO model);

	DepartmentDTO entityToModel(Department entity);

	DepartmentDTO entityToModelIgnoreEmployees(Department entity);
}
