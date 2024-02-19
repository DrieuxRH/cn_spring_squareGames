package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

@RestController
public class GameController {
    @Autowired
    private GameCatalog gameCatalog;
    private Game game;
    private final HashMap<UUID,Game> gameMap = new HashMap<>();

    @GetMapping("/gameCatalog")
    public Collection<String> getGameCatalog() {
        return gameCatalog.getGameIdentifiers();
    }
    @GetMapping("/gameParameters")
    public HashMap<String, String> getNeededGamaParameters() {
        return gameCatalog.getGameNeededParameters();
    }

    @PostMapping("/games")
    public GameParams createGame(@RequestBody GameCreationParams params) {
        System.out.println(params.game());
        game = gameCatalog.createGame(params.game(), params.playersNb(), params.boardSize());
        System.out.println(game);
        UUID gameUuid = UUID.randomUUID();
        GameParams gameDTO = new GameParams(gameUuid, game.getFactoryId(), game.getPlayerIds(),
                                            game.getStatus(), game.getCurrentPlayerId(), game.getBoardSize(),
                                            game.getBoard(), game.getRemainingTokens(), game.getRemovedTokens());
        gameMap.put(gameUuid, game);
        ObjectMapper objectMapper = new ObjectMapper();
        return gameDTO;
    }

    @GetMapping("/games/{gameId}")
    public Object getGame(@PathVariable String gameId) {

        // TODO - actually get and return game with id 'gameId'
        return null;
    }

}


