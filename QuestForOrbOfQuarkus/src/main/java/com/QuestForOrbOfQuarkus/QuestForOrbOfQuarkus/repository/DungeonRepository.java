package com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.repository;

import com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.entity.DungeonEntity;
import com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.entity.LevelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface DungeonRepository extends JpaRepository<DungeonEntity, Long> {

    @Modifying
    @Query(value = "insert into dungeon_item (dungeon_id, item_id) values (:dungeon_id, :item_id)", nativeQuery = true)
    void insertDungeon(@Param("dungeon_id") Long dungeon_id, @Param("item_id") Long item_id);

    @Query("SELECT d FROM DungeonEntity d inner join d.maps m where m.id = :mapId")
    DungeonEntity findDungeonByMapId(Long mapId);


}
