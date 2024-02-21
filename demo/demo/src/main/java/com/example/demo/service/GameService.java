package com.example.demo.service;


import com.example.demo.controller.GameDTO;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.InvalidPositionException;

import java.util.Collection;
import java.util.List;

public interface GameService {

    List getGameCatalog();

    Game createGame(String type, int playersNb, int boardSize);

    List getGameNeededParameters();

    Game getGame(String id);

    Game addMove(String gameId, int x, int y) throws InvalidPositionException;
}


