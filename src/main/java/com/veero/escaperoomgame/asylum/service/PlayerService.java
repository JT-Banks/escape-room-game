package com.veero.escaperoomgame.asylum.service;

import com.veero.escaperoomgame.asylum.model.Action;
import com.veero.escaperoomgame.asylum.model.Item;
import com.veero.escaperoomgame.asylum.model.Room;
import com.veero.escaperoomgame.core.dto.PlayerCreationResponse;
import com.veero.escaperoomgame.core.model.DefaultInventory;
import com.veero.escaperoomgame.core.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class PlayerService {

    @Autowired
    private StarterItemService starterItemService;

    @Autowired
    private RoomService roomService;

    public Player createNewPlayer(Player newPlayerData) {

        Room startingRoom = roomService.getStartingRoom();
        Player newPlayer = new Player();
        newPlayer.setPlayerName(newPlayerData.getPlayerName());
        newPlayer.setPlayerId(UUID.randomUUID().toString());
        newPlayer.setBackground(newPlayerData.getBackground());
        newPlayer.setDifficultyLevel(newPlayerData.getDifficultyLevel());
        newPlayer.setSpecialAbility(newPlayerData.getSpecialAbility());
        newPlayer.setStatus(Player.PlayerStatus.PLAYING);
        newPlayer.setCurrentRoomId(startingRoom.getId());
        newPlayer.setScore(0);
        newPlayer.setTimeRemaining(60.00);

        newPlayer.setInventory(new DefaultInventory());

        Item starterItem = starterItemService.getStarterItem(newPlayerData.getStarterItem());
        newPlayer.setStarterItem(starterItem.getName());
        newPlayer.addItem(starterItem);

        newPlayer.getActions().add(createAction("inspect".toLowerCase()));
        newPlayer.getActions().add(createAction("use".toLowerCase()));
        return newPlayer;
    }

    public PlayerCreationResponse createPlayerResponse(Player newPlayer) {
        return new PlayerCreationResponse(
                newPlayer.getPlayerId(),
                newPlayer.getPlayerName(),
                newPlayer.getBackground(),
                newPlayer.getDifficultyLevel(),
                newPlayer.getSpecialAbility(),
                newPlayer.getStarterItem(),
                newPlayer.getInventory(),
                newPlayer.getActions()
        );
    }

    private Action createAction(String actionType) {
        Action action = new Action();
        action.setActionType(actionType);
        return action;
    }

}
