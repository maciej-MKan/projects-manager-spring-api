package pl.zajavka.project_manager.api.controller.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.zajavka.project_manager.api.dto.UserDTO;
import pl.zajavka.project_manager.api.dto.UserDetailsDTO;
import pl.zajavka.project_manager.api.dto.mapper.UserDTOMapper;
import pl.zajavka.project_manager.api.dto.mapper.UserDetailsDTOMapper;
import pl.zajavka.project_manager.business.UsersService;
import pl.zajavka.project_manager.domian.User;

@RestController
@AllArgsConstructor
@RequestMapping(UserController.API_USERS)
public class UserController {
    public static final String API_USERS = "/users";
    public static final String USER = "/user/{userId}";
    public static final String USER_DETAILS = "/{userId}";
    public static final String ADD_USER = "/add";
    public static final String UPDATE_USER = "/update";
    public static final String DELETE_USER = "/delete/{userId}";

    private final UsersService userService;
    private final UserDTOMapper userDTOMapper;
    private final UserDetailsDTOMapper userDetailsDTOMapper;

    @GetMapping(value = USER)
    public UserDTO getUser(@PathVariable Integer userId) {
        return userDTOMapper.map(userService.findUserById(userId));
    }

    @GetMapping(value = USER_DETAILS)
    public UserDetailsDTO userDetails(@PathVariable Integer userId) {
        return userDetailsDTOMapper.map(userService.findUserById(userId));
    }

    @PostMapping(value = ADD_USER)
    public UserDTO addUser(@RequestBody UserDTO userDTO) {
        User user = userDTOMapper.map(userDTO);
        return userDTOMapper.map(userService.saveUser(user));
    }

    @PutMapping(value = UPDATE_USER)
    public UserDTO updateUser(@RequestBody UserDTO userDTO) {
        User user = userDTOMapper.map(userDTO);
        return userDTOMapper.map(userService.updateUser(user));
    }

    @DeleteMapping(value = DELETE_USER)
    public ResponseEntity<String> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }
}