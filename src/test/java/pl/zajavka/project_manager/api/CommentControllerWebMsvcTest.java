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
import pl.zajavka.project_manager.api.controller.rest.CommentController;
import pl.zajavka.project_manager.api.dto.CommentDTO;
import pl.zajavka.project_manager.api.dto.mapper.CommentDTOMapper;
import pl.zajavka.project_manager.business.CommentService;
import pl.zajavka.project_manager.domian.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CommentController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CommentControllerWebMsvcTest extends AbstractJwt{

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @MockBean
    private CommentService commentService;
    @MockBean
    private CommentDTOMapper commentDTOMapper;

    private static CommentDTO someCommentDTO() {
        return CommentDTO.builder()
                .commentId(123)
                .author(null)
                .project(null)
                .comment("some comment")
                .date(123456789)
                .build();
    }

    @Test
    void addCommentSuccess() throws Exception {

        //given
        CommentDTO commentDTO = someCommentDTO();
        String requestBody = objectMapper.writeValueAsString(commentDTO.withCommentId(null));
        String responseBody = objectMapper.writeValueAsString(commentDTO);

        when(commentService.saveComment(any())).thenReturn(Comment.builder().build());
        when(commentDTOMapper.map(any(Comment.class))).thenReturn(someCommentDTO());

        // when, then
        MvcResult result = mockMvc.perform(post(
                        CommentController.API_COMMENTS + CommentController.ADD_COMMENT
                )
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.commentId", is(someCommentDTO().getCommentId())))
                .andReturn();

        assertThat(result.getResponse().getContentAsString())
                .isEqualTo(responseBody);
    }

    @Test
    void getCommentsListCorrectly() throws Exception {

        //given
        Integer projectId = 1;
        Comment comment = Comment.builder().build();
        CommentDTO commentDTO = someCommentDTO();
        List<Comment> commentsList = List.of(
                comment.withCommentId(1),
                comment.withCommentId(2),
                comment.withCommentId(3),
                comment.withCommentId(5));
        List<CommentDTO> commentsDTOList = List.of(
                commentDTO.withCommentId(1),
                commentDTO.withCommentId(2),
                commentDTO.withCommentId(3),
                commentDTO.withCommentId(5)
        );

        String responseBody = objectMapper.writeValueAsString(commentsDTOList);

        when(commentService.findCommentsByProject(projectId)).thenReturn(commentsList);
        when(commentDTOMapper.map(any(Comment.class))).thenReturn(
                commentsDTOList.get(0),
                commentsDTOList.get(1),
                commentsDTOList.get(2),
                commentsDTOList.get(3)
        );

        // when, then
        MvcResult result = mockMvc.perform(get(
                        CommentController.API_COMMENTS + CommentController.COMMENTS_FOR_PROJECT, projectId
                )
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(result.getResponse().getContentAsString())
                .isEqualTo(responseBody);
    }
}
