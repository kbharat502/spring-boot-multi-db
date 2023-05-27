package example.test.springbootmultidb.contacts.controller;

import example.test.springbootmultidb.contacts.exception.ProjectCreationException;
import example.test.springbootmultidb.contacts.exception.ProjectNotFoundException;
import example.test.springbootmultidb.contacts.model.ProjectDTO;
import example.test.springbootmultidb.contacts.service.ProjectService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@Validated
@RestController
@RequestMapping("/projects")
public class ProjectController extends AbstractController{

    public ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(path = "/{prjId}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable("prjId") Long projectId) throws ProjectNotFoundException {

        ProjectDTO projectDTO  = projectService.getProjectById(projectId, false);

        return new ResponseEntity<>(projectDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/{prjId}/includeEmployees")
    public ResponseEntity<ProjectDTO> getProjectByIdWithEmployees(@PathVariable("prjId") Long projectId) throws ProjectNotFoundException {

        ProjectDTO projectDTO  = projectService.getProjectById(projectId, true);

        return new ResponseEntity<>(projectDTO, HttpStatus.OK);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addNewProject(@Valid @RequestBody ProjectDTO projectDTO, HttpServletRequest request) throws ProjectCreationException {
        ProjectDTO returnedProjectDTO = projectService.addProject(projectDTO);

        HttpHeaders headers = new HttpHeaders();

        buildLocationHeader(request, headers,
                String.join("", "/api/projects/", String.valueOf(projectDTO.getId())));

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{prjId}/addEmployees/{empIds}")
    public ResponseEntity<ProjectDTO> addEmployeesToProject(
            @NotNull @PathVariable("prjId") Long projectId, @NotNull @PathVariable("empIds") Long[] employeeIds) {

        ProjectDTO projectDTO = projectService.addEmployeeToProject(projectId, Arrays.asList(employeeIds));
        return new ResponseEntity<>(projectDTO, HttpStatus.OK);
    }

    @PutMapping("/{prjId}/removeEmployees/{empIds}")
    public ResponseEntity<ProjectDTO> removeEmployeesToProject(
            @NotNull @PathVariable("prjId") Long projectId, @NotNull @PathVariable("empIds") Long[] employeeIds) throws ProjectNotFoundException {

        ProjectDTO projectDTO = projectService.deleteEmployeeFromProject(projectId, Arrays.asList(employeeIds));
        return new ResponseEntity<>(projectDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{prjId}")
    public void deleteProject(@NotNull @PathVariable("prjId") Long projectId) {
        projectService.deleteProject(projectId);
    }
}
