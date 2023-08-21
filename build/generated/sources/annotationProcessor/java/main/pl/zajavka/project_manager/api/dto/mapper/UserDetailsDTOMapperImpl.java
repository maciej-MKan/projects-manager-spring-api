package pl.zajavka.project_manager.api.dto.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.zajavka.project_manager.api.dto.UserDetailsDTO;
import pl.zajavka.project_manager.domian.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-21T22:06:32+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 17.0.8 (Private Build)"
)
@Component
public class UserDetailsDTOMapperImpl implements UserDetailsDTOMapper {

    @Override
    public UserDetailsDTO map(User user) {
        if ( user == null ) {
            return null;
        }

        UserDetailsDTO.UserDetailsDTOBuilder userDetailsDTO = UserDetailsDTO.builder();

        userDetailsDTO.userId( user.getUserId() );
        userDetailsDTO.name( user.getName() );
        userDetailsDTO.surname( user.getSurname() );
        userDetailsDTO.password( user.getPassword() );
        userDetailsDTO.age( user.getAge() );
        userDetailsDTO.gender( user.getGender() );
        userDetailsDTO.email( user.getEmail() );
        userDetailsDTO.phone( user.getPhone() );
        userDetailsDTO.superUser( user.getSuperUser() );

        return userDetailsDTO.build();
    }

    @Override
    public User map(UserDetailsDTO user) {
        if ( user == null ) {
            return null;
        }

        User.UserBuilder user1 = User.builder();

        user1.name( user.getName() );
        user1.surname( user.getSurname() );
        user1.password( user.getPassword() );
        user1.superUser( user.getSuperUser() );
        user1.age( user.getAge() );
        user1.gender( user.getGender() );
        user1.email( user.getEmail() );
        user1.phone( user.getPhone() );

        return user1.build();
    }
}
