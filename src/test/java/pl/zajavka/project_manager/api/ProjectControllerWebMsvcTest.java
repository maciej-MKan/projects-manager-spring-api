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
import pl.zajavka.project_manager.api.controller.rest.ProjectController;
import pl.zajavka.project_manager.api.dto.CommentDTO;
import pl.zajavka.project_manager.api.dto.ProjectDTO;
import pl.zajavka.project_manager.api.dto.ProjectDetailsDTO;
import pl.zajavka.project_manager.api.dto.mapper.ProjectDTOMapper;
import pl.zajavka.project_manager.api.dto.mapper.ProjectDetailsDTOMapper;
import pl.zajavka.project_manager.business.ProjectsService;
import pl.zajavka.project_manager.domian.Project;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProjectController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectControllerWebMsvcTest extends AbstractJwt {

    private MockMvc mockMvc;
    
    private ObjectMapper objectMapper;

    @MockBean
    private ProjectsService projectsService;
    
    @MockBean
    private ProjectDTOMapper projectDTOMapper;
    
    @MockBean
    private ProjectDetailsDTOMapper projectDetailsDTOMapper;

    private static ProjectDTO someProjectDTO() {
        return ProjectDTO.builder()
                .projectId(123)
                .name("Test Project")
                .startDate(123456789)
                .endDate(1234567890)
                .author(null)
                .build();
    }

    @Test
    void allProjects() throws Exception {
        //given
        ProjectDTO projectDTO = someProjectDTO();
        List<Project> projects = List.of(Project.builder().build());

        when(projectsService.findAllProjects()).thenReturn(projects);
        when(projectDTOMapper.map(any(Project.class))).thenReturn(projectDTO);

        //when, then
        MvcResult result = mockMvc.perform(get(ProjectController.API_PROJECTS + ProjectController.ALL_PROJECTS)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.availableProjects[0].projectId").value(projectDTO.getProjectId()))
                .andReturn();

        assertThat(result.getResponse().getContentAsString())
                .contains("availableProjects")
                .contains(String.valueOf(projectDTO.getProjectId()));
    }

    @Test
    void projectsByUser() throws Exception {
        //given
        ProjectDTO projectDTO = someProjectDTO();
        List<Project> projects = List.of(Project.builder().build());

        when(projectsService.findProjectsByUser(any(Integer.class))).thenReturn(projects);
        when(projectDTOMapper.map(any(Project.class))).thenReturn(projectDTO);

        //when, then
        MvcResult result = mockMvc.perform(get(ProjectController.API_PROJECTS + ProjectController.PROJECT_BY_USER)
                .param("userId", "123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.availableProjects[0].projectId").value(projectDTO.getProjectId()))
                .andReturn();

        assertThat(result.getResponse().getContentAsString())
                .contains("availableProjects")
                .contains(String.valueOf(projectDTO.getProjectId()));
    }

    @Test
    void projectDetails() throws Exception {
        //given
        ProjectDetailsDTO projectDetailsDTO = ProjectDetailsDTO.builder()
                .projectId(123)
                .description("description")
                .comments(List.of(CommentDTO.builder().build()))
                .build();
        Project project = Project.builder().build();

        when(projectsService.findProjectDetails(any(Integer.class))).thenReturn(project);
        when(projectDetailsDTOMapper.map(any(Project.class))).thenReturn(projectDetailsDTO);

        //when, then
        MvcResult result = mockMvc.perform(get(ProjectController.API_PROJECTS + ProjectController.PROJECT_DETAILS, 123)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.projectId").value(projectDetailsDTO.getProjectId()))
                .andExpect(jsonPath("$.description").value(projectDetailsDTO.getDescription()))
                .andExpect(jsonPath("$.comments").exists())
                .andReturn();

        assertThat(result.getResponse().getContentAsString())
                .contains("projectId")
                .contains(String.valueOf(projectDetailsDTO.getProjectId()));
    }

    @Test
    void addProjectSuccess() throws Exception {
        //given
        ProjectDTO projectDTO = someProjectDTO();
        String requestBody = objectMapper.writeValueAsString(projectDTO.withProjectId(null));
        String responseBody = objectMapper.writeValueAsString(projectDTO);

        when(projectsService.saveProject(any())).thenReturn(Project.builder().build());
        when(projectDTOMapper.map(any(Project.class))).thenReturn(someProjectDTO());

        //when, then
        MvcResult result = mockMvc.perform(post(ProjectController.API_PROJECTS + ProjectController.ADD_PROJECT)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.projectId").value(projectDTO.getProjectId()))
                .andReturn();

        assertThat(result.getResponse().getContentAsString())
                .contains("projectId")
                .contains(String.valueOf(projectDTO.getProjectId()));
    }

    @Test
    void updateProjectSuccess() throws Exception {
        //given
        ProjectDTO projectDTO = someProjectDTO();
        String requestBody = objectMapper.writeValueAsString(projectDTO);
        String responseBody = objectMapper.writeValueAsString(projectDTO);

        when(projectsService.updateProject(any())).thenReturn(Project.builder().build());
        when(projectDTOMapper.map(any(Project.class))).thenReturn(someProjectDTO());

        //when, then
        MvcResult result = mockMvc.perform(put(ProjectController.API_PROJECTS + ProjectController.UPDATE_PROJECT)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.projectId").value(projectDTO.getProjectId()))
                .andReturn();

        assertThat(result.getResponse().getContentAsString())
                .contains("projectId")
                .contains(String.valueOf(projectDTO.getProjectId()));
    }

    @Test
    void deleteProjectSuccess() throws Exception {
        //given

        //when, then
        mockMvc.perform(delete(ProjectController.API_PROJECTS + ProjectController.DELETE_PROJECT, 123)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
