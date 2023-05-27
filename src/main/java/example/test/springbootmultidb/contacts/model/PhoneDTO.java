package example.test.springbootmultidb.contacts.model;

//import javax.validation.constraints.NotBlank;

import example.test.springbootmultidb.contacts.model.enums.PhoneDTOType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneDTO {

	@NotNull(message = "{phone.type.required}")
	private PhoneDTOType type;
	
	@NotBlank(message = "{phone.number.required}")
	private String number;
}
