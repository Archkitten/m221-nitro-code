package com.example.sping_portfolio.algorithm;

import org.springframework.util.StringUtils;
import java.math.BigDecimal;
import java.math.BigInteger;

// x = radians
// y = precision
// n = iterations
public abstract class _Sin {
    String method, equation, notes = "";
    BigDecimal x;
    int y, n;
    double result, sTime, eTime, elpsd;
    
    public void startTime() {
        this.sTime = System.nanoTime();
    }

    public void endTime() {
        this.eTime = System.nanoTime();
        this.elpsd = (this.eTime - this.sTime) / Math.pow(10,9);
    }

    public void print() {
        this.run();

        System.out.println(this.equation);
        System.out.println(this.notes);
        
        System.out.println(
                    String.format("| %7s | %.17f | %6s |",
                        method,
                        result,
                        String.valueOf(elpsd)
                    )
                );
    }

    public BigInteger factorial(BigInteger x) {
        if (x.compareTo(BigInteger.valueOf(2)) > 0) {
            return x.multiply(factorial(x.subtract(BigInteger.valueOf(1))));
        } else {
            return x;
        }
    }

    public String getElapsed() { 
        return String.valueOf(this.elpsd);
    }

    public String getMethod() {
        return this.method;
    }
    
    public String getRadians() {
        return String.valueOf(this.x.doubleValue());
    }

    public String getResult() {
        return String.valueOf(this.result);
    }

    public double iterate(int i) {
        String sAdd;
        BigDecimal a, b, c, add;
        int e;
        String operator;
        
        if (i % 2 == 0) {
            operator = " + ";
        } else {
            operator = " - ";
        }

        e = i * 2 + 1;

        a = this.x.pow(e);
        b = new BigDecimal(this.factorial(BigInteger.valueOf(e)));
        c = new BigDecimal(Math.pow(-1,i));

        sAdd = operator + "x^" + e + " / " + e + "!";
        this.equation += sAdd;
        add = a.divide(b, this.y, BigDecimal.ROUND_HALF_EVEN).multiply(c);
        this.notes += String.format("%s%." + (sAdd.length() - 5) + "f", operator, add.abs());

        return add.doubleValue();
    }

    protected abstract void run();
}

