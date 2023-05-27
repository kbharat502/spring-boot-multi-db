package example.test.springbootmultidb.contacts.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDTO {

    @Null
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private LocalDate expectedStartDate;

    private LocalDate actualStartDate;

    @NotNull
    private LocalDate expectedFinishDate;

    private LocalDate actualFinishDate;

    @NotBlank
    private String createdBy;

    private String updatedBy;

    private List<EmployeeDTO> employees;
}
