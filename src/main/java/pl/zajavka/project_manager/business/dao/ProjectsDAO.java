package pl.zajavka.project_manager.business.dao;

import pl.zajavka.project_manager.domian.Project;

import java.util.List;

public interface ProjectsDAO {

    List<Project> findAllProjects();
}
