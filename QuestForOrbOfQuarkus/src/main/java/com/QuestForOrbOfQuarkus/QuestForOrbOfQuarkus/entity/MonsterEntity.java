package com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "MONSTER")
public class MonsterEntity {
    @SequenceGenerator(
            name="monsterSeq",
            sequenceName = "MONSTER_SEQ",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "monsterSeq")
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;
    private String name;
    private Integer health;
    private Integer damage;


    @OneToMany(mappedBy = "monster", fetch = FetchType.LAZY)
    private List<DungeonEntity> dungeons = new ArrayList<>();
}
