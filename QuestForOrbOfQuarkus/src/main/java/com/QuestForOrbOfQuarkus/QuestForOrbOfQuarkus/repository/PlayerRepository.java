package com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.repository;

import com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.entity.ItemEntity;
import com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {

    @Query("SELECT distinct i FROM ItemEntity i inner join i.players p WHERE p.id = :id")
    List<ItemEntity> findItemByPlayerId(Long id);


    @Modifying
    @Query(value = "DELETE FROM player_item pi where pi.player_id = :player_id and pi.item_id = :item_id ", nativeQuery = true)
    void RemoveItem(@Param("player_id") Long player_id, @Param("item_id") Long item_id);


    @Modifying
    @Query(value = "insert into player_item (player_id, item_id) values (:player_id, :item_id)", nativeQuery = true)
    void InsertPlayerItem(@Param("player_id") Long player_id, @Param("item_id") Long item_id);


    /*
        @Modifying
    @Query(value = "insert into dungeon_item (dungeon_id, item_id) values (:dungeon_id, :item_id)", nativeQuery = true)
    void insertDungeon(@Param("dungeon_id") Long dungeon_id, @Param("item_id") Long item_id);
     */
}
