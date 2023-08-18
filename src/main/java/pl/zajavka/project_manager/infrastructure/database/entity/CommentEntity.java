package pl.zajavka.project_manager.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "commentId")
@ToString(of = {"project", "author", "comment"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project_comments")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @Column(name = "comment")
    private String comment;

    @Column(name = "timestamp")
    private Integer date;

}
