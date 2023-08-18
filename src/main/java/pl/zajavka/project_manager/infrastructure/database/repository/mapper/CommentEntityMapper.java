package pl.zajavka.project_manager.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.project_manager.domian.Comment;
import pl.zajavka.project_manager.infrastructure.database.entity.CommentEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentEntityMapper {

    Comment mapFromEntity(CommentEntity comment);

    CommentEntity mapToEntity(Comment comment);
}

