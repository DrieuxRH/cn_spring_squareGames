package com.example.demo.service;

import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.InvalidPositionException;
import fr.le_campus_numerique.square_games.engine.Token;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
class GameServiceImpl implements GameService{

    private Game game;

    @Autowired
    private GameCatalogService gameCatalog;

    private final HashMap<String,Game> gameMap = new HashMap<>();

    @Override
    public Game createGame(String type, int playersNb, int boardSize) {
        switch (type){
            case "tictactoe" -> game = new TicTacToeGameFactory().createGame(playersNb, boardSize );
            case "15 puzzle" -> game = new TaquinGameFactory().createGame(playersNb, boardSize );
            case "connect4" -> game = new ConnectFourGameFactory().createGame(playersNb, boardSize );
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

    @Override
    public List getGameCatalog() {
        return List.copyOf(gameCatalog.getGameIdentifiers());
    }


}
