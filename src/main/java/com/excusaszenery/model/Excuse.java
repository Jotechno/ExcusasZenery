package com.excusaszenery.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "excuses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Excuse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false, length = 500)
    private String excuse;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @PrePersist
    public void prePersist() {
        this.creationDate = new Date(); // java.util.Date, no LocalDateTime
    }

    @Column(nullable = true, unique = false)
    private Integer likes = 0;

    @Column(nullable = false)
    private Boolean isPublic = true;
}