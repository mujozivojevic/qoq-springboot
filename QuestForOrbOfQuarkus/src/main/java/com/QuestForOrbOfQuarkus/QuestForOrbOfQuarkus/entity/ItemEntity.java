package com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "ITEM")
public class ItemEntity {
    @SequenceGenerator(
            name="itemSeq",
            sequenceName = "ITEM_SEQ",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemSeq")
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    private String name;
    private String type;

    @Column(name="increaseby")
    private Integer increaseBy;

    @ManyToMany(mappedBy = "items")
    private List<PlayerEntity> players = new ArrayList<>();


    @ManyToMany(mappedBy = "items")
    private List<DungeonEntity> dungeons = new ArrayList<>();
}
