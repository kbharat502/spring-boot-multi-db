package example.test.springbootmultidb.contacts.service.web.mappers;

import example.test.springbootmultidb.contacts.db.entities.Project;
import example.test.springbootmultidb.contacts.model.ProjectDTO;

public interface ProjectMapper {

    Project modelToEntity(ProjectDTO model);

    ProjectDTO entityToModel(Project entity);

    ProjectDTO entityToModelIgnoreEmployee(Project entity);
}
