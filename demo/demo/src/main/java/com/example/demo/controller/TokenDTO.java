package com.example.demo.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.le_campus_numerique.square_games.engine.CellPosition;

import java.util.Set;
import java.util.UUID;

//TODO search annotation that removes the null from json
public record TokenDTO(String ownerID, String name, Integer x, Integer y) {
}
