package com.example.demo;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.stereotype.Service;


@Service
public class GameCatalogDummyImpl implements GameCatalog {

    private TicTacToeGameFactory ticTacToeGameFactory;
    Game game;

    private Set<String> gameNames = new HashSet<String>();

    public GameCatalogDummyImpl() {
        this.ticTacToeGameFactory = new TicTacToeGameFactory();
        this.gameNames.add(ticTacToeGameFactory.getGameId());
    }


    @Override
    public Collection<String> getGameIdentifiers() {
        return gameNames;
    }

    @Override
    public HashMap<String, String> getGameNeededParameters() {
        return null;
    }

    public Game createGame(String ID, int playersNb, int boardSize){
        switch (ID){
            case "tictactoe" -> game = new TicTacToeGameFactory().createGame(playersNb, boardSize );
        }
        return game;
    }

}
