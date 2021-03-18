package com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "LEVEL")

public class LevelEntity {
    @SequenceGenerator(
            name="levelSeq",
            sequenceName = "LEVEL_SEQ",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "levelSeq")
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    private Integer level;

    @OneToMany(mappedBy = "level", fetch = FetchType.LAZY)
    private List<MapEntity> maps = new ArrayList<>();
}
