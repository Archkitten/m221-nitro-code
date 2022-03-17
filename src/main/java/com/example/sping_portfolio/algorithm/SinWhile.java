package com.example.sping_portfolio.algorithm;

import java.lang.Math;
import java.math.BigDecimal;

public class SinWhile extends _Sin {
    
    public SinWhile(BigDecimal x, int y, int n) {
        super.method = "While";
        super.x = x;
        super.y = y;
        super.n = n;
    }

    @Override
    protected void run() {
        super.result = 0;
        startTime(); 
        int i = 0;
        while (i < super.n) {
            super.result += iterate(i);
            i++;
        }

        endTime();
    }

}

