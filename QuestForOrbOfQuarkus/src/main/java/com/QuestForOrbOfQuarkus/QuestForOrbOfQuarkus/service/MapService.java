package com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.service;

import com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.dto.MapDto;
import com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.dto.PlayerDto;

public interface MapService {

    MapDto newGame(PlayerDto playerDto);
    MapDto fightMonster(Long id);
    MapDto goToNextLevel(Long id, Boolean isFlee);
    MapDto playerFlee(Long id);
    MapDto getItem(Long id);
    MapDto healPlayer(Long id);

}
