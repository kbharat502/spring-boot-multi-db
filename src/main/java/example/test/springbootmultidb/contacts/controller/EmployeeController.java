package example.test.springbootmultidb.contacts.controller;

import example.test.springbootmultidb.contacts.exception.EmployeeCreationException;
import example.test.springbootmultidb.contacts.exception.EmployeeNotFoundException;
import example.test.springbootmultidb.contacts.model.EmployeeDTO;
import example.test.springbootmultidb.contacts.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/employees")
public class EmployeeController extends AbstractController {

	private static final Logger LOG = LogManager.getLogger(EmployeeController.class);

	private EmployeeService eService;
	
	public EmployeeController(EmployeeService eService) {
		this.eService = eService;
	}
	
	@GetMapping(path = "/{empId}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@Digits(message = "{employee.id.required}", integer = 9, fraction = 0) @PathVariable("empId") Long employeeId) {
		EmployeeDTO employeeDTO = eService.getEmployeeById(employeeId);
		
		return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
	}
	
	@GetMapping(path = "/departmentName/{deptName}")
	public ResponseEntity<List<EmployeeDTO>> getEmployeeByDepartmentName(@PathVariable("deptName") String deptName) {
		List<EmployeeDTO> EmployeeList = eService.getEmployeesByDepartmentName(deptName);
		
		return new ResponseEntity<>(EmployeeList, HttpStatus.OK);
	}

	@GetMapping(path = "/phone/{phNo}")
	public ResponseEntity<List<EmployeeDTO>> getEmployeeByPhoneNumber(@PathVariable("phNo") Long phNo) {
		List<EmployeeDTO> EmployeeList = eService.getEmployeesByPhoneNumber(phNo);

		return new ResponseEntity<>(EmployeeList, HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addNewEmployee(@Valid @RequestBody EmployeeDTO employeeDTO,
											HttpServletRequest request) throws EmployeeCreationException {

		//Validate the employee DTO and save the employee details.
		EmployeeDTO empDTO = eService.addEmployee(employeeDTO);

		HttpHeaders headers = new HttpHeaders();

		buildLocationHeader(request, headers,
				String.join("", "/employees/", String.valueOf(empDTO.getId())));
		/*StringBuffer requestURL = request.getRequestURL();
		LOG.debug("Request URI: {}", requestURL.toString());

		String contextPath = request.getContextPath();
		LOG.debug("Context Path: {}", contextPath);

		String domainPath = requestURL.toString().substring(0,
				(requestURL.toString().indexOf(contextPath) + (contextPath.length())));
		LOG.debug("Domain Path: {}", domainPath);



		headers.add("Location", (domainPath + "/employees/" + empDTO.getId()));*/

		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/{empId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void handleDelete(@PathVariable("empId") Long empId) throws EmployeeNotFoundException {

		eService.deleteEmployeeById(empId);
	}
}
