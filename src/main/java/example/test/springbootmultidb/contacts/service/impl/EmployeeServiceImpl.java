package example.test.springbootmultidb.contacts.service.impl;

import example.test.springbootmultidb.contacts.db.entities.Address;
import example.test.springbootmultidb.contacts.db.entities.Contact;
import example.test.springbootmultidb.contacts.db.entities.Department;
import example.test.springbootmultidb.contacts.db.entities.Employee;
import example.test.springbootmultidb.contacts.db.entities.Phone;
import example.test.springbootmultidb.contacts.db.repo.DepartmentRepository;
import example.test.springbootmultidb.contacts.db.repo.EmployeeRepository;
import example.test.springbootmultidb.contacts.db.specs.EmployeeSpecification;
import example.test.springbootmultidb.contacts.exception.EmployeeCreationException;
import example.test.springbootmultidb.contacts.exception.EmployeeNotFoundException;
import example.test.springbootmultidb.contacts.model.EmployeeDTO;
import example.test.springbootmultidb.contacts.service.EmployeeService;
import example.test.springbootmultidb.contacts.service.web.mappers.DepartmentMapper;
import example.test.springbootmultidb.contacts.service.web.mappers.EmployeeMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	private static final Logger LOG = LogManager.getLogger(EmployeeServiceImpl.class);

	private EmployeeRepository eRepo;

	private DepartmentRepository dRepo;
	
	private EmployeeMapper eMapper;

	private DepartmentMapper dMapper;
	
	public EmployeeServiceImpl(EmployeeRepository eRepo, EmployeeMapper eMapper,
							   DepartmentRepository dRepo, DepartmentMapper dMapper) {
		this.eRepo = eRepo;
		this.eMapper = eMapper;
		this.dRepo = dRepo;
		this.dMapper = dMapper;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public EmployeeDTO getEmployeeById(long id) {
		Employee employee = eRepo.getOne(id);
		EmployeeDTO employeeDTO = null;
		if(null != employee) {
			employeeDTO = eMapper.entityToModel(employee);
		}
		return employeeDTO;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<EmployeeDTO> getEmployeesByDepartmentName(String deptName) {

		List<EmployeeDTO> empList = null; 
		List<Employee> employeeList = eRepo.findByDepartmentName(deptName);
		if(!CollectionUtils.isEmpty(employeeList)) {
			empList = eMapper.entitiesToModels(employeeList);
		}
		return empList;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<EmployeeDTO> getEmployeesByPhoneNumber(Long number) {
		//List<Employee> employees = eRepo.findAll(EmployeeSpecification.findEmployeeByPhone(number));
		//List<EmployeeDTO> list = eMapper.entitiesToModels(employees);
		List<Employee> employees = eRepo.findAll(EmployeeSpecification.findEmployeeOnlyByPhone(number));
		EmployeeDTO employeeDTO = eMapper.entityToModelIgnoreAllChildren(employees.get(0));
		List<EmployeeDTO> list = Arrays.asList(employeeDTO);
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public EmployeeDTO addEmployee(EmployeeDTO employee) throws EmployeeCreationException {
		EmployeeDTO savedEmp = null;
		try{
			Employee employeeEntity = eMapper.modelToEntity( employee );
			employeeEntity.setCreatedBy("ASDF");
			employeeEntity.setCreatedDate(LocalDateTime.now());
			employeeEntity.setUpdatedBy("ASDF");
			employeeEntity.setUpdatedDate(LocalDateTime.now());

			Contact contactEntity = employeeEntity.getContact();
			contactEntity.setEmployee(employeeEntity);

			for(Address address : contactEntity.getAddresses()) {
				address.setContact(contactEntity);
			}

			for(Phone phone : contactEntity.getPhones()) {
				phone.setContact(contactEntity);
			}

			if(employee.getDepartment() != null) {
				Optional<Department> dept = dRepo.findByName(employee.getDepartment().getName());
				if(dept.isPresent()) {
					employeeEntity.setDepartment(dept.get());
				}
			}

			Employee savedEmployee = eRepo.save(employeeEntity);

			savedEmp = eMapper.entityToModel(savedEmployee);
		} catch (DataAccessException dae) {
			LOG.error("Exception while saving the employee object {}", dae.getMessage(), dae);
			throw new EmployeeCreationException(dae.getMessage());
		}

		return savedEmp;
	}

	@Override
	public void deleteEmployeeById(Long id) throws EmployeeNotFoundException {

		Optional<Employee> byId = eRepo.findById(id);
		if(!byId.isPresent()) {
			throw new EmployeeNotFoundException("No employee found with ID: " + id);
		}

		eRepo.deleteById(id);
	}

}
