package com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.repository;

import com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.entity.MapEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface MapRepository extends JpaRepository<MapEntity, Long> {

    @Query("SELECT m FROM MapEntity m inner join m.dungeon d where d.id = ?1")
    MapEntity findDungeonMap(Long dungeonId);

    @Query("SELECT m FROM MapEntity m inner join m.player p WHERE p.id = :playerId AND m.isThere = true")
    MapEntity findMapByPlayerId(Long playerId);



    @Query("SELECT m FROM MapEntity m inner join m.level l inner join m.player p where p.id = :playerId and l.id = :levelId")
    MapEntity nextMapLevel(Long playerId, Long levelId);

}
