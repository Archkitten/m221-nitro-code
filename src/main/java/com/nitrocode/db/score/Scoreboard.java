package com.nitrocode.db.score;

import java.util.*;

public class Scoreboard {

    private Map<Integer, Integer> playertoSpeedMapping;
    private Map<Integer, Set<Integer>> speedtoPlayerMapping;

    public Scoreboard() {
        this.playertoSpeedMapping = new HashMap<>();
        this.speedtoPlayerMapping = new TreeMap<>(Collections.reverseOrder());
    }

    public void addSpeed(int playerId, int speed) {
        if (playertoSpeedMapping.containsKey(playerId)) {
            int previousSpeed = playertoSpeedMapping.get(playerId);
            if (speedtoPlayerMapping.getOrDefault(previousSpeed, new HashSet<>()).contains(playerId)) {
                speedtoPlayerMapping.get(previousSpeed).remove(playerId);
            }
            speed += previousSpeed;
        }
        playertoSpeedMapping.put(playerId, speed);
        speedtoPlayerMapping.computeIfAbsent(speed, i -> new HashSet<>()).add(playerId);
    }

}
