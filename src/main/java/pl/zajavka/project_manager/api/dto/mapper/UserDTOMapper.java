package pl.zajavka.project_manager.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.project_manager.api.dto.UserDTO;
import pl.zajavka.project_manager.domian.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDTOMapper {

    UserDTO map(User user);

    User map(UserDTO user);
}
