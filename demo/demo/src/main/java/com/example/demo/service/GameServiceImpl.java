package com.example.demo.service;

import com.example.demo.plugin.GamePlugin;
import com.example.demo.plugin.TicTacToePlugin;
import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.InvalidPositionException;
import fr.le_campus_numerique.square_games.engine.Token;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
class GameServiceImpl implements GameService{

    private Game game;

    @Autowired
    private List<GamePlugin> gamePlugins;

    private final HashMap<String,Game> gameMap = new HashMap<>();

    @Override
    public Game createGame(String type) {
        for(GamePlugin gamePlugin: gamePlugins){
           if(gamePlugin.getType().equals(type)){
                game = gamePlugin.createGame();
            }
        }

        gameMap.put(game.getId().toString(), game);
        return game;
    }

    @Override
    public List getGameNeededParameters() {
        return null;
    }

    @Override
    public Game getGame(String id) {
        return gameMap.get(id);
    }

    @Override
    public Game addMove(String gameId, int x, int y) throws InvalidPositionException {
        game = gameMap.get(gameId);
        Token token = game.getRemainingTokens().iterator().next();
        token.moveTo(new CellPosition(x,y));

        return game;
    }

}
