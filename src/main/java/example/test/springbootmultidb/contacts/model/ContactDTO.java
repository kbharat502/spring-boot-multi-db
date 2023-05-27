package example.test.springbootmultidb.contacts.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactDTO {

	@Null(message = "{id.should.not.supplied.from.object}")
	private Long id;
	
	@Valid
	private List<AddressDTO> addresses;
	
	@Email(message = "{contact.email.invalid}")
	private String email;
	
	@Valid
	private List<PhoneDTO> phones;
	
	
}
