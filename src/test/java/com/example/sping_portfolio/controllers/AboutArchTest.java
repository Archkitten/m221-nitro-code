package com.example.sping_portfolio.controllers;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AboutArchTest {
    AboutArch aboutArch = new AboutArch();
    @Test
    public void testHalfLife() {
        int n = 5;
        AboutArch.AlgorithmData result = aboutArch.halfLifeFor(n);
        System.out.println(result.getMethod());
        System.out.println(result.getTotalTimeInNano());
        System.out.println(result.getResult());

    }

    @Test
    public void testCutHalf() {
        List<Double> list = new ArrayList<>();
        int n = 5;
        double radioactivity = 14.0;
        aboutArch.cutHalf(list, radioactivity, n);
        System.out.println(list);
    }
}
