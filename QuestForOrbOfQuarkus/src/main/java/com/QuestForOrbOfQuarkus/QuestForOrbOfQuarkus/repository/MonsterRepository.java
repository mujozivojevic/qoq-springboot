package com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.repository;

import com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.entity.MapEntity;
import com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.entity.MonsterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MonsterRepository extends JpaRepository<MonsterEntity, Long> {




    @Query("SELECT m FROM MonsterEntity m inner join m.dungeons d where d.id = :dungeonId")
    MonsterEntity findMonsterByDungeonId(Long dungeonId);

}
