package com.example.demo.service;

import java.util.*;

import com.example.demo.plugin.GamePlugin;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
class GameCatalogServiceImpl implements GameCatalogService {

    private TicTacToeGameFactory ticTacToeGameFactory;
    private TaquinGameFactory taquinGameFactory;
    private ConnectFourGameFactory connectFourGameFactory;

    Game game;
    //private Set<String> gameNames = new HashSet<String>();

    @Autowired
    private List<GamePlugin> gamePlugins;


   public Collection<String> getGameIdentifiers(Locale locale) {
        Set<String> gameNames = new HashSet<String>();
        for(GamePlugin gamePlugin: gamePlugins){
           System.out.println("game name: " + gamePlugin.getName(locale));
           gameNames.add(gamePlugin.getName(locale));
        }
        return gameNames;
    }

    @Override
    public List getGameCatalog(Locale locale) {

        return List.copyOf(getGameIdentifiers(locale));
    }
}
