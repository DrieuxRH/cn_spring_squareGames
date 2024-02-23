package com.example.demo.plugin;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGame;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Connect4Plugin implements GamePlugin{
    private Game game;

    @Value("${game.connect4.default-player-count}")
    private Integer playersNb;

    @Value("${game.connect4.default-board-size}")
    private Integer  boardSize;

    @Autowired
    private MessageSource messageSource;

    @Override
    public String getName(Locale locale) {
        return messageSource.getMessage("game.connect4.factoryId",null,locale);
    }

    public String getType(){
        return new ConnectFourGameFactory().getGameFactoryId();
    }

    @Override
    public Game createGame() {
        game = new ConnectFourGameFactory().createGame(playersNb,boardSize);
        return game;
    }
}
