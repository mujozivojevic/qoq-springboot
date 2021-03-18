package com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.dto;

import lombok.Data;

@Data
public class MapDto {
    private Long id;
    private Long levelId;
    private Long playerId;
    private Long dungeonId;
    private PlayerDto playerDto;
    private MonsterDto monsterDto;
    private ItemDto itemDto;
    private boolean isThere;
    private boolean isWIn;
    private String message;
}
