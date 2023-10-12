package pl.zajavka.project_manager.api.dto.mapper;

import org.mapstruct.*;
import pl.zajavka.project_manager.api.dto.UserDetailsDTO;
import pl.zajavka.project_manager.domian.User;

@Mapper(componentModel = "spring")
public interface UserDetailsDTOMapper {

//    @Mappings({@Mapping(target = "userId", ignore = true), @Mapping(target = "projects", ignore = true)})
    @Mapping(target = "password", ignore = true)
    UserDetailsDTO map(User user);

    User map(UserDetailsDTO user);

}
