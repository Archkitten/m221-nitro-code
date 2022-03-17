package com.example.sping_portfolio.algorithm;

import java.lang.Math;
import java.math.BigDecimal;

public class SinRecurse extends _Sin {
    
    public SinRecurse(BigDecimal x, int y, int n) {
        super.method = "Recursion";
        super.x = x;
        super.y = y;
        super.n = n;
    }

    @Override
    protected void run() {
        super.result = 0;
        startTime(); 
        super.result = recursion(0);
        endTime();
    }

    public double recursion(int i) {
        if (i == super.n - 1) {
            return iterate(i);
        } else {
            return iterate(i) + recursion(i+1);
        }
    }

}

