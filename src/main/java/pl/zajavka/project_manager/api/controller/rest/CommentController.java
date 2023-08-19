package pl.zajavka.project_manager.api.controller.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.zajavka.project_manager.api.dto.CommentDTO;
import pl.zajavka.project_manager.api.dto.mapper.CommentDTOMapper;
import pl.zajavka.project_manager.business.CommentService;
import pl.zajavka.project_manager.domian.Comment;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(CommentController.API_COMMENTS)
public class CommentController {
    public static final String API_COMMENTS = "/comments";
    public static final String COMMENTS_FOR_PROJECT = "/project/{projectId}";
    public static final String ADD_COMMENT = "/add";

    private final CommentService commentService;
    private final CommentDTOMapper commentDTOMapper;

    @GetMapping(value = COMMENTS_FOR_PROJECT)
    public List<CommentDTO> commentsForProject(@PathVariable Integer projectId) {
        log.info("Handled projectId [{}]", projectId);
        List<CommentDTO> commentDTOList = commentService.findCommentsByProject(projectId).stream()
                .map(commentDTOMapper::map)
                .toList();
        log.info("List of comments to return [{}]", commentDTOList);
        return commentDTOList;
    }

    @PostMapping(value = ADD_COMMENT)
    public CommentDTO addComment(@RequestBody CommentDTO commentDTO) {
        log.info("Handled param [{}]", commentDTO);
        Comment comment = commentDTOMapper.map(commentDTO);
        CommentDTO returnCommentDTO = commentDTOMapper.map(commentService.saveComment(comment));
        log.info("Comment to return [{}]", returnCommentDTO);
        return returnCommentDTO;
    }
}