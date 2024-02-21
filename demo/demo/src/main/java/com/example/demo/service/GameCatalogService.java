package com.example.demo.service;

import fr.le_campus_numerique.square_games.engine.Game;

import java.util.Collection;
import java.util.HashMap;

public interface GameCatalogService {
    Collection<String> getGameIdentifiers();
    HashMap<String, String> getGameNeededParameters();

}
