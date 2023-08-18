package pl.zajavka.project_manager.business.dao;

import pl.zajavka.project_manager.domian.Comment;
import pl.zajavka.project_manager.domian.Project;

import java.util.List;

public interface CommentsDAO {

    List<Comment> findCommentsByProject(Project project);

    Comment saveComment(Comment comment);

}
