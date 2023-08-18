package pl.zajavka.project_manager.api.controller.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.zajavka.project_manager.api.dto.AvailableProjectsDTO;
import pl.zajavka.project_manager.api.dto.ProjectDTO;
import pl.zajavka.project_manager.api.dto.ProjectDetailsDTO;
import pl.zajavka.project_manager.api.dto.mapper.ProjectDTOMapper;
import pl.zajavka.project_manager.api.dto.mapper.ProjectDetailsDTOMapper;
import pl.zajavka.project_manager.business.ProjectsService;
import pl.zajavka.project_manager.domian.Project;

@RestController
@AllArgsConstructor
@RequestMapping(ProjectController.API_PROJECTS)
public class ProjectController {
    public static final String API_PROJECTS = "/projects";
    public static final String ALL_PROJECTS = "/all";
    public static final String PROJECT_BY_USER = "/user";
    public static final String PROJECT_DETAILS = "/{projectId}";
    public static final String ADD_PROJECT = "/add";
    public static final String UPDATE_PROJECT = "/update";
    public static final String DELETE_PROJECT = "/delete/{projectId}";

    private final ProjectsService projectsService;
    private final ProjectDTOMapper projectDTOMapper;
    private final ProjectDetailsDTOMapper projectDetailsDTOMapper;

    @GetMapping(value = ALL_PROJECTS)
    public AvailableProjectsDTO allProjects() {
        return AvailableProjectsDTO.builder()
                .availableProjects(projectsService.findAllProjects().stream()
                        .map(projectDTOMapper::map)
                        .toList())
                .build();
    }

    @GetMapping(value = PROJECT_BY_USER)
    public AvailableProjectsDTO projectsByUser(@RequestParam Integer userId) {
        return AvailableProjectsDTO.builder()
                .availableProjects(projectsService.findProjectsByUser(userId).stream()
                        .map(projectDTOMapper::map)
                        .toList())
                .build();
    }

    @GetMapping(value = PROJECT_DETAILS)
    public ProjectDetailsDTO projectDetails(@PathVariable Integer projectId){
        return projectDetailsDTOMapper.map(projectsService.findProjectDetails(projectId));
    }

    @PostMapping(value = ADD_PROJECT)
    public ProjectDTO addProject(@RequestBody ProjectDTO projectDTO) {
        // Map the DTO to a Project object and save it
        Project project = projectDTOMapper.map(projectDTO);
        return projectDTOMapper.map(projectsService.saveProject(project));
    }

    @PutMapping(value = UPDATE_PROJECT)
    public ProjectDTO updateProject(@RequestBody ProjectDTO projectDTO) {
        // Map the DTO to a Project object and update it
        Project project = projectDTOMapper.map(projectDTO);
        return projectDTOMapper.map(projectsService.updateProject(project));
    }

    @DeleteMapping(value = DELETE_PROJECT)
    public ResponseEntity<String> deleteProject(@PathVariable Integer projectId) {
        projectsService.deleteProject(projectId);
        return ResponseEntity.ok("Project deleted successfully");
    }
}
