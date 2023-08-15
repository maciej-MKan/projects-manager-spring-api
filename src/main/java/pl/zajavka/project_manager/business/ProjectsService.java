package pl.zajavka.project_manager.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.project_manager.business.dao.ProjectsDAO;
import pl.zajavka.project_manager.domian.Project;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ProjectsService {

    private final ProjectsDAO projectsDAO;

    @Transactional
    public List<Project> findAllProjects(){
        List<Project> allProjects = projectsDAO.findAllProjects();
        log.info("Found and return [{}] projects", allProjects.size());
        return allProjects;
    }
}
