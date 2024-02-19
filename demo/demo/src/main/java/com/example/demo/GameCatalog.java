package com.example.demo;

import fr.le_campus_numerique.square_games.engine.Game;

import java.util.Collection;
import java.util.HashMap;

public interface GameCatalog {
    Collection<String> getGameIdentifiers();
    HashMap<String, String> getGameNeededParameters();

    Game createGame(String type, int playersNb, int boardSize);


}
