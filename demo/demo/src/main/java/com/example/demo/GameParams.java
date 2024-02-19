package com.example.demo;

import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.GameStatus;
import fr.le_campus_numerique.square_games.engine.Token;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public record GameParams(UUID key, String type, Set<UUID> playersUuid,
                         GameStatus gameStatus, UUID currentPlayer,
                         int boardSize, Map<CellPosition, Token> board,
                         Collection<Token> remainingTokens, Collection<Token> removedTokens) {
}
