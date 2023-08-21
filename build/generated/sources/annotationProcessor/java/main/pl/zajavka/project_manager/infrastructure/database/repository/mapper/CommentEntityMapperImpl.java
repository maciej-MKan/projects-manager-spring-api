package pl.zajavka.project_manager.infrastructure.database.repository.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.zajavka.project_manager.domian.Comment;
import pl.zajavka.project_manager.domian.Project;
import pl.zajavka.project_manager.domian.User;
import pl.zajavka.project_manager.infrastructure.database.entity.CommentEntity;
import pl.zajavka.project_manager.infrastructure.database.entity.ProjectEntity;
import pl.zajavka.project_manager.infrastructure.database.entity.UserEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-21T22:06:32+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 17.0.8 (Private Build)"
)
@Component
public class CommentEntityMapperImpl implements CommentEntityMapper {

    @Override
    public Comment mapFromEntity(CommentEntity comment) {
        if ( comment == null ) {
            return null;
        }

        Comment.CommentBuilder comment1 = Comment.builder();

        comment1.commentId( comment.getCommentId() );
        comment1.comment( comment.getComment() );
        comment1.date( comment.getDate() );
        comment1.author( userEntityToUser( comment.getAuthor() ) );
        comment1.project( projectEntityToProject( comment.getProject() ) );

        return comment1.build();
    }

    @Override
    public CommentEntity mapToEntity(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentEntity.CommentEntityBuilder commentEntity = CommentEntity.builder();

        commentEntity.commentId( comment.getCommentId() );
        commentEntity.author( userToUserEntity( comment.getAuthor() ) );
        commentEntity.project( projectToProjectEntity( comment.getProject() ) );
        commentEntity.comment( comment.getComment() );
        commentEntity.date( comment.getDate() );

        return commentEntity.build();
    }

    protected User userEntityToUser(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.userId( userEntity.getUserId() );
        user.name( userEntity.getName() );
        user.surname( userEntity.getSurname() );
        user.password( userEntity.getPassword() );
        user.superUser( userEntity.getSuperUser() );
        user.age( userEntity.getAge() );
        user.gender( userEntity.getGender() );
        user.email( userEntity.getEmail() );
        user.phone( userEntity.getPhone() );
        List<ProjectEntity> list = userEntity.getProjects();
        if ( list != null ) {
            user.projects( new ArrayList<ProjectEntity>( list ) );
        }

        return user.build();
    }

    protected List<User> userEntityListToUserList(List<UserEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<User> list1 = new ArrayList<User>( list.size() );
        for ( UserEntity userEntity : list ) {
            list1.add( userEntityToUser( userEntity ) );
        }

        return list1;
    }

    protected Project projectEntityToProject(ProjectEntity projectEntity) {
        if ( projectEntity == null ) {
            return null;
        }

        Project.ProjectBuilder project = Project.builder();

        project.projectId( projectEntity.getProjectId() );
        project.name( projectEntity.getName() );
        project.description( projectEntity.getDescription() );
        project.startDate( projectEntity.getStartDate() );
        project.endDate( projectEntity.getEndDate() );
        project.author( userEntityToUser( projectEntity.getAuthor() ) );
        project.users( userEntityListToUserList( projectEntity.getUsers() ) );

        return project.build();
    }

    protected UserEntity userToUserEntity(User user) {
        if ( user == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.userId( user.getUserId() );
        userEntity.name( user.getName() );
        userEntity.surname( user.getSurname() );
        userEntity.superUser( user.getSuperUser() );
        userEntity.password( user.getPassword() );
        userEntity.age( user.getAge() );
        userEntity.gender( user.getGender() );
        userEntity.email( user.getEmail() );
        userEntity.phone( user.getPhone() );
        List<ProjectEntity> list = user.getProjects();
        if ( list != null ) {
            userEntity.projects( new ArrayList<ProjectEntity>( list ) );
        }

        return userEntity.build();
    }

    protected List<UserEntity> userListToUserEntityList(List<User> list) {
        if ( list == null ) {
            return null;
        }

        List<UserEntity> list1 = new ArrayList<UserEntity>( list.size() );
        for ( User user : list ) {
            list1.add( userToUserEntity( user ) );
        }

        return list1;
    }

    protected ProjectEntity projectToProjectEntity(Project project) {
        if ( project == null ) {
            return null;
        }

        ProjectEntity.ProjectEntityBuilder projectEntity = ProjectEntity.builder();

        projectEntity.projectId( project.getProjectId() );
        projectEntity.name( project.getName() );
        projectEntity.description( project.getDescription() );
        projectEntity.startDate( project.getStartDate() );
        projectEntity.endDate( project.getEndDate() );
        projectEntity.author( userToUserEntity( project.getAuthor() ) );
        projectEntity.users( userListToUserEntityList( project.getUsers() ) );

        return projectEntity.build();
    }
}
