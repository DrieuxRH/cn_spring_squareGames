package com.example.demo.controller;

import com.example.demo.service.GameService;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.InvalidPositionException;
import fr.le_campus_numerique.square_games.engine.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Stream;

@RestController
public class GameController {

    private Game game;


    @Autowired
    private GameService gameService;

    @GetMapping("/gameCatalog")
    public List getGameCatalog() {
        return gameService.getGameCatalog();
    }
    @GetMapping("/gameParameters")
    public List getNeededGamaParameters() {
        return gameService.getGameNeededParameters();
    }

    @PostMapping("/games")
    public GameDTO createGame(@RequestBody GameCreationParamsDTO params) {
        game = gameService.createGame(params.game(), params.playersNb(), params.boardSize());
        System.out.println(game);
        return translateGameToGameDTO(game);
    }

    @GetMapping("/games/{gameId}")
    public GameDTO getGame(@PathVariable String gameId) {
        game = gameService.getGame(gameId);
        return translateGameToGameDTO(game);
    }

    @PutMapping("/games/{gameId}")
    public GameDTO addMove(@PathVariable String gameId, @RequestBody moveDTO params){
        try {
            game = gameService.addMove(gameId, params.x(), params.y());
        } catch (InvalidPositionException e){
            System.out.println("bad request");
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Move is not allowed");
        }
        System.out.println("game: " + game);
        return translateGameToGameDTO(game);
    }

    private List<TokenDTO> mapTokensToTokenDTO(Stream<Token> tokensStream){
        List<TokenDTO> tokenDTOS = tokensStream.map(token -> new TokenDTO(token.getOwnerId().toString(),
                token.getName(),
                ((token.getPosition()!=null)?token.getPosition().x():null),
                ((token.getPosition()!=null)?token.getPosition().y():null)))
                .toList();

        return tokenDTOS;
    }



    private GameDTO translateGameToGameDTO(Game game){
        String gameUuid = game.getId().toString();
        UUID winnerUuid =(game.getStatus().name().equals("TERMINATED") )? game.getCurrentPlayerId() : null;
        List<TokenDTO> board =  mapTokensToTokenDTO(game.getBoard().values().stream());

        List<TokenDTO> remainingTokens = mapTokensToTokenDTO(game.getRemainingTokens().stream());
        List<TokenDTO> removedTokens = mapTokensToTokenDTO(game.getRemovedTokens().stream());
        System.out.println(game.getPlayerIds());
        List players = List.copyOf(game.getPlayerIds());
        String player1 = game.getPlayerIds().iterator().next().toString();
        String player2 = game.getPlayerIds().iterator().next().toString();


        GameDTO gameDTO = new GameDTO(gameUuid, game.getFactoryId(),players,
                game.getStatus().name(), game.getCurrentPlayerId().toString(), winnerUuid,
                game.getBoardSize(), board, remainingTokens, removedTokens);


        return gameDTO;

    }

}


