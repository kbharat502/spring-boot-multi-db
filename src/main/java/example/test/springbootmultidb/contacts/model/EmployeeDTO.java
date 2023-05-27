package example.test.springbootmultidb.contacts.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {

	@Null(message = "{id.should.not.supplied.from.object}")
	private Long id;
	
	@NotBlank(message = "{employee.first.name.required}")
	private String firstName;

	@NotBlank(message = "{employee.last.name.required}")
	private String lastName;
	
	//@JsonFormat(pattern="yyyy-MM-dd")
	@NotNull(message = "{employee.date.of.birth.required}")
	@Past(message = "{employee.date.of.birth.in.past}")
	private LocalDate dateOfBirth;

	//@JsonFormat(pattern="yyyy-MM-dd")
	@NotNull(message = "{employee.join.date.required}")
	@FutureOrPresent(message = "{employee.join.date.present.or.future}")
	private LocalDate joinDate;

	@Valid
	private ContactDTO contact;

	@Valid
	private DepartmentDTO department;
}
