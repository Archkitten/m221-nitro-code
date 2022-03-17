package com.example.sping_portfolio.algorithm;

import java.lang.Math;
import java.math.BigDecimal;
import java.util.stream.Stream;
import java.util.stream.IntStream;

public class SinStream extends _Sin {
    
    public SinStream(BigDecimal x, int y, int n) {
        super.method = "Stream";
        super.x = x;
        super.y = y;
        super.n = n;
    }

    @Override
    protected void run() {
        super.result = 0;
        startTime(); 
        double result = 0;
        IntStream arr = IntStream.rangeClosed(0, super.n - 1);
        arr.forEach(i -> super.result += iterate(i));

        endTime();
    }

}

