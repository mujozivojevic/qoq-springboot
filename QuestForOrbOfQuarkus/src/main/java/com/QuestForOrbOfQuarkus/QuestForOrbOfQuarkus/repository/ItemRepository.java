package com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.repository;

import com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.entity.ItemEntity;
import com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.entity.LevelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {



    @Query("SELECT i FROM ItemEntity i inner join i.dungeons d where d.id = :dungeonId")
    ItemEntity findItemByDungeonId(Long dungeonId);
}
