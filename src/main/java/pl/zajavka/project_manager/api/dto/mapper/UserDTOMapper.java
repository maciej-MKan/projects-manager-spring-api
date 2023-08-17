package pl.zajavka.project_manager.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.zajavka.project_manager.api.dto.UserDTO;
import pl.zajavka.project_manager.domian.User;

@Mapper(componentModel = "spring")
public interface UserDTOMapper {

    UserDTO map(User user);
}
