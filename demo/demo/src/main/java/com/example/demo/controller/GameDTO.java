package com.example.demo.controller;

import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Token;

import java.util.*;

public record GameDTO(String key, String type, List players, String gameStatus,
                      String currentPlayer, UUID winner, int boardSize, List board,
                      List remainingTokens, List removedTokens) {
}
