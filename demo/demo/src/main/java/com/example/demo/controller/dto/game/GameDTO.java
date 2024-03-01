package com.example.demo.controller.dto.game;

import java.util.*;

public record GameDTO(String key, String type, List players, String gameStatus,
                      String currentPlayer, UUID winner, int boardSize, List board,
                      List remainingTokens, List removedTokens) {
}
