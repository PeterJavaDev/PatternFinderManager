package org.patternfinder.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
public class SearchTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pattern;
    private String inputText;

    @Enumerated(EnumType.STRING)
    private SearchTaskStatus status;

    private Integer progress;
    private Integer position;
    private Integer typos;
    private String worker;
    private OffsetDateTime createdTs;

    @PrePersist
    public void prePersist() {
        createdTs = OffsetDateTime.now();
    }

}
