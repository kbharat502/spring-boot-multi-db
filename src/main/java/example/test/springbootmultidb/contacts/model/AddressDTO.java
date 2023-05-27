package example.test.springbootmultidb.contacts.model;

import example.test.springbootmultidb.contacts.model.enums.AddressDTOType;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO {

	@NotBlank(message = "{address.street1.notBlank}")
	private String street1;
	
	private String street2;
	
	@NotBlank(message = "{address.city.notBlank}")
	private String city;
	
	@NotBlank(message = "{address.state.notBlank}")
	private String state;
	
	@NotBlank(message = "{address.zipcode.notBlank}")
	@Size(message = "{address.zipcode.size}", min = 5, max = 9)
	@Digits(message = "{address.zipcode.digits}", integer = 9, fraction = 0)
	private String zipcode;

	@NotNull(message = "{address.type.notNull}")
	private AddressDTOType type;
}
