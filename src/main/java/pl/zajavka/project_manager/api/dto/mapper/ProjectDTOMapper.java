package pl.zajavka.project_manager.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.project_manager.api.dto.ProjectDTO;
import pl.zajavka.project_manager.domian.Project;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectDTOMapper {

    ProjectDTO map(Project project);

    Project map(ProjectDTO project);
}
