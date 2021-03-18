package com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.repository;

import com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.entity.LevelEntity;
import com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.entity.MapEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LevelRepository extends JpaRepository<LevelEntity, Long> {

    @Query("SELECT l FROM LevelEntity l inner join l.maps m where m.id = :mapId")
    LevelEntity findMapLevel(Long mapId);
}
