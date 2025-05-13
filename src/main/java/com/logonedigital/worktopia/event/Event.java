package com.logonedigital.worktopia.event;

import com.logonedigital.worktopia.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event extends BaseEntity {

    private String title;
    private LocalDateTime date;

    @Column(columnDefinition = "text")
    private String description;

}