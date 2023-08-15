package pl.zajavka.project_manager.api.controller.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.zajavka.project_manager.api.dto.AvailableProjectsDTO;
import pl.zajavka.project_manager.api.dto.mapper.ProjectDTOMapper;
import pl.zajavka.project_manager.business.ProjectsService;

@RestController
@AllArgsConstructor
@RequestMapping(ProjectController.API_PROJECTS)
public class ProjectController {
    public static final String API_PROJECTS = "/projects";
    public static final String ALL_PROJECTS = "/all";

    private final ProjectsService projectsService;
    private final ProjectDTOMapper projectDTOMapper;

    @GetMapping(value = ALL_PROJECTS)
    public AvailableProjectsDTO allProjects() {
        return AvailableProjectsDTO.builder()
                .availableProjects(projectsService.findAllProjects().stream()
                        .map(projectDTOMapper::map)
                        .toList())
                .build();
    }
}
