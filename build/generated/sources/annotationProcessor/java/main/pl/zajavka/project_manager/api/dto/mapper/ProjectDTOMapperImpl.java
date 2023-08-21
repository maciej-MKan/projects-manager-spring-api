package pl.zajavka.project_manager.api.dto.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.zajavka.project_manager.api.dto.ProjectDTO;
import pl.zajavka.project_manager.api.dto.UserDTO;
import pl.zajavka.project_manager.domian.Project;
import pl.zajavka.project_manager.domian.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-21T22:06:32+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 17.0.8 (Private Build)"
)
@Component
public class ProjectDTOMapperImpl implements ProjectDTOMapper {

    @Override
    public ProjectDTO map(Project project) {
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

    @Override
    public Project map(ProjectDTO project) {
        if ( project == null ) {
            return null;
        }

        Project.ProjectBuilder project1 = Project.builder();

        project1.projectId( project.getProjectId() );
        project1.name( project.getName() );
        project1.startDate( project.getStartDate() );
        project1.endDate( project.getEndDate() );
        project1.author( userDTOToUser( project.getAuthor() ) );

        return project1.build();
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
}
