package com.example.demo.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import org.springframework.stereotype.Service;


@Service
class GameCatalogServiceImpl implements GameCatalogService {

    private TicTacToeGameFactory ticTacToeGameFactory;
    private TaquinGameFactory taquinGameFactory;
    private ConnectFourGameFactory connectFourGameFactory;

    Game game;

    private Set<String> gameNames = new HashSet<String>();

    public GameCatalogServiceImpl() {
        this.ticTacToeGameFactory = new TicTacToeGameFactory();
        this.taquinGameFactory = new TaquinGameFactory();
        this.connectFourGameFactory = new ConnectFourGameFactory();
        this.gameNames.add(ticTacToeGameFactory.getGameFactoryId());
        this.gameNames.add(taquinGameFactory.getGameFactoryId());
        this.gameNames.add(connectFourGameFactory.getGameFactoryId());
    }


    @Override
    public Collection<String> getGameIdentifiers() {
        return gameNames;
    }

    @Override
    public HashMap<String, String> getGameNeededParameters() {
        return null;
    }



}
