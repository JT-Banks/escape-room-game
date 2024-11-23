package com.veero.escaperoomgame.asylum.service;

import com.veero.escaperoomgame.asylum.model.Action;
import com.veero.escaperoomgame.asylum.model.InteractionType;
import com.veero.escaperoomgame.asylum.model.Item;
import com.veero.escaperoomgame.core.dto.PlayerCreationResponse;
import com.veero.escaperoomgame.core.model.InventoryImpl;
import com.veero.escaperoomgame.core.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {

    private final StarterItemService starterItemService;

    @Autowired
    public PlayerService(StarterItemService starterItemService) {
        this.starterItemService = starterItemService;
    }

    public Player createNewPlayer(Player newPlayerData) {
        Player newPlayer = new Player();
        List<Action> actionList = new ArrayList<>();
        Action inspectAction = new Action();
        inspectAction.setActionType("Inspect");
        actionList.add(inspectAction);
        newPlayer.setPlayerName(newPlayerData.getPlayerName());
        newPlayer.setPlayerId(String.valueOf((int) (Math.random() * 9000) + 1000));
        newPlayer.setBackground(newPlayerData.getBackground());
        newPlayer.setDifficultyLevel(newPlayerData.getDifficultyLevel());
        newPlayer.setSpecialAbility(newPlayerData.getSpecialAbility());
        newPlayer.setStatus(Player.PlayerStatus.PLAYING);
        newPlayer.setCurrentRoomId("1");
        newPlayer.setInteractionId("1");
        newPlayer.setScore(0);
        newPlayer.setTimeRemaining(60.00);
        Item starterItem = starterItemService.getStarterItem(newPlayerData.getStarterItem());
        newPlayer.setStarterItem(starterItem.getName());
        newPlayer.addItem(starterItem);
        newPlayer.setInventory(new InventoryImpl());
        newPlayer.setActions(actionList);
        return newPlayer;
    }

    public PlayerCreationResponse createPlayerResponse(Player newPlayer) {
        return new PlayerCreationResponse(
                newPlayer.getPlayerId(),
                newPlayer.getPlayerName(),
                newPlayer.getBackground(),
                newPlayer.getDifficultyLevel(),
                newPlayer.getSpecialAbility(),
                newPlayer.getStarterItem()
        );
    }
}
