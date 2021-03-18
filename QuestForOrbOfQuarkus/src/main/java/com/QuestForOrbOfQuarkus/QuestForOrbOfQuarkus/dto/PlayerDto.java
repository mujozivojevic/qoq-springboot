package com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class PlayerDto {
    private Integer id;
    private Integer damage;
    private Integer health;
    private String playerName;
    private Integer levelDamage;
    private Integer levelHealth;
    private boolean isDeath;
    private List<ItemDto> items = new ArrayList<>();

}
