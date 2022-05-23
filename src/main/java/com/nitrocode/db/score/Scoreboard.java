package com.nitrocode.db.score;

import java.util.*;

public class Scoreboard {

    private Map<Integer, Integer> playertoSpeedMapping;
    private Map<Integer, Set<Integer>> speedtoPlayerMapping;

    public Scoreboard() {
        this.playertoSpeedMapping = new HashMap<>();
        this.speedtoPlayerMapping = new TreeMap<>(Collections.reverseOrder());
    }

}
