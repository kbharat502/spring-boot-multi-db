package example.test.springbootmultidb.contacts.service.impl;

import example.test.springbootmultidb.contacts.db.entities.Employee;
import example.test.springbootmultidb.contacts.db.entities.Project;
import example.test.springbootmultidb.contacts.db.repo.EmployeeRepository;
import example.test.springbootmultidb.contacts.db.repo.ProjectRepository;
import example.test.springbootmultidb.contacts.exception.ProjectCreationException;
import example.test.springbootmultidb.contacts.exception.ProjectNotFoundException;
import example.test.springbootmultidb.contacts.model.ProjectDTO;
import example.test.springbootmultidb.contacts.service.ProjectService;
import example.test.springbootmultidb.contacts.service.web.mappers.EmployeeMapper;
import example.test.springbootmultidb.contacts.service.web.mappers.ProjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    public ProjectMapper projectMapper;

    public ProjectRepository projectRepository;

    public EmployeeMapper employeeMapper;

    public EmployeeRepository employeeRepository;

    public ProjectServiceImpl(ProjectMapper projectMapper,
                              ProjectRepository projectRepository,
                              EmployeeMapper employeeMapper,
                              EmployeeRepository employeeRepository) {
        this.projectMapper = projectMapper;
        this.projectRepository = projectRepository;
        this.employeeMapper = employeeMapper;
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ProjectDTO getProjectById(long id, boolean includeEmployees) throws ProjectNotFoundException {

        ProjectDTO projectDTO = null;
                Optional<Project> projectOptional = projectRepository.findById(id);
        if(projectOptional.isPresent()) {
            if(includeEmployees) {
                projectDTO = projectMapper.entityToModel(projectOptional.get());
            } else {
                projectDTO = projectMapper.entityToModelIgnoreEmployee(projectOptional.get());
            }
        } else {
            throw new ProjectNotFoundException("No project with ID: " + id + " was found");
        }

        return projectDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ProjectDTO getProjectByName(String name) {
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ProjectDTO addProject(ProjectDTO model) throws ProjectCreationException {
        ProjectDTO projectDTO = null;
        Project project = projectMapper.modelToEntity(model);

        try {
            project.setCreatedDate(LocalDateTime.now());
            project.setUpdatedDate(LocalDateTime.now());
            if(StringUtils.isBlank(model.getUpdatedBy())) {
                project.setUpdatedBy(model.getCreatedBy());
            }

            Project savedProject = projectRepository.save(project);

            projectDTO = projectMapper.entityToModel(savedProject);
        } catch (DataAccessException dae) {
            throw new ProjectCreationException(String.join("","Problem creating the project with name '", model.getName(), "'."));
        }

        return projectDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ProjectDTO addEmployeeToProject(long id, List<Long> employeeIds) {

        ProjectDTO projectDTO = null;
        Optional<Project> projectOptional = projectRepository.findById(id);

        if(projectOptional.isPresent()) {
            Project project = projectOptional.get();
            List<Employee> employees = employeeRepository.findAllById(employeeIds);

            employees.forEach(employee -> {
                employee.addProject(project);
                project.addEmployee(employee);
            });
            /*for (Employee employee : employees) {
                employee.addProject(project);
                project.addEmployee(employee);
            }*/

            Project savedProject = projectRepository.save(project);

            projectDTO = projectMapper.entityToModel(savedProject);
        }

        return projectDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteProject(long id) {
        projectRepository.deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ProjectDTO deleteEmployeeFromProject(long id, List<Long> employeeIds) throws ProjectNotFoundException {
        ProjectDTO projectDTO = null;
        Optional<Project> projectOptional = projectRepository.findById(id);

        if(projectOptional.isPresent()) {
            final Project project = projectOptional.get();
            List<Employee> employees = employeeRepository.findAllById(employeeIds);

            employees.forEach(employee -> {
                employee.removeProject(project);
                project.removeEmployee(employee);
            });
            /*for (Employee employee : employees) {
                employee.addProject(project);
                project.addEmployee(employee);
            }*/

            Project savedProject = projectRepository.save(project);

            projectDTO = projectMapper.entityToModel(savedProject);
        } else {
            throw new ProjectNotFoundException("Couldn't find the project with ID: " + id);
        }

        return projectDTO;
    }
}
