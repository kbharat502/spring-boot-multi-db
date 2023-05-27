package example.test.springbootmultidb.contacts.service.web.mappers.impl;

import example.test.springbootmultidb.contacts.db.entities.Employee;
import example.test.springbootmultidb.contacts.model.EmployeeDTO;
import example.test.springbootmultidb.contacts.service.web.mappers.ContactMapper;
import example.test.springbootmultidb.contacts.service.web.mappers.DepartmentMapper;
import example.test.springbootmultidb.contacts.service.web.mappers.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Autowired
    private ContactMapper contactMapper;
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public Employee modelToEntity(EmployeeDTO model) {
        if ( model == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setEmpId( model.getId() );
        employee.setDateOfBirth( model.getDateOfBirth() );
        employee.setFirstName( model.getFirstName() );
        employee.setJoinDate( model.getJoinDate() );
        employee.setLastName( model.getLastName() );
        employee.setContact( contactMapper.modelToEntity( model.getContact() ) );

        return employee;
    }

    @Override
    public EmployeeDTO entityToModel(Employee entity) {
        if ( entity == null ) {
            return null;
        }

        EmployeeDTO.EmployeeDTOBuilder employeeDTO = EmployeeDTO.builder();

        employeeDTO.id( entity.getEmpId() );
        employeeDTO.contact( contactMapper.entityToModelIgnoreEmployee( entity.getContact() ) );
        employeeDTO.department( departmentMapper.entityToModelIgnoreEmployees( entity.getDepartment() ) );
        employeeDTO.firstName( entity.getFirstName() );
        employeeDTO.lastName( entity.getLastName() );
        employeeDTO.dateOfBirth( entity.getDateOfBirth() );
        employeeDTO.joinDate( entity.getJoinDate() );

        return employeeDTO.build();
    }

    @Override
    public List<EmployeeDTO> entitiesToModels(List<Employee> entity) {
        if ( entity == null ) {
            return null;
        }

        List<EmployeeDTO> list = new ArrayList<EmployeeDTO>( entity.size() );
        for ( Employee employee : entity ) {
            list.add( entityToModel( employee ) );
        }

        return list;
    }

    @Override
    public EmployeeDTO entityToModelIgnoreContacts(Employee entity) {
        if ( entity == null ) {
            return null;
        }

        EmployeeDTO.EmployeeDTOBuilder employeeDTO = EmployeeDTO.builder();

        employeeDTO.id( entity.getEmpId() );
        employeeDTO.department( departmentMapper.entityToModelIgnoreEmployees( entity.getDepartment() ) );
        employeeDTO.firstName( entity.getFirstName() );
        employeeDTO.lastName( entity.getLastName() );
        employeeDTO.dateOfBirth( entity.getDateOfBirth() );
        employeeDTO.joinDate( entity.getJoinDate() );

        return employeeDTO.build();
    }

    @Override
    public EmployeeDTO entityToModelIgnoreDepartment(Employee entity) {
        if ( entity == null ) {
            return null;
        }

        EmployeeDTO.EmployeeDTOBuilder employeeDTO = EmployeeDTO.builder();

        employeeDTO.id( entity.getEmpId() );
        employeeDTO.contact( contactMapper.entityToModelIgnoreEmployee( entity.getContact() ) );
        employeeDTO.firstName( entity.getFirstName() );
        employeeDTO.lastName( entity.getLastName() );
        employeeDTO.dateOfBirth( entity.getDateOfBirth() );
        employeeDTO.joinDate( entity.getJoinDate() );

        return employeeDTO.build();
    }

    @Override
    public EmployeeDTO entityToModelIgnoreAllChildren(Employee entity) {
        if ( entity == null ) {
            return null;
        }

        EmployeeDTO.EmployeeDTOBuilder employeeDTO = EmployeeDTO.builder();

        employeeDTO.id( entity.getEmpId() );
        employeeDTO.firstName( entity.getFirstName() );
        employeeDTO.lastName( entity.getLastName() );
        employeeDTO.dateOfBirth( entity.getDateOfBirth() );
        employeeDTO.joinDate( entity.getJoinDate() );

        return employeeDTO.build();
    }
}
