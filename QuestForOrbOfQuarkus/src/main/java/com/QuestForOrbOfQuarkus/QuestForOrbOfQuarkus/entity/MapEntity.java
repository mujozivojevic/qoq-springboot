package com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "MAP")
public class MapEntity {
    @SequenceGenerator(
            name = "mapSeq",
            sequenceName = "MAP_SEQ",
            allocationSize = 1
    )

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mapSeq")
    @Column(name ="ID", nullable = false)
    private Long id;

    private String message;
    private boolean isThere;
    private boolean isWin;


    @ManyToOne
    private PlayerEntity player;

    @ManyToOne
    private LevelEntity level;

    @ManyToOne
    private DungeonEntity dungeon;

}
