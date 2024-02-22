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
    private String playersNb;

    @Value("${game.tictactoe.default-board-size")
    private String  boardSize;

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
    /* Playing with @Value
    public TicTacToePlugin(@Value("${game.tictactoe.default-player-count}") Integer playersNb) {
        this.playersNb = playersNb;
    }

    @Value("${game.tictactoe.default-player-count}")
    //private String playersNb;

    @PostConstruct
    public void test() {
        System.out.println("players nb: " + playersNb);
    }


     */
    public Game createGame() {
        game = new TicTacToeGameFactory().createGame(Integer.parseInt(playersNb),Integer.parseInt(boardSize));
        return game;
    }
}
