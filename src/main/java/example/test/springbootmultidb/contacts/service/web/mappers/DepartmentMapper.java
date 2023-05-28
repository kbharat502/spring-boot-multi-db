package example.test.springbootmultidb.contacts.service.web.mappers;

import example.test.springbootmultidb.contacts.db.entities.Department;
import example.test.springbootmultidb.contacts.model.DepartmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {EmployeeMapper.class},
		unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartmentMapper {

	DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);
	
	@Mapping(source = "id", target = "deptId")
	Department modelToEntity(DepartmentDTO model);
	
	@Named("department")
	@Mapping(source = "deptId", target = "id")
	@Mapping(qualifiedByName = {"EmployeeModel"}, target = "employees")
	DepartmentDTO entityToModel(Department entity);
	
	@Named("departmentNoEmployees")
	@Mapping(source = "deptId", target = "id")
	@Mapping(target = "employees", ignore = true)
	DepartmentDTO entityToModelIgnoreEmployees(Department entity);
}
