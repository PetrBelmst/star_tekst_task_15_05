package com.example.demo.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "COMMENT")
public class Comment implements EntityObject {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID", nullable = false, insertable = false, updatable = false)
    @Type(type = "uuid-char")
    private UUID ID;

    @Column(name = "ARTEFACT_ID", nullable = false, insertable = false, updatable = false)
    private UUID ArtefactID;

    @Column(name = "User_ID", nullable = false)
    private String UserID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ARTEFACT_ID")
    private Artefact artefact;

    private String Content;

    @Column(name = "Was_Updated", nullable = false)
    private boolean isOriginal = false;

}
