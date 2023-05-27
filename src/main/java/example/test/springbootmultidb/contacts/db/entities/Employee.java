package example.test.springbootmultidb.contacts.db.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * The persistent class for the employee database table.
 * 
 */
@Entity
@Table(name="employee")
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_generator")
	@SequenceGenerator(name="employee_generator", sequenceName = "employee_seq", allocationSize=1)
	@Column(name="emp_id", unique=true, nullable=false, precision=131089)
	private Long empId;

	@Column(name="created_by", nullable=false, length=10)
	private String createdBy;

	@Column(name="created_date", nullable=false)
	private LocalDateTime createdDate;

	@Column(name="date_of_birth")
	private LocalDate dateOfBirth;

	@Column(name="first_name", nullable=false, length=50)
	private String firstName;

	@Column(name="join_date", nullable=false)
	private LocalDate joinDate;

	@Column(name="last_name", nullable=false, length=50)
	private String lastName;

	@Column(name="middle_initial", length=1)
	private String middleInitial;

	@Column(length=5)
	private String suffix;

	@Column(name="updated_by", nullable=false, length=10)
	private String updatedBy;

	@Column(name="updated_date", nullable=false)
	private LocalDateTime updatedDate;

	//bi-directional many-to-one association to Department
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="dept_id", nullable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@LazyGroup("department")
	private Department department;

	//bi-directional one-to-one association to Contact
	@OneToOne(mappedBy="employee", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@LazyGroup("contact")
	private Contact contact;

	@ManyToMany(targetEntity = Project.class, mappedBy = "employees",
			cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	@LazyCollection(LazyCollectionOption.TRUE)
	@LazyGroup("projects")
	private List<Project> projects;

	public Employee() {
	}

	public Long getEmpId() {
		return this.empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDate getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public LocalDate getJoinDate() {
		return this.joinDate;
	}

	public void setJoinDate(LocalDate joinDate) {
		this.joinDate = joinDate;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleInitial() {
		return this.middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getSuffix() {
		return this.suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Contact getContact() {
		return this.contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public void addProject(Project project) {
		if(this.projects == null) {
			this.projects = new ArrayList<>();
		}

		this.projects.add(project);
	}

	public void removeProject(Project project) {
		if(this.projects != null) {
			Iterator<Project> prjIt = this.projects.listIterator();
			do {
				if(project.getPrjId().equals(prjIt.next().getPrjId())) {
					prjIt.remove();
				}
			} while(prjIt.hasNext());
		}
	}

}