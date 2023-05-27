package example.test.springbootmultidb.contacts.db.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.LazyGroup;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
@ToString(exclude = {"employees"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "projects")
public class Project implements Serializable {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_generator")
    @SequenceGenerator(name="project_generator", sequenceName = "project_seq", allocationSize=1, initialValue = 1)
    @Column(name = "prj_id")
    private Long prjId;

    @Column(name = "prj_name")
    private String name;

    @Column(name = "expected_start_date")
    private LocalDate expectedStartDate;

    @Column(name = "actual_start_date")
    private LocalDate actualStartDate;

    @Column(name = "expected_finish_date")
    private LocalDate expectedFinishDate;

    @Column(name = "actual_finish_date")
    private LocalDate actualFinishDate;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "updated_by")
    private String updatedBy;

    @ManyToMany(targetEntity = Employee.class,
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "emp_prj",
            joinColumns = @JoinColumn(name="prj_id", referencedColumnName="prj_id"),
            inverseJoinColumns = @JoinColumn( name="emp_id", referencedColumnName="emp_id"))
    @LazyCollection(LazyCollectionOption.TRUE)
    @LazyGroup("projects")
    private List<Employee> employees;

    public void addEmployee(Employee employee) {
        if(this.employees == null) {
            this.employees = new ArrayList<>();
        }

        this.employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        Iterator<Employee> empIt = this.employees.listIterator();
        do {
            if(employee.getEmpId().equals(empIt.next().getEmpId())) {
                empIt.remove();
            }
        } while(empIt.hasNext());
    }
}
