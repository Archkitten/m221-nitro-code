package com.example.sping_portfolio.algorithm;

import java.lang.Math;
import java.math.BigDecimal;

public class SinFor extends _Sin {
    
    public SinFor(BigDecimal x, int y, int n) {
        super.method = "For";
        super.x = x;
        super.y = y;
        super.n = n;
    }

    @Override
    protected void run() {
        super.result = 0;
        startTime(); 
        for (int i = 0; i < super.n; i++) {
            super.result += iterate(i);
        }

        endTime();
    }

}

