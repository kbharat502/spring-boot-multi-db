package example.test.springbootmultidb.contacts.service.web.mappers.impl;

import example.test.springbootmultidb.contacts.db.entities.Department;
import example.test.springbootmultidb.contacts.db.entities.Employee;
import example.test.springbootmultidb.contacts.model.DepartmentDTO;
import example.test.springbootmultidb.contacts.model.EmployeeDTO;
import example.test.springbootmultidb.contacts.service.web.mappers.DepartmentMapper;
import example.test.springbootmultidb.contacts.service.web.mappers.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@Component
public class DepartmentMapperImpl implements DepartmentMapper {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Department modelToEntity(DepartmentDTO model) {
        if ( model == null ) {
            return null;
        }

        Department department = new Department();

        if ( model.getId() != null ) {
            department.setDeptId( model.getId() );
        }
        department.setName( model.getName() );
        department.setEmployees( employeeDTOListToEmployeeSet( model.getEmployees() ) );

        return department;
    }

    @Override
    public DepartmentDTO entityToModel(Department entity) {
        if ( entity == null ) {
            return null;
        }

        DepartmentDTO.DepartmentDTOBuilder departmentDTO = DepartmentDTO.builder();

        departmentDTO.id( entity.getDeptId() );
        departmentDTO.employees( employeeSetToEmployeeDTOList( entity.getEmployees() ) );
        departmentDTO.name( entity.getName() );

        return departmentDTO.build();
    }

    @Override
    public DepartmentDTO entityToModelIgnoreEmployees(Department entity) {
        if ( entity == null ) {
            return null;
        }

        DepartmentDTO.DepartmentDTOBuilder departmentDTO = DepartmentDTO.builder();

        departmentDTO.id( entity.getDeptId() );
        departmentDTO.name( entity.getName() );

        return departmentDTO.build();
    }

    protected Set<Employee> employeeDTOListToEmployeeSet(List<EmployeeDTO> list) {
        if ( list == null ) {
            return null;
        }

        Set<Employee> set = new LinkedHashSet<Employee>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( EmployeeDTO employeeDTO : list ) {
            set.add( employeeMapper.modelToEntity( employeeDTO ) );
        }

        return set;
    }

    protected List<EmployeeDTO> employeeSetToEmployeeDTOList(Set<Employee> set) {
        if ( set == null ) {
            return null;
        }

        List<EmployeeDTO> list = new ArrayList<EmployeeDTO>( set.size() );
        for ( Employee employee : set ) {
            list.add( employeeMapper.entityToModel( employee ) );
        }

        return list;
    }
}
