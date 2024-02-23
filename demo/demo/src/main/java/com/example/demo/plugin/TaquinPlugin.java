package com.example.demo.plugin;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGame;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class TaquinPlugin implements GamePlugin{

    private Game game;

    @Value("${game.taquin.default-player-count}")
    private Integer playersNb;

    @Value("${game.taquin.default-board-size}")
    private Integer  boardSize;

    @Autowired
    private MessageSource messageSource;

    @Override
    public String getName(Locale locale) {
        return messageSource.getMessage("game.taquin.factoryId",null,locale);
    }

    public Game createGame() {
        game = new TaquinGameFactory().createGame(playersNb,boardSize);
        return game;
    }

    @Override
    public String getType() {
        return new TaquinGameFactory().getGameFactoryId();
    }
}
