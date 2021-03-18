package com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DungeonDto {
    private Integer id;
    private Integer monsterId;

    private List<ItemDto> items = new ArrayList<>();
}
