package pl.zajavka.project_manager.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.zajavka.project_manager.api.dto.ProjectDTO;
import pl.zajavka.project_manager.domian.Project;

@Mapper(componentModel = "spring")
public interface ProjectDTOMapper {

    ProjectDTO map(Project project);
}
