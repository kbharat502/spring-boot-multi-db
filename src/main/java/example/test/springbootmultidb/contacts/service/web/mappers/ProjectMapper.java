package example.test.springbootmultidb.contacts.service.web.mappers;

import example.test.springbootmultidb.contacts.db.entities.Project;
import example.test.springbootmultidb.contacts.model.ProjectDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {EmployeeMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "prjId"),
            @Mapping(target = "employees", ignore = true)
    })
    Project modelToEntity(ProjectDTO model);


    @Mapping(source = "prjId", target = "id")
    ProjectDTO entityToModel(Project entity);


    @Mapping(source = "prjId", target = "id")
    @Mapping(target = "employees", ignore = true)
    ProjectDTO entityToModelIgnoreEmployee(Project entity);
}
