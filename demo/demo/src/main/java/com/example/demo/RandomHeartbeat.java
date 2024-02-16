package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomHeartbeat implements HeartbeatSensor {

    @Override
    public int get() {
        Random r = new Random();
        int low = 0;
        int high = 140;
        int result = r.nextInt(high-low) + low;
        return result;
    }
}
