package com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.service;

import com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.dto.ItemDto;
import com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.dto.MapDto;
import com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.dto.MonsterDto;
import com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.dto.PlayerDto;
import com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.entity.*;
import com.QuestForOrbOfQuarkus.QuestForOrbOfQuarkus.repository.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MapServiceImpl implements MapService {

    private PlayerRepository playerRepository;
    private LevelRepository levelRepository;
    private MapRepository mapRepository;
    private MonsterRepository monsterRepository;

    private ItemRepository itemRepository;
    private DungeonRepository dungeonRepository;


    @Override
    public MapDto newGame(PlayerDto playerDto) {
        PlayerEntity newPlayer = new PlayerEntity();
        newPlayer.setPlayerName(playerDto.getPlayerName());
        newPlayer.setDamage(10);
        newPlayer.setHealth(100);

        newPlayer.setLevelDamage(10);
        newPlayer.setLevelHealth(100);

        newPlayer.setDeath(false);

        playerRepository.save(newPlayer);
        GenerateDungeon(newPlayer);

        ModelMapper modelMapper = new ModelMapper();
        MapEntity firstMap = mapRepository.findMapByPlayerId(newPlayer.getId());
        firstMap.setMessage("This is your first level, if there are monsters you can fight or flee. You can only take items if you beat a monster. If there are no monsters, click the button to go to the next level. Good luck to you!");
        firstMap.setPlayer(newPlayer);
        PlayerDto newPlayerDto = modelMapper.map(newPlayer, PlayerDto.class);

        MapDto mapDto = modelMapper.map(firstMap, MapDto.class);
         mapDto = findMonster(mapDto, modelMapper);
         mapDto = findItem(mapDto,modelMapper);

        mapDto.setPlayerDto(newPlayerDto);

        return mapDto;
    }

    private MapDto findItem(MapDto mapDto, ModelMapper modelMapper) {
        ItemEntity item = itemRepository.findItemByDungeonId(Long.valueOf(mapDto.getDungeonId()));
        if(item != null){
            ItemDto itemDto = modelMapper.map(item,ItemDto.class);
            mapDto.setItemDto(itemDto);
        }
        return mapDto;
    }

    private MapDto findMonster(MapDto mapDto, ModelMapper modelMapper) {
        DungeonEntity dungeon = dungeonRepository.findDungeonByMapId(Long.valueOf(mapDto.getId()));
        MonsterEntity monster = dungeon.getMonster();
        if(monster != null) {
            MonsterDto monsterDto = modelMapper.map(monster,MonsterDto.class);
            mapDto.setMonsterDto(monsterDto);
        }
        return mapDto;
    }

    private void GenerateDungeon(PlayerEntity newPlayer) {
        Integer level = 1;

        while (level <= levelRepository.count()) {
            MapEntity newMap = new MapEntity();
            Optional<LevelEntity> dbLevel = levelRepository.findById(Long.valueOf(level));
            newMap.setLevel(dbLevel.get());
            newMap.setPlayer(newPlayer);

            DungeonEntity newDungeon = GenerateMonster(level);

            newMap.setDungeon(newDungeon);
            if (level == 1) {
                newMap.setThere(true);
            } else {
                newMap.setThere(false);
            }

            newMap.setWin(false);
            dungeonRepository.save(newDungeon);
            mapRepository.save(newMap);

            GenerateItem(newDungeon);
            level++;

        }
    }

    private DungeonEntity GenerateMonster(Integer level) {
        DungeonEntity dungeon = new DungeonEntity();
        Integer random;
        if (level == 1) {
            random = GenerateRandomNumber(1, 3);
            if (random == 3) {
                return dungeon;
            }
            Optional<MonsterEntity> monster = monsterRepository.findById(Long.valueOf(random));
            dungeon.setMonster(monster.get());
            return dungeon;
        } else if (level == 2) {
            random = GenerateRandomNumber(1, 3);
            if (random == 3) {
                return dungeon;
            }
            Optional<MonsterEntity> monster = monsterRepository.findById(Long.valueOf(random));
            dungeon.setMonster(monster.get());
            return dungeon;
        } else if (level == 3) {
            random = GenerateRandomNumber(3, 5);
            if (random == 5) {
                return dungeon;
            }
            Optional<MonsterEntity> monster = monsterRepository.findById(Long.valueOf(random));
            dungeon.setMonster(monster.get());
            return dungeon;
        } else if (level == 4) {
            random = GenerateRandomNumber(3, 5);
            if (random == 5) {
                return dungeon;
            }
            Optional<MonsterEntity> monster = monsterRepository.findById(Long.valueOf(random));
            dungeon.setMonster(monster.get());
            return dungeon;
        } else if (level == 5) {
            random = GenerateRandomNumber(5, 7);
            if (random == 7) {
                return dungeon;
            }
            Optional<MonsterEntity> monster = monsterRepository.findById(Long.valueOf(random));
            dungeon.setMonster(monster.get());
            return dungeon;
        } else if (level == 6) {
            random = GenerateRandomNumber(5, 7);
            if (random == 7) {
                return dungeon;
            }
            Optional<MonsterEntity> monster = monsterRepository.findById(Long.valueOf(random));
            dungeon.setMonster(monster.get());
            return dungeon;
        } else if (level == 7) {
            random = GenerateRandomNumber(7, 9);
            if (random == 9) {
                return dungeon;
            }
            Optional<MonsterEntity> monster = monsterRepository.findById(Long.valueOf(random));
            dungeon.setMonster(monster.get());
            return dungeon;
        } else if (level == 8) {
            random = GenerateRandomNumber(7, 9);
            if (random == 9) {
                return dungeon;
            }
            Optional<MonsterEntity> monster = monsterRepository.findById(Long.valueOf(random));
            dungeon.setMonster(monster.get());
            return dungeon;
        } else if (level == 9) {
            random = GenerateRandomNumber(9, 11);
            if (random == 11) {
                return dungeon;
            }
            Optional<MonsterEntity> monster = monsterRepository.findById(Long.valueOf(random));
            dungeon.setMonster(monster.get());
            return dungeon;
        }else if (level == 10) {
            Optional<MonsterEntity> monster = monsterRepository.findById(Long.valueOf(11));
            dungeon.setMonster(monster.get());
            return dungeon;
        }

        else {

            return dungeon;
        }
    }

        private void GenerateItem (DungeonEntity newDungeon){
            MapEntity dungeonMap = mapRepository.findDungeonMap(newDungeon.getId());
            LevelEntity dungeonLevel = levelRepository.findMapLevel(dungeonMap.getId());
            Integer random;
            Long level = dungeonLevel.getId();


            if(level == 1 || level == 2 || level == 3){
                random = GenerateRandomNumber(1,4);
                if(random == 4){
                    return;
                }
                Optional<ItemEntity> item = itemRepository.findById(Long.valueOf(random));
               dungeonRepository.insertDungeon(newDungeon.getId(), item.get().getId());
            }
            else if(level == 4 || level == 5 || level == 6){
                random = GenerateRandomNumber(4,7);
                if(random == 7){
                    return;
                }
                Optional<ItemEntity> item = itemRepository.findById(Long.valueOf(random));
                dungeonRepository.insertDungeon(newDungeon.getId(), item.get().getId());

            }
            else if(level == 7 || level == 8 || level == 9){
                random = GenerateRandomNumber(7,10);
                if(random == 10){
                    return;
                }
                Optional<ItemEntity> item = itemRepository.findById(Long.valueOf(random));
                dungeonRepository.insertDungeon(newDungeon.getId(), item.get().getId());
            } else if(level == 10){
                Optional<ItemEntity> item = itemRepository.findById(Long.valueOf(10));
                dungeonRepository.insertDungeon(newDungeon.getId(), item.get().getId());
            }

        }

        public Integer GenerateRandomNumber (Integer min, Integer max){
            Integer random_int = (int) (Math.random() * (max - min + 1) + min);
            return random_int;
        }

    public MapDto playerFlee(Long id) {
        MapEntity currentMap = mapRepository.findMapByPlayerId(id);
        if(currentMap.getId() == levelRepository.findAll().stream().count())
            return null;
        if(currentMap.getDungeon().getMonster() == null)
            return null;
        Optional<PlayerEntity> currentPlayer = playerRepository.findById(id);

        List<ItemEntity> playerItems = playerRepository.findItemByPlayerId(id);

        for(ItemEntity item : playerItems){
            playerRepository.RemoveItem(id, item.getId());
        }

        Integer playerHealth = currentPlayer.get().getHealth();
        Integer newHealth = (playerHealth - (playerHealth * 35)/100);
        if(newHealth <= 0 )
        {
            currentPlayer.get().setHealth(0);
            currentPlayer.get().setDeath(true);
        }
        currentPlayer.get().setHealth(newHealth);
        MapDto mapDto = goToNextLevel(id,true);

        return mapDto;
    }


    public MapDto goToNextLevel(Long id, Boolean isFlee) {
        Optional<PlayerEntity> currentPlayer = playerRepository.findById(id);
        if (currentPlayer.get().isDeath() == true)
            return null;


        MapEntity currentMap = mapRepository.findMapByPlayerId(id);

        MonsterEntity currentMonster = currentMap.getDungeon().getMonster();
        List<ItemEntity> item = currentMap.getDungeon().getItems();
        ModelMapper modelMapper = new ModelMapper();
        MapDto mapDto;
        if (currentMonster != null && isFlee == false) {
            mapDto = modelMapper.map(currentMap, MapDto.class);
            mapDto.setPlayerDto(modelMapper.map(currentPlayer.get(), PlayerDto.class));
            mapDto.setMonsterDto(modelMapper.map(currentMonster, MonsterDto.class));
            return mapDto;
        }


        currentMap.setThere(false);
        Optional<LevelEntity> nextLevel = levelRepository.findById(currentMap.getLevel().getId() + 1);
        currentPlayer = Optional.of(GenerateLevelProperties(nextLevel.get().getId(), currentPlayer.get()));
        PlayerDto playerDto = modelMapper.map(currentPlayer.get(), PlayerDto.class);
        MapEntity nextMap = mapRepository.nextMapLevel(currentPlayer.get().getId(), nextLevel.get().getId());
        nextMap.setThere(true);

        DungeonEntity nextDungeon = dungeonRepository.findDungeonByMapId(nextMap.getId());
        MonsterEntity nextMonster = monsterRepository.findMonsterByDungeonId(nextDungeon.getId());
        List<ItemEntity> items = nextDungeon.getItems();
        List<ItemDto> itemsDto = new ArrayList<>();
        for (ItemEntity itemFor : items) {
            itemsDto.add(modelMapper.map(itemFor, ItemDto.class));
        }


        if (nextMonster != null) {
            nextMap.setMessage("Looks like we have monster in here. FIGTH or FLEE?");
        } else {
            nextMap.setMessage("You're lucky, there are no monster inside this dungeon");
        }
        if (!itemsDto.isEmpty() && itemsDto != null) {
            if (itemsDto.get(0).getType().equals("Final")) {
                nextMap.setMessage("You have reached the finals, watch out for this monster...");
            }
        }
        mapRepository.save(nextMap);
        mapDto = modelMapper.map(nextMap, MapDto.class);
        if (!itemsDto.isEmpty() && itemsDto != null) {
            mapDto.setItemDto(itemsDto.get(0));
        }
        if (nextMonster != null) {
            mapDto.setMonsterDto(modelMapper.map(nextMonster, MonsterDto.class));
        }

        mapDto.setPlayerDto(playerDto);

        return mapDto;
    }


    private PlayerEntity GenerateLevelProperties(Long level, PlayerEntity currentPlayer) {
        Integer increaseHealth = 20;
        Integer increaseDamage = 5;

        currentPlayer.setLevelHealth(currentPlayer.getLevelHealth() + increaseHealth);
        currentPlayer.setLevelDamage(currentPlayer.getLevelDamage() + increaseDamage);
        currentPlayer.setDamage(currentPlayer.getLevelDamage());


        Integer playerHealth = currentPlayer.getHealth();
        Integer levelHealth = currentPlayer.getLevelHealth();
        Integer addHealth = playerHealth + increaseHealth;

        playerHealth = addHealth >= levelHealth ? levelHealth : addHealth;
        currentPlayer.setHealth(playerHealth);

        return currentPlayer;
    }

    public MapDto fightMonster(Long id){
            Optional<PlayerEntity> currentPlayer = playerRepository.findById(id);
            if(currentPlayer.get().isDeath())
                return null;

            MapEntity currentMap = mapRepository.findMapByPlayerId(id);
            if(currentMap.getDungeon().getMonster() == null)
                return null;

            Integer playerHealth = currentPlayer.get().getHealth();
            Integer playerDamage = currentPlayer.get().getDamage();
            List<ItemEntity> playerItems = playerRepository.findItemByPlayerId(id);

            MonsterEntity currentMonster = currentMap.getDungeon().getMonster();
            Integer monsterHealth = currentMonster.getHealth();
            Integer monsterDamage = currentMonster.getDamage();


            while(playerHealth> 0 && monsterHealth > 0){
                double random = GenerateRandomNumber(1,6)/5;
                monsterHealth = monsterHealth - (int)(playerDamage * random);

                random = GenerateRandomNumber(1,6)/5;
                playerHealth = playerHealth - (int)(monsterDamage * random);
            }


            ModelMapper modelMapper = new ModelMapper();
            MapDto mapDto;

            if(monsterHealth <= 0)
            {
                if(currentMap.getDungeon().getMonster().getId() == 11){
                    currentMap.setMessage("Congratulations, you killed all the monsters, now get the final item to finish this game.");
                }else { currentMap.setMessage("Woow, you killed a monster!"); }
                currentMap.getDungeon().setMonster(null);

            }
            if(playerHealth <= 0){
                currentPlayer.get().setHealth(0);
                currentPlayer.get().setDeath(true);
                currentMap.setMessage("Unlucky... you're dead!!");



            }
            currentPlayer.get().setHealth(playerHealth);
            playerRepository.save(currentPlayer.get());

            PlayerDto playerDto  = modelMapper.map(currentPlayer.get(),PlayerDto.class);

            ItemEntity item = itemRepository.findItemByDungeonId(currentMap.getDungeon().getId());
            ItemDto itemDto = null;
            if(item != null) {
                itemDto = modelMapper.map(item, ItemDto.class);
            }

            mapDto = modelMapper.map(currentMap,MapDto.class);
            mapDto.setPlayerDto(playerDto);
            mapDto.setItemDto(itemDto);
            return mapDto;
        }
    public MapDto getItem(Long id) {
        Optional<PlayerEntity> currentPlayer = playerRepository.findById(id);
        MapEntity currentMap = mapRepository.findMapByPlayerId(id);
        if(currentMap.getDungeon().getItems().isEmpty() || currentMap.getDungeon().getItems() == null)
            return null;
        if(currentMap.getDungeon().getMonster() != null){
            return null;
        }
        DungeonEntity currentDungeon = dungeonRepository.findDungeonByMapId(currentMap.getId());

        List<ItemEntity> playerItems = playerRepository.findItemByPlayerId(id);
        ItemEntity dungeonItem = itemRepository.findItemByDungeonId(currentDungeon.getId());

        for(ItemEntity item : playerItems){
            if(dungeonItem.getType().equals(item.getType())){
                playerRepository.RemoveItem(currentPlayer.get().getId(), item.getId());

                if(item.getType().equals("DamageUp")){
                    Integer increaseBy = item.getIncreaseBy();
                    Integer playerDamage = currentPlayer.get().getDamage();
                    playerDamage = playerDamage - increaseBy;
                    currentPlayer.get().setDamage(playerDamage);
                    currentPlayer.get().setLevelDamage(playerDamage);
                }
                if(item.getType().equals("StrengthUp")){
                    Integer increaseBy = item.getIncreaseBy();
                    Integer playerHealth = currentPlayer.get().getHealth();
                    playerHealth = playerHealth - increaseBy;
                    currentPlayer.get().setHealth(playerHealth);
                    currentPlayer.get().setLevelHealth(currentPlayer.get().getLevelHealth() - increaseBy);
                }
            }

        }
        playerRepository.InsertPlayerItem(currentPlayer.get().getId(), dungeonItem.getId());
        if(dungeonItem.getType().equals("DamageUp")){
            Integer increaseBy = dungeonItem.getIncreaseBy();
            Integer playerDamage = currentPlayer.get().getDamage();
            playerDamage = playerDamage + increaseBy;
            currentPlayer.get().setDamage(playerDamage);
            currentPlayer.get().setLevelDamage(playerDamage);
            currentMap.setMessage("You have improved your attack a little...");

        }
        if(dungeonItem.getType().equals("StrengthUp")){
            Integer increaseBy = dungeonItem.getIncreaseBy();
            Integer playerHealth = currentPlayer.get().getHealth();
            playerHealth = playerHealth + increaseBy;
            currentPlayer.get().setHealth(playerHealth);
            currentPlayer.get().setLevelHealth(currentPlayer.get().getLevelHealth() + increaseBy);
            currentMap.setMessage("You have improved your strength a little...");
        }
        if (dungeonItem.getType().equals("HealPotion")) {
            currentMap.setMessage("Now that you have a healed potion, you can use it by clicking the Heal button");
        }
        if (dungeonItem.getType().equals("Final")) {
            currentMap.setMessage("Now that you have this item, you have won the game !!");
            currentMap.setWin(true);
        }
        currentMap.getDungeon().setItems(null);
        mapRepository.save(currentMap);
        playerRepository.save(currentPlayer.get());
        ModelMapper modelMapper = new ModelMapper();

        PlayerDto playerDto = modelMapper.map(currentPlayer.get(), PlayerDto.class);
        MapDto mapDto = modelMapper.map(currentMap, MapDto.class);
        mapDto.setItemDto(null);
        mapDto.setPlayerDto(playerDto);

        return mapDto;
    }
    public MapDto healPlayer(Long id) {
        Optional<PlayerEntity> currentPlayer = playerRepository.findById(Long.valueOf(id));
        MapEntity currentMap = mapRepository.findMapByPlayerId(Long.valueOf(id));
        DungeonEntity currentDungeon = dungeonRepository.findDungeonByMapId(currentMap.getId());

        List<ItemEntity> playerItems = playerRepository.findItemByPlayerId(id);

        for (ItemEntity item : playerItems) {
            if (item.getType().equals("HealPotion")) {

                Integer playerHealth = currentPlayer.get().getHealth();
                Integer levelHealth = currentPlayer.get().getLevelHealth();
                Integer addHealth = playerHealth + item.getIncreaseBy();
                playerHealth = addHealth >= levelHealth ? levelHealth : addHealth;
                currentPlayer.get().setHealth(playerHealth);
                playerRepository.RemoveItem(currentPlayer.get().getId(), item.getId());
                currentMap.setMessage("You have improved your health a little ...");
            }
        }
        playerRepository.save(currentPlayer.get());
        ModelMapper modelMapper = new ModelMapper();
        MapDto mapDto = modelMapper.map(currentMap,MapDto.class);
        mapDto.setPlayerDto(modelMapper.map(currentPlayer.get(), PlayerDto.class));
        MonsterEntity monster = monsterRepository.findMonsterByDungeonId(currentDungeon.getId());
        ItemEntity item = itemRepository.findItemByDungeonId(currentDungeon.getId());
        ItemDto itemDto = null;
        if(item != null){
            itemDto = modelMapper.map(item,ItemDto.class);
        }
        MonsterDto monsterDto =null;
        if(monster != null){
            monsterDto = modelMapper.map(monster,MonsterDto.class);
        }
        mapDto.setItemDto(itemDto);
        mapDto.setMonsterDto(monsterDto);

        return mapDto;
    }
}

