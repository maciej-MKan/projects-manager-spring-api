package pl.zajavka.project_manager.api.controller.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.zajavka.project_manager.api.dto.UserDTO;
import pl.zajavka.project_manager.api.dto.UserDetailsDTO;
import pl.zajavka.project_manager.api.dto.mapper.UserDTOMapper;
import pl.zajavka.project_manager.api.dto.mapper.UserDetailsDTOMapper;
import pl.zajavka.project_manager.business.UsersService;
import pl.zajavka.project_manager.domian.User;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(UserController.API_USERS)
@Validated
public class UserController {
    public static final String API_USERS = "/users";
    public static final String USER_DETAILS  = "/user/{userId}";
    public static final String USER= "/{userId}";
    public static final String ADD_USER = "/add";
    public static final String UPDATE_USER = "/update";
    public static final String DELETE_USER = "/delete/{userId}";

    private final UsersService userService;
    private final UserDTOMapper userDTOMapper;
    private final UserDetailsDTOMapper userDetailsDTOMapper;

    @GetMapping(value = {USER, USER + "/"})
    public UserDTO getUser(@PathVariable Integer userId) {
        return userDTOMapper.map(userService.findUserById(userId));
    }

    @GetMapping(value = {USER_DETAILS, USER_DETAILS + "/"})
    public UserDetailsDTO userDetails(@PathVariable Integer userId) {
        return userDetailsDTOMapper.map(userService.findUserById(userId));
    }

    @PostMapping(value = {ADD_USER, ADD_USER + "/"})
    public UserDTO addUser(@RequestBody @Valid UserDetailsDTO userDTO) {
        log.info("Handled request [{}]", userDTO);
        User user = userDetailsDTOMapper.map(userDTO);
        return userDTOMapper.map(userService.saveUser(user));
    }

    @PutMapping(value = {UPDATE_USER, UPDATE_USER + "/"})
    public UserDTO updateUser(@RequestBody UserDTO userDTO) {
        User user = userDTOMapper.map(userDTO);
        return userDTOMapper.map(userService.updateUser(user));
    }

    @DeleteMapping(value = {DELETE_USER, DELETE_USER + "/"})
    public ResponseEntity<String> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }
}