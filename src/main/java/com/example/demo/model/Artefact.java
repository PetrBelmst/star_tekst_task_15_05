package com.example.demo.model;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    @Column(name = "ID", updatable = false, nullable = false)
    @Type(type = "uuid-char")
    private UUID ID;

    @Column(name = "Creation_Time", updatable = false, nullable = false)
    private LocalDateTime Created;

    @Column(name = "User_ID", updatable = false, nullable = false)
    private String UserID;

    private String Category;
    private String Description;

}





















