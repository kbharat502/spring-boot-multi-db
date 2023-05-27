package example.test.springbootmultidb.contacts.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentDTO {

	@Null
	private Long id;

	@NotBlank(message = "{department.name.required}")
	private String name;

	@Valid
	private List<EmployeeDTO> employees;
}
