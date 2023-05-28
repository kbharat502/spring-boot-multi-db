package example.test.springbootmultidb.contacts.service.web.mappers;

import example.test.springbootmultidb.contacts.db.entities.Employee;
import example.test.springbootmultidb.contacts.model.EmployeeDTO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {ContactMapper.class, DepartmentMapper.class},
		unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {

	EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

	@Mappings({
		@Mapping(source = "id", target = "empId"),
		@Mapping(target = "department", ignore = true)
	})
	Employee modelToEntity(EmployeeDTO model);
	
	@Named("EmployeeModel")
	@Mapping(source = "empId", target = "id")
	@Mapping(target = "contact", qualifiedByName = "contactIgnoreEmployee")
	@Mapping(target = "department", qualifiedByName = "departmentNoEmployees")
	EmployeeDTO entityToModel(Employee entity);
	
	//@Mapping(qualifiedByName = "EmployeeModel", target = "empty")
	@IterableMapping(elementTargetType = EmployeeDTO.class, qualifiedByName = "EmployeeModel")
	List<EmployeeDTO> entitiesToModels(List<Employee> entity);
	
	@Mappings({
		@Mapping(source = "empId", target = "id"), 
		@Mapping(target = "contact", ignore = true),
		@Mapping(target = "department", qualifiedByName = "departmentNoEmployees")
	})
	EmployeeDTO entityToModelIgnoreContacts(Employee entity);
	
	@Mappings({
		@Mapping(source = "empId", target = "id"),
		@Mapping(target = "contact", qualifiedByName = "contactIgnoreEmployee"),
		@Mapping( target = "department", ignore = true)
	})
	EmployeeDTO entityToModelIgnoreDepartment(Employee entity);
	
	@Mappings({
		@Mapping(source = "empId", target = "id"), 
		@Mapping( target = "contact", ignore = true), 
		@Mapping( target = "department", ignore = true)
	})
	EmployeeDTO entityToModelIgnoreAllChildren(Employee entity);
}
