package com.example.demo.plugin;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class TicTacToePlugin implements GamePlugin{

    private Game game;

    @Value("${game.tictactoe.default-player-count}")
    private Integer playersNb;

    @Value("${game.tictactoe.default-board-size}")
    private Integer boardSize;

    @Autowired
    private MessageSource messageSource;

    @Override
    public String getName(Locale locale) {
        System.out.println(locale);
        return messageSource.getMessage("game.tictactoe.factoryId",null,locale);
    }

    public String getType(){
        return new TicTacToeGameFactory().getGameFactoryId();
    }

    public Game createGame() {
        game = new TicTacToeGameFactory().createGame(playersNb,boardSize);
        return game;
    }
}
