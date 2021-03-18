package com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "DUNGEON")
public class DungeonEntity {
    @SequenceGenerator(
            name="dungeonSeq",
            sequenceName = "DUNGEON_SEQ",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dungeonSeq")
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne
    private MonsterEntity monster;

    @OneToMany(mappedBy = "dungeon", fetch = FetchType.LAZY)
    private List<MapEntity> maps = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "DUNGEON_ITEM",
            joinColumns = @JoinColumn(name = "DUNGEON_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID")
    )
    private List<ItemEntity> items = new ArrayList<>();
}
