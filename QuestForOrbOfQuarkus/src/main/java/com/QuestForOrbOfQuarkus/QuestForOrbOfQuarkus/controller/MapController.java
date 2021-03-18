package com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.controller;


import com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.dto.MapDto;
import com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.dto.PlayerDto;
import com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.service.MapService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;


@RestController
@AllArgsConstructor
@RequestMapping("/game")
public class MapController {

    private final MapService mapService;


    /*

    http://localhost:5556/game/

    JSON body :

    {
    "playerName" : "user"
    }

     */
    @PostMapping
    private MapDto newGame(@RequestBody PlayerDto dto){
        MapDto mapDto = mapService.newGame(dto);
        return mapDto;
    }
    /*
       ukoliko monster postoji monster u dugeonnu

       http://localhost:5556/game/{playerId}/fight  ili http://localhost:5556/game/{playerId}/flee

     */
    @GetMapping("/{id}/fight")
    private MapDto fightMonster(@PathVariable("id") Long id){
        MapDto mapDto = mapService.fightMonster(id);
        return mapDto;
    }
    @GetMapping("/{id}/flee")
    private MapDto playerFlee(@PathVariable("id") Long id){
        MapDto mapDto = mapService.playerFlee(id);
        return mapDto;
    }

    @GetMapping("/{id}/move")
    private MapDto playerMove(@PathVariable("id") Long id){
        MapDto mapDto = mapService.goToNextLevel(id,false);
        return mapDto;
    }
        /*
       ukoliko monster postoji item u dugeonnu

       http://localhost:5556/game/{playerId}/item

     */


    @GetMapping("/{id}/item")
    private MapDto playerItem(@PathVariable("id") Long id){
        MapDto mapDto = mapService.getItem(id);
        return mapDto;
    }

    /*
 ukoliko monster postoji item HealPotion u dugeonnu

    http://localhost:5556/game/{playerId}/heal

*/
    @GetMapping("/{id}/heal")
    private MapDto playerHeal(@PathVariable("id") Long id){
        MapDto mapDto = mapService.healPlayer(id);
        return mapDto;
    }



}
