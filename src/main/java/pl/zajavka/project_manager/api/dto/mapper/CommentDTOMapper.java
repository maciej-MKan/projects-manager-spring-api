package pl.zajavka.project_manager.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.project_manager.api.dto.CommentDTO;
import pl.zajavka.project_manager.domian.Comment;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentDTOMapper {

    CommentDTO map(Comment comment);

    Comment map(CommentDTO comment);
}
