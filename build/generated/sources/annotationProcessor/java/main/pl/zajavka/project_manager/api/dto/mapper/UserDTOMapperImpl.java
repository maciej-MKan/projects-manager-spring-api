package pl.zajavka.project_manager.api.dto.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.zajavka.project_manager.api.dto.UserDTO;
import pl.zajavka.project_manager.domian.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-21T22:06:32+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 17.0.8 (Private Build)"
)
@Component
public class UserDTOMapperImpl implements UserDTOMapper {

    @Override
    public UserDTO map(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO.UserDTOBuilder userDTO = UserDTO.builder();

        userDTO.userId( user.getUserId() );
        userDTO.name( user.getName() );
        userDTO.surname( user.getSurname() );

        return userDTO.build();
    }

    @Override
    public User map(UserDTO user) {
        if ( user == null ) {
            return null;
        }

        User.UserBuilder user1 = User.builder();

        user1.userId( user.getUserId() );
        user1.name( user.getName() );
        user1.surname( user.getSurname() );

        return user1.build();
    }
}
