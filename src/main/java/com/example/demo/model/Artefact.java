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
    @Column(name = "ARTEFACT_ID", updatable = false, nullable = false)
    @Type(type = "uuid-char")
    private UUID ID;

    @Column(name = "Creation_Time", updatable = false, nullable = false)
    private LocalDateTime Created;

    @Column(name = "User_ID", updatable = false, nullable = false)
    private String UserID;

    @OneToMany(mappedBy = "ARTEFACT")
    @JoinColumn(name = "ARTEFACT_ID")
    private List<Comment> commentList = new ArrayList<>();

    public void addComment(Comment comment) {
        commentList.add(comment);
        comment.setArtefact(this);
        comment.setArtefactID(this.getID());
    }

    public void removeComment(Comment comment) {
        commentList.remove(comment);
    }

    private String Category;
    private String Description;

}





















