package example.test.springbootmultidb.contacts.service;

import example.test.springbootmultidb.contacts.exception.ProjectCreationException;
import example.test.springbootmultidb.contacts.exception.ProjectNotFoundException;
import example.test.springbootmultidb.contacts.model.ProjectDTO;

import java.util.List;

public interface ProjectService {

    ProjectDTO getProjectById(long id, boolean includeEmployees) throws ProjectNotFoundException;

    ProjectDTO getProjectByName(String name);

    ProjectDTO addProject(ProjectDTO model) throws ProjectCreationException;

    ProjectDTO addEmployeeToProject(long id, List<Long> employeeIds);

    void deleteProject(long id);

    ProjectDTO deleteEmployeeFromProject(long id, List<Long> employeeIds) throws ProjectNotFoundException;
}
