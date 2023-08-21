package pl.zajavka.project_manager.api.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.zajavka.project_manager.api.dto.ProjectDetailsDTO;
import pl.zajavka.project_manager.api.dto.UserDTO;
import pl.zajavka.project_manager.domian.Project;
import pl.zajavka.project_manager.domian.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-21T22:06:32+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 17.0.8 (Private Build)"
)
@Component
public class ProjectDetailsDTOMapperImpl implements ProjectDetailsDTOMapper {

    @Override
    public ProjectDetailsDTO map(Project project) {
        if ( project == null ) {
            return null;
        }

        ProjectDetailsDTO.ProjectDetailsDTOBuilder projectDetailsDTO = ProjectDetailsDTO.builder();

        projectDetailsDTO.projectId( project.getProjectId() );
        projectDetailsDTO.name( project.getName() );
        projectDetailsDTO.description( project.getDescription() );
        projectDetailsDTO.startDate( project.getStartDate() );
        projectDetailsDTO.endDate( project.getEndDate() );
        projectDetailsDTO.author( userToUserDTO( project.getAuthor() ) );
        projectDetailsDTO.users( userListToUserDTOList( project.getUsers() ) );

        return projectDetailsDTO.build();
    }

    @Override
    public Project map(ProjectDetailsDTO project) {
        if ( project == null ) {
            return null;
        }

        Project.ProjectBuilder project1 = Project.builder();

        project1.projectId( project.getProjectId() );
        project1.name( project.getName() );
        project1.description( project.getDescription() );
        project1.startDate( project.getStartDate() );
        project1.endDate( project.getEndDate() );
        project1.author( userDTOToUser( project.getAuthor() ) );
        project1.users( userDTOListToUserList( project.getUsers() ) );

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

    protected List<UserDTO> userListToUserDTOList(List<User> list) {
        if ( list == null ) {
            return null;
        }

        List<UserDTO> list1 = new ArrayList<UserDTO>( list.size() );
        for ( User user : list ) {
            list1.add( userToUserDTO( user ) );
        }

        return list1;
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

    protected List<User> userDTOListToUserList(List<UserDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<User> list1 = new ArrayList<User>( list.size() );
        for ( UserDTO userDTO : list ) {
            list1.add( userDTOToUser( userDTO ) );
        }

        return list1;
    }
}
