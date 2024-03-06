package com.example.demo.controller;

import com.example.demo.controller.dto.matches.MatchDTO;
import com.example.demo.controller.dto.user.UserAuthSendDTO;
import com.example.demo.controller.dto.user.UserDTO;
import com.example.demo.controller.dto.user.UsersAuthDTO;
import com.example.demo.response.ResponseHandler;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class StatisticsController {

    private String url = "http://172.22.114.56:9191";

    @GetMapping("/statistics/matches")
    public ResponseEntity<String> geLtAllUsers() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response
                = restTemplate.getForEntity(url + "/Matches", String.class);
        System.out.println(response);
        // TODO errorhandling
        // TODO handling of the reponse
        //return ResponseHandler.generateResponse("Users found", HttpStatus.OK, response);
        return response;
    }

    @PostMapping("statistics/match")
    public ResponseEntity<String> addMatch(@RequestBody MatchDTO params) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MatchDTO> request = new HttpEntity<>(params);
        ResponseEntity<String> response = restTemplate.postForEntity( url + "/Match", request , String.class );
        return response;
    }
}
