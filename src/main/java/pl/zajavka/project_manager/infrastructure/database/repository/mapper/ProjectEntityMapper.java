package pl.zajavka.project_manager.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.project_manager.domian.Project;
import pl.zajavka.project_manager.infrastructure.database.entity.ProjectEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectEntityMapper {

    Project mapFromEntity(ProjectEntity entity);
}
