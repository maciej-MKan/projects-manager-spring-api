package pl.zajavka.project_manager.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.zajavka.project_manager.api.controller.rest.UserController;
import pl.zajavka.project_manager.api.dto.UserDTO;
import pl.zajavka.project_manager.api.dto.UserDetailsDTO;
import pl.zajavka.project_manager.api.dto.mapper.UserDTOMapper;
import pl.zajavka.project_manager.api.dto.mapper.UserDetailsDTOMapper;
import pl.zajavka.project_manager.business.UsersService;
import pl.zajavka.project_manager.domian.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserControllerTest extends AbstractJwt{

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @MockBean
    private UsersService userService;

    @MockBean
    private UserDTOMapper userDTOMapper;

    @MockBean
    private UserDetailsDTOMapper userDetailsDTOMapper;

    private static UserDTO someUserDTO() {
        return UserDTO.builder()
                .userId(123)
                .name("John")
                .surname("Doe")
                .build();
    }

    private static UserDetailsDTO someUserDetailsDTO() {
        return UserDetailsDTO.builder()
                .userId(123)
                .name("John")
                .surname("Doe")
                .password("password123")
                .age(18)
                .gender("Ms")
                .email("janinback@example.com")
                .phone("987654321")
                .superUser(false)
                .build();
    }

    @Test
    void getUser() throws Exception {
        //given
        UserDTO userDTO = someUserDTO();
        User user = User.builder().build();

        when(userService.findUserById(any(Integer.class))).thenReturn(user);
        when(userDTOMapper.map(any(User.class))).thenReturn(userDTO);

        //when, then
        MvcResult result = mockMvc.perform(get(UserController.API_USERS + UserController.USER, 123)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(userDTO.getUserId()))
                .andReturn();

        assertThat(result.getResponse().getContentAsString())
                .contains("userId")
                .contains(String.valueOf(userDTO.getUserId()));
    }

    @Test
    void userDetails() throws Exception {
        //given
        UserDetailsDTO userDetailsDTO = UserDetailsDTO.builder()
                .userId(123)
                .name("John")
                .surname("Done")
                .age(23)
                .build();
        User user = User.builder().build();

        when(userService.findUserById(any(Integer.class))).thenReturn(user);
        when(userDetailsDTOMapper.map(any(User.class))).thenReturn(userDetailsDTO);

        //when, then
        MvcResult result = mockMvc.perform(get(UserController.API_USERS + UserController.USER_DETAILS, 123)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(userDetailsDTO.getName()))
                .andExpect(jsonPath("$.surname").value(userDetailsDTO.getSurname()))
                .andExpect(jsonPath("$.age").value(userDetailsDTO.getAge()))
                .andReturn();

        assertThat(result.getResponse().getContentAsString())
                .contains("name")
                .contains(userDetailsDTO.getName());
    }

    @Test
    void addUserSuccess() throws Exception {
        //given
        UserDetailsDTO userDTO = someUserDetailsDTO();
        String requestBody = objectMapper.writeValueAsString(userDTO.withUserId(null));

        when(userService.saveUser(any())).thenReturn(User.builder().build());
        when(userDetailsDTOMapper.map(any(User.class))).thenReturn(someUserDetailsDTO());
        when(userDTOMapper.map(any(User.class))).thenReturn(someUserDTO());

        //when, then
        MvcResult result = mockMvc.perform(post(UserController.API_USERS + UserController.ADD_USER)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.userId").value(userDTO.getUserId()))
                .andReturn();

        String contentAsString = result.getResponse().getContentAsString();

        assertThat(contentAsString)
                .contains("userId")
                .contains(String.valueOf(userDTO.getUserId()));
    }

    @Test
    void updateUserSuccess() throws Exception {
        //given
        UserDTO userDTO = someUserDTO();
        String requestBody = objectMapper.writeValueAsString(userDTO);

        when(userService.updateUser(any())).thenReturn(User.builder().build());
        when(userDTOMapper.map(any(User.class))).thenReturn(someUserDTO());

        //when, then
        MvcResult result = mockMvc.perform(put(UserController.API_USERS + UserController.UPDATE_USER)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(userDTO.getUserId()))
                .andReturn();

        assertThat(result.getResponse().getContentAsString())
                .contains("userId")
                .contains(String.valueOf(userDTO.getUserId()));
    }

    @Test
    void deleteUserSuccess() throws Exception {
        //given

        //when, then
        mockMvc.perform(delete(UserController.API_USERS + UserController.DELETE_USER, 123)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
