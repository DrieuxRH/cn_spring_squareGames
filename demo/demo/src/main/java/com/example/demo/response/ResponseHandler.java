package com.example.demo.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj){
        Map<String, Object> responseMap = new HashMap<String, Object>();

        responseMap.put("message", message);
        responseMap.put("status", status.value());
        responseMap.put("data", responseObj);

        return new ResponseEntity<Object>(responseMap, status);
    }
}
