package pl.zajavka.project_manager.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.zajavka.project_manager.api.dto.UserDetailsDTO;
import pl.zajavka.project_manager.domian.User;

@Mapper(componentModel = "spring")
public interface UserDetailsDTOMapper {

    @Mapping(target = "password", ignore = true)
    UserDetailsDTO map(User user);

    @Mappings({@Mapping(target = "userId", ignore = true), @Mapping(target = "projects", ignore = true)})
    User map(UserDetailsDTO user);
}
