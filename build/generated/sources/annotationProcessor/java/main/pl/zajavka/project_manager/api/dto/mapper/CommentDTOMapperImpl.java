package pl.zajavka.project_manager.api.dto.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.zajavka.project_manager.api.dto.CommentDTO;
import pl.zajavka.project_manager.api.dto.ProjectDTO;
import pl.zajavka.project_manager.api.dto.UserDTO;
import pl.zajavka.project_manager.domian.Comment;
import pl.zajavka.project_manager.domian.Project;
import pl.zajavka.project_manager.domian.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-21T22:06:32+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 17.0.8 (Private Build)"
)
@Component
public class CommentDTOMapperImpl implements CommentDTOMapper {

    @Override
    public CommentDTO map(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentDTO.CommentDTOBuilder commentDTO = CommentDTO.builder();

        commentDTO.commentId( comment.getCommentId() );
        commentDTO.comment( comment.getComment() );
        commentDTO.date( comment.getDate() );
        commentDTO.project( projectToProjectDTO( comment.getProject() ) );
        commentDTO.author( userToUserDTO( comment.getAuthor() ) );

        return commentDTO.build();
    }

    @Override
    public Comment map(CommentDTO comment) {
        if ( comment == null ) {
            return null;
        }

        Comment.CommentBuilder comment1 = Comment.builder();

        comment1.commentId( comment.getCommentId() );
        comment1.comment( comment.getComment() );
        comment1.date( comment.getDate() );
        comment1.author( userDTOToUser( comment.getAuthor() ) );
        comment1.project( projectDTOToProject( comment.getProject() ) );

        return comment1.build();
    }

    protected UserDTO userToUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO.UserDTOBuilder userDTO = UserDTO.builder();

        userDTO.userId( user.getUserId() );
        userDTO.name( user.getName() );
        userDTO.surname( user.getSurname() );

        return userDTO.build();
    }

    protected ProjectDTO projectToProjectDTO(Project project) {
        if ( project == null ) {
            return null;
        }

        ProjectDTO.ProjectDTOBuilder projectDTO = ProjectDTO.builder();

        projectDTO.projectId( project.getProjectId() );
        projectDTO.name( project.getName() );
        projectDTO.startDate( project.getStartDate() );
        projectDTO.endDate( project.getEndDate() );
        projectDTO.author( userToUserDTO( project.getAuthor() ) );

        return projectDTO.build();
    }

    protected User userDTOToUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.userId( userDTO.getUserId() );
        user.name( userDTO.getName() );
        user.surname( userDTO.getSurname() );

        return user.build();
    }

    protected Project projectDTOToProject(ProjectDTO projectDTO) {
        if ( projectDTO == null ) {
            return null;
        }

        Project.ProjectBuilder project = Project.builder();

        project.projectId( projectDTO.getProjectId() );
        project.name( projectDTO.getName() );
        project.startDate( projectDTO.getStartDate() );
        project.endDate( projectDTO.getEndDate() );
        project.author( userDTOToUser( projectDTO.getAuthor() ) );

        return project.build();
    }
}
