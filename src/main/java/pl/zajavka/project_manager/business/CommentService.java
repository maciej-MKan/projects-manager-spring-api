package pl.zajavka.project_manager.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.zajavka.project_manager.api.dto.CommentDTO;
import pl.zajavka.project_manager.business.dao.CommentsDAO;
import pl.zajavka.project_manager.domian.Comment;
import pl.zajavka.project_manager.domian.Project;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CommentService {

    private final CommentsDAO commentsDAO;

    public List<Comment> findCommentsByProject(Integer projectId){
        Project project = Project.builder()
                .projectId(projectId)
                .build();
        List<Comment> comments = commentsDAO.findCommentsByProject(project);
        log.info("Found [{}] comments for project [{}]", comments.size(), project.getProjectId());
        return comments;
    }

    public Comment saveComment(Comment comment){
        Comment newComment = commentsDAO.saveComment(comment);
        log.info("Created new comment [{}] for project [{}]", comment, comment.getProject().getProjectId());
        return newComment;
    }
}
