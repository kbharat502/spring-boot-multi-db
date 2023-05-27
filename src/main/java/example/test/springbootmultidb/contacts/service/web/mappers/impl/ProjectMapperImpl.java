package example.test.springbootmultidb.contacts.service.web.mappers.impl;

import example.test.springbootmultidb.contacts.db.entities.Project;
import example.test.springbootmultidb.contacts.model.ProjectDTO;
import example.test.springbootmultidb.contacts.service.web.mappers.EmployeeMapper;
import example.test.springbootmultidb.contacts.service.web.mappers.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapperImpl implements ProjectMapper {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Project modelToEntity(ProjectDTO model) {
        if ( model == null ) {
            return null;
        }

        Project.ProjectBuilder project = Project.builder();

        project.prjId( model.getId() );
        project.name( model.getName() );
        project.expectedStartDate( model.getExpectedStartDate() );
        project.actualStartDate( model.getActualStartDate() );
        project.expectedFinishDate( model.getExpectedFinishDate() );
        project.actualFinishDate( model.getActualFinishDate() );
        project.createdBy( model.getCreatedBy() );
        project.updatedBy( model.getUpdatedBy() );

        return project.build();
    }

    @Override
    public ProjectDTO entityToModel(Project entity) {
        if ( entity == null ) {
            return null;
        }

        ProjectDTO.ProjectDTOBuilder projectDTO = ProjectDTO.builder();

        projectDTO.id( entity.getPrjId() );
        projectDTO.name( entity.getName() );
        projectDTO.expectedStartDate( entity.getExpectedStartDate() );
        projectDTO.actualStartDate( entity.getActualStartDate() );
        projectDTO.expectedFinishDate( entity.getExpectedFinishDate() );
        projectDTO.actualFinishDate( entity.getActualFinishDate() );
        projectDTO.createdBy( entity.getCreatedBy() );
        projectDTO.updatedBy( entity.getUpdatedBy() );
        projectDTO.employees( employeeMapper.entitiesToModels( entity.getEmployees() ) );

        return projectDTO.build();
    }

    @Override
    public ProjectDTO entityToModelIgnoreEmployee(Project entity) {
        if ( entity == null ) {
            return null;
        }

        ProjectDTO.ProjectDTOBuilder projectDTO = ProjectDTO.builder();

        projectDTO.id( entity.getPrjId() );
        projectDTO.name( entity.getName() );
        projectDTO.expectedStartDate( entity.getExpectedStartDate() );
        projectDTO.actualStartDate( entity.getActualStartDate() );
        projectDTO.expectedFinishDate( entity.getExpectedFinishDate() );
        projectDTO.actualFinishDate( entity.getActualFinishDate() );
        projectDTO.createdBy( entity.getCreatedBy() );
        projectDTO.updatedBy( entity.getUpdatedBy() );

        return projectDTO.build();
    }
}
