package com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "PLAYER")
public class PlayerEntity {
    @SequenceGenerator(
            name="playerSeq",
            sequenceName = "PLAYER_SEQ",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "playerSeq")
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    private Integer damage;
    private Integer health;
    private String playerName;
    private Integer levelDamage;
    private Integer levelHealth;
    private boolean isDeath;

    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY)
    private List<MapEntity> maps = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "PLAYER_ITEM",
            joinColumns = @JoinColumn(name = "PLAYER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID")
    )
    private List<ItemEntity> items = new ArrayList<>();

}
