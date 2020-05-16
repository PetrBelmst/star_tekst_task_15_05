package com.example.demo.model;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ARTEFACT")

public class Artefact implements EntityObject {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ARTEFACT_ID", nullable = false)
    @Type(type = "uuid-char")
    private UUID ID;

    @Column(name = "Creation_Time", nullable = false)
    private LocalDateTime Created;

    @Column(name = "User_ID", nullable = false)
    private String UserID;

    @Column(name = "Category", nullable = false)
    private String Category;

    @Column(name = "Description", nullable = false)
    private String Description;

    @Transient
    private List<Comment> commentList = new ArrayList<>();

    public void addComment(Comment comment) {
        commentList.add(comment);
        comment.setArtefact(this);
        comment.setArtefactID(this.getID());
    }

    public void removeComment(Comment comment) {
        commentList.remove(comment);
    }
    @Transient
    private static final String TYPE = "ARTEFACT";

    @Column(name = "Was_Updated", nullable = false)
    private boolean isChanged = false;

}





















