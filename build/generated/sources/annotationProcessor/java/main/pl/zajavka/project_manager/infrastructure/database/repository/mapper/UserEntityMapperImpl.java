package pl.zajavka.project_manager.infrastructure.database.repository.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.zajavka.project_manager.domian.User;
import pl.zajavka.project_manager.infrastructure.database.entity.ProjectEntity;
import pl.zajavka.project_manager.infrastructure.database.entity.UserEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-21T22:06:31+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 17.0.8 (Private Build)"
)
@Component
public class UserEntityMapperImpl implements UserEntityMapper {

    @Override
    public User mapFromEntity(UserEntity value) {
        if ( value == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.userId( value.getUserId() );
        user.name( value.getName() );
        user.surname( value.getSurname() );
        user.password( value.getPassword() );
        user.superUser( value.getSuperUser() );
        user.age( value.getAge() );
        user.gender( value.getGender() );
        user.email( value.getEmail() );
        user.phone( value.getPhone() );
        List<ProjectEntity> list = value.getProjects();
        if ( list != null ) {
            user.projects( new ArrayList<ProjectEntity>( list ) );
        }

        return user.build();
    }

    @Override
    public UserEntity mapToEntity(User user) {
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
}
